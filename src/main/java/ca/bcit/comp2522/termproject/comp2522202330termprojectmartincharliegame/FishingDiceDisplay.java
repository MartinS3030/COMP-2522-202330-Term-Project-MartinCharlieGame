package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.SequentialTransition;
import javafx.animation.KeyFrame;

/**
 * Represents the dice display for the fishing game.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public class FishingDiceDisplay extends DiceDisplay {
    private static final Color LOCKED_COLOR = Color.RED;
    private final DiceRoller diceRoller;
    private final Stage primaryStage;
    private int rollCounter;
    private final int roundCounter;

    /**
     * Represents the game state.
     */
    public enum GameState {
        WAITING_FOR_USE_DICE,
        WAITING_FOR_DICE_SELECTION,
        DICE_IN_USE
    }

    private GameState gameState = GameState.WAITING_FOR_USE_DICE;

    /**
     * Constructs a fishing dice display.
     *
     * @param primaryStage the stage
     */
    public FishingDiceDisplay(final Stage primaryStage) {
        super();
        this.primaryStage = primaryStage;
        diceRoller = new DiceRoller(getFishingRod().getComponents());
        diceRoller.rollDice();
        rollCounter = 0;
        roundCounter = getPlayer().getCastOfTheDay();
    }

    /**
     * Gets rod from player.
     */
    @Override
    protected FishingRod generateRod() {
        return getPlayer().getRod();
    }

    /**
     * Locks the dice from rolling if it is unlocked, and vice versa.
     *
     * @param clickedDiceView the dice view that was clicked
     * @param fishingRod the fishing rod
     */
    public void lockDice(final ImageView clickedDiceView, final FishingRod fishingRod) {
        if (gameState != GameState.WAITING_FOR_USE_DICE) {
            return;
        }
        if (diceRoller.isLocked(fishingRod.getComponents().get(findDiceViewPosition(clickedDiceView)))) {
            resetBorderColor(clickedDiceView);
            // Unlock the dice
            diceRoller.unlockDice(fishingRod.getComponents().get(findDiceViewPosition(clickedDiceView)));
        } else {
            // Lock the dice
            diceRoller.lockDice(fishingRod.getComponents().get(findDiceViewPosition(clickedDiceView)));
            // Change the border color of the locked ImageView
            setBorderColor(clickedDiceView, LOCKED_COLOR);
        }
    }

    /**
     * Rolls the dice with animation.
     *
     * @param event the mouse event
     */
    public void rollDice(final ActionEvent event) {
        if (gameState != GameState.WAITING_FOR_USE_DICE || rollCounter >= 3) {
            return;
        }
        gameState = GameState.DICE_IN_USE;
        rollCounter++;
        System.out.println("Rolling dice...\n");

        // Create a SequentialTransition to play timelines sequentially
        SequentialTransition sequentialTransition = new SequentialTransition();

        // Create a Timeline for each roll
        Timeline timeline = new Timeline();

        // Define the duration for each keyframe
        Duration keyFrameDuration = Duration.millis(20);

        // Add keyframes for each number the dice can show
        for (int i = 1; i <= 13; i++) {

            // Create a keyframe to transition to the next image
            KeyFrame keyFrame = new KeyFrame(
                    keyFrameDuration.multiply(i * i),  // Adjust timing
                    (ActionEvent e) -> updateDiceFaceViews()
            );

            // Add the keyframe to the timeline
            timeline.getKeyFrames().add(keyFrame);
        }
        timeline.setOnFinished(e -> {
            if (rollCounter == 3) {
                readyDiceForSelection();
            } else {
                gameState = GameState.WAITING_FOR_USE_DICE;
            }

        });

        // Add the timeline to the sequential transition
        sequentialTransition.getChildren().add(timeline);

        // Play the sequential transition
        sequentialTransition.play();
    }

    private void readyDiceForSelection() {
        gameState = GameState.WAITING_FOR_DICE_SELECTION;
        for (ImageView diceView : getDiceViews()) {
            setBorderColor(diceView, getBORDERCOLOR());
        }

    }

    private void updateDiceFaceViews() {
        // Update the face value of the diceRoller based on the animation
        diceRoller.rollDice();

        for (int i = 0; i < getDiceViews().length; i++) {
            ImageView newImageView = getImageViews(getFishingRod().getComponents().get(i));

            StackPane stackPane;
            if (diceRoller.isLocked(getFishingRod().getComponents().get(i))) {
                stackPane = createRoundedBorderedImageView(newImageView, LOCKED_COLOR);
            } else {
                stackPane = createRoundedBorderedImageView(newImageView, getBORDERCOLOR());
            }
            int finalI = i;
            stackPane.setOnMouseClicked(event -> selectDice(finalI));

            getvBox()[i].getChildren().set(0, stackPane);

            // Update the diceViews array
            getDiceViews()[i] = newImageView;
            getDiceViews()[i].setPreserveRatio(true);
            getDiceViews()[i].setFitWidth(100);
        }
    }

    /**
     * Readies the dice to use for fish.
     *
     * @param event the mouse event
     */
    public void useDice(final ActionEvent event) {
        if (rollCounter != 0 && gameState == GameState.WAITING_FOR_USE_DICE) {
            readyDiceForSelection();
            System.out.println("Using dice...\n");
        }
    }

    /**
     * Selects the dice.
     *
     * @param diceIndex the index of the dice
     */
    @Override
    protected void selectDice(final int diceIndex) {
        System.out.printf("Selecting dice %d\n", diceIndex);
        if (gameState == GameState.WAITING_FOR_DICE_SELECTION) {
            Dice dice = getFishingRod().getComponents().get(diceIndex);
            if (getSelectedDice().contains(dice)) {
                resetBorderColor(getDiceViews()[diceIndex]);
                getSelectedDice().remove(dice);
            } else if (!getUsedDice().contains(dice)) {
                getSelectedDice().add(dice);
                setBorderColor(getDiceViews()[diceIndex], getSELECTEDCOLOR());
            }
        } else if (gameState == GameState.WAITING_FOR_USE_DICE) {
            DiceFaceModal diceFaceModal = new DiceFaceModal(diceIndex);
            diceFaceModal.openInGamePopup(primaryStage);
        }
    }

    /**
     * Finishes the round of dice rolling.
     *
     * @param event the mouse event
     */
    private void finishDice(final ActionEvent event) {
        if (gameState != GameState.DICE_IN_USE) {
            System.out.println("Finishing dice...\n");
            gameState = GameState.WAITING_FOR_USE_DICE;
            diceRoller.resetDice();
            getSelectedDice().clear();
            getUsedDice().clear();
            for (ImageView diceView : getDiceViews()) {
                setBorderColor(diceView, getBORDERCOLOR());
            }
            rollCounter = 0;
            getPlayer().setCastOfTheDay(roundCounter + 1);
            System.out.println("Round " + roundCounter + " finished.");
            new InitialFishingScreen().start(primaryStage);
        }
    }

    /**
     * Gets the dice display.
     *
     * @return the dice display
     */
    @Override
    public HBox getDiceDisplay() {
        for (int i = 0; i < getDiceViews().length; i++) {
            int diceIndex = i;
            Button lockUnlock = ButtonMaker.createButton("Lock", event -> lockDice(getDiceViews()[diceIndex],
                    getFishingRod()), 0, 0);
            lockUnlock.setMaxWidth(130);
            StackPane diceBox = createRoundedBorderedImageView(getDiceViews()[i], getBORDERCOLOR());
            diceBox.setOnMouseClicked(event -> selectDice(diceIndex));
            getvBox()[i].getChildren().addAll(diceBox, lockUnlock);
            gethBox().getChildren().add(getvBox()[i]);
        }
        Button activeQuests = ButtonMaker.createButton("Quests", this::activeQuests, 0, 0);
        Button rollDice = ButtonMaker.createButton("Roll Dice", this::rollDice, 0, 0);
        Button useDice = ButtonMaker.createButton("Use Dice", this::useDice, 0, 0);
        Button finishDice = ButtonMaker.createButton("Finish", this::finishDice, 0, 0);
        rollDice.setMaxWidth(130);
        useDice.setMaxWidth(130);
        finishDice.setMaxWidth(130);
        activeQuests.setMaxWidth(130);
        getvBox()[getDiceViews().length].getChildren().addAll(rollDice, useDice, finishDice, activeQuests);
        gethBox().getChildren().add(getvBox()[getDiceViews().length]);

        gethBox().setSpacing(25);

        return gethBox();
    }

    /**
     * Opens the active quests modal.
     *
     * @param actionEvent the action event
     */
    public void activeQuests(final ActionEvent actionEvent) {
        ModalPopUp modalPopUp = new ActiveQuestModal();
        modalPopUp.openInGamePopup(primaryStage);
    }
}
