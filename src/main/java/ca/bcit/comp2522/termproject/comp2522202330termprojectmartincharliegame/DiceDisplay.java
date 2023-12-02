package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;


public class DiceDisplay {
    private final Color BORDER_COLOR = Color.BLACK;
    private final Color LOCKED_COLOR = Color.RED;
    private final Color SELECTED_COLOR = Color.FORESTGREEN;
    private final Color USED_COLOR = Color.GREY;
    private final ImageView[] diceViews;
    private final Fishing_Rod fishingRod;
    private final Dice_Roller diceRoller;
    private final HBox hBox;
    private final VBox[] vBox;
    private int rollCounter;
    private int roundCounter;
    private final int MAX_ROUNDS = 5;
    private final Stage primaryStage;

    private enum GameState {
        WAITING_FOR_USE_DICE,
        WAITING_FOR_DICE_SELECTION,
        DICE_IN_USE
    }

    private boolean isActiveQuestOpen = false;

    private boolean isDiceViewOpen = false;
    private GameState gameState = GameState.WAITING_FOR_USE_DICE;

    private final ArrayList<Dice> selectedDice;
    private final ArrayList<Dice> usedDice;

    public DiceDisplay(Stage primaryStage) {
        this.primaryStage = primaryStage;
        fishingRod = new Fishing_Rod();
        diceRoller = new Dice_Roller(fishingRod.getComponents());

        diceViews = new ImageView[fishingRod.getComponents().size()];
        updateDiceView();

        hBox = new HBox();
        vBox = new VBox[]{new VBox(), new VBox(), new VBox(), new VBox(), new VBox(), new VBox()};

        rollCounter = 0;
        roundCounter = 0;

        selectedDice = new ArrayList<Dice>();
        usedDice = new ArrayList<Dice>();
    }


    private StackPane createRoundedBorderedImageView(ImageView imageView, Color color) {
        // Create a StackPane to hold the ImageView
        StackPane stackPane = new StackPane(imageView);

        Border border = getRoundedBorder(color);

        // Apply the Border to the StackPane
        stackPane.setBorder(border);

        // Set the size of the StackPane to match the size of the image
        stackPane.setMaxSize(imageView.getFitWidth(), imageView.getFitHeight());

        return stackPane;
    }

    private Border getRoundedBorder(Color color) {
        // Define the corner radii for rounded borders
        CornerRadii cornerRadii = new CornerRadii(10); // Adjust the radius as needed
        BorderWidths borderWidth = new BorderWidths(15); // Adjust the width as needed

        // Create a BorderStroke with the desired color, style, width, and rounded corners
        BorderStroke borderStroke = new BorderStroke(color, BorderStrokeStyle.SOLID, cornerRadii, borderWidth);

        // Create a Border with the specified BorderStroke
        return new Border(borderStroke);
    }

    private void resetBorderColor(ImageView imageView) {
        setBorderColor(imageView, BORDER_COLOR);
    }

    private void setBorderColor(ImageView imageView, Color color) {
        // Apply the border color to the given ImageView
        Border border = getRoundedBorder(color);
        ((StackPane) imageView.getParent()).setBorder(border);
    }

    private ImageView getImageViews(Dice dice) {
        return getDiceFaceImage(dice.getFaceUpValue());
    }

    private ImageView getDiceFaceImage(Integer diceFace) {
        Image pipOne = new Image("file:../../resources//Dice/dice-six-faces-one.png");
        Image pipTwo = new Image("file:../../resources//Dice/dice-six-faces-two.png");
        Image pipThree = new Image("file:../../resources//Dice/dice-six-faces-three.png");
        Image pipFour = new Image("file:../../resources//Dice/dice-six-faces-four.png");
        Image pipFive = new Image("file:../../resources//Dice/dice-six-faces-five.png");
        Image pipSix = new Image("file:../../resources//Dice/dice-six-faces-six.png");

        return switch (diceFace) {
            case 1 -> new ImageView(pipOne);
            case 2 -> new ImageView(pipTwo);
            case 3 -> new ImageView(pipThree);
            case 4 -> new ImageView(pipFour);
            case 5 -> new ImageView(pipFive);
            default -> new ImageView(pipSix);
        };
    }

    public void lockDice(ImageView clickedDiceView, Fishing_Rod fishingRod) {
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

    private int findDiceViewPosition(ImageView targetImageView) {
        for (int i = 0; i < diceViews.length; i++) {
            if (diceViews[i] == targetImageView) {
                return i; // Found the target ImageView, return its index
            }
        }
        return -1; // If the target ImageView is not found in the array
    }

    public void rollDice(final ActionEvent event) {
        if (gameState != GameState.WAITING_FOR_USE_DICE || rollCounter >= 3) {
            return;
        }
        gameState = GameState.DICE_IN_USE;
        rollCounter++;
        System.out.println("Rolling dice...\n");

        // Create a SequentialTransition to play timelines sequentially
        SequentialTransition sequentialTransition = new SequentialTransition();

        // Number of rolls
        int numberOfRolls = 1;  // Adjust as needed

        for (int rollNumber = 0; rollNumber < numberOfRolls; rollNumber++) {
            // Create a Timeline for each roll
            Timeline timeline = new Timeline();

            // Define the duration for each keyframe (adjust as needed)
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
        }

        // Play the sequential transition
        sequentialTransition.play();
    }

    private void readyDiceForSelection() {
        gameState = GameState.WAITING_FOR_DICE_SELECTION;
        for (int i = 0; i < diceViews.length; i++) {
            if (diceRoller.isLocked(fishingRod.getComponents().get(i))) {
                setBorderColor(diceViews[i], BORDER_COLOR);
            }
        }

    }

    private void updateDiceFaceViews() {
        // Update the face value of the diceRoller based on the animation
        diceRoller.rollDice();

        for (int i = 0; i < diceViews.length; i++) {
            ImageView newImageView = getImageViews(fishingRod.getComponents().get(i));

            StackPane stackPane;
            if (diceRoller.isLocked(fishingRod.getComponents().get(i))) {
                stackPane = createRoundedBorderedImageView(newImageView, LOCKED_COLOR);
            } else {
                stackPane = createRoundedBorderedImageView(newImageView, BORDER_COLOR);
            }

            vBox[i].getChildren().set(0, stackPane);

            // Update the diceViews array
            diceViews[i] = newImageView;
            diceViews[i].setPreserveRatio(true);
            diceViews[i].setFitWidth(100);
        }
    }

    private void updateDiceView() {
        for (int i = 0; i < fishingRod.getComponents().size(); i++) {
            diceViews[i] = getImageViews(fishingRod.getComponents().get(i));
        }

        for (ImageView diceView : diceViews) {
            diceView.setPreserveRatio(true); // Preserve the aspect ratio of the image
            diceView.setFitWidth(100); // Set the desired width
        }
    }
    public void useDice(final ActionEvent event) {
        if (gameState == GameState.WAITING_FOR_USE_DICE) {
            readyDiceForSelection();
            System.out.println("Using dice...\n");
        }
    }

    private void selectDice(int diceIndex) {
        System.out.printf("Selecting dice %d\n", diceIndex);
        if (gameState == GameState.WAITING_FOR_DICE_SELECTION) {
            Dice dice = fishingRod.getComponents().get(diceIndex);
            if (selectedDice.contains(dice)) {
                resetBorderColor(diceViews[diceIndex]);
                selectedDice.remove(dice);
            } else if (!usedDice.contains(dice)){
                selectedDice.add(dice);
                setBorderColor(diceViews[diceIndex], SELECTED_COLOR);
            }
        } else if (gameState == GameState.WAITING_FOR_USE_DICE) {
            DiceFaceModal diceFaceModal = new DiceFaceModal(diceIndex);
            diceFaceModal.openInGamePopup(primaryStage);
        }
    }

    private void finishDice(final ActionEvent event) {
        if (gameState != GameState.DICE_IN_USE) {
            System.out.println("Finishing dice...\n");
            gameState = GameState.WAITING_FOR_USE_DICE;
            for (Dice dice : fishingRod.getComponents()) {
                diceRoller.unlockDice(dice);
            }
            rollCounter = 0;
            roundCounter++;
            System.out.println("Round " + roundCounter + " finished.");
        }
        if (roundCounter == MAX_ROUNDS) {
            System.out.println("Day finished.");
            System.exit(0);
        }
    }
    
    public HBox getDiceDisplay() {
        for (int i = 0; i < diceViews.length; i++) {
            int diceIndex = i;
            Button lockUnlock = ButtonMaker.createButton("lockUnlock", event -> lockDice(diceViews[diceIndex], fishingRod), 0, 0);
            lockUnlock.setMaxWidth(130);
            StackPane diceBox = createRoundedBorderedImageView(diceViews[i], BORDER_COLOR);
            diceBox.setOnMouseClicked(event -> selectDice(diceIndex));
            vBox[i].getChildren().addAll(diceBox, lockUnlock);
            hBox.getChildren().add(vBox[i]);
        }
        Button rollDice = ButtonMaker.createButton("rollDice", this::rollDice, 0, 0);
        Button useDice = ButtonMaker.createButton("useDice", this::useDice, 0, 0);
        Button finishDice = ButtonMaker.createButton("finishRound", this::finishDice, 0, 0);
        Button activeQuests = ButtonMaker.createButton("activeQuests", this::activeQuests, 0, 0);
        rollDice.setMaxWidth(130);
        useDice.setMaxWidth(130);
        finishDice.setMaxWidth(130);
        activeQuests.setMaxWidth(130);
        vBox[diceViews.length].getChildren().addAll(rollDice, useDice, finishDice, activeQuests);
        hBox.getChildren().add(vBox[diceViews.length]);

        hBox.setSpacing(25);

        return hBox;
    }

    private void activeQuests(ActionEvent actionEvent) {
        ModalPopUp modalPopUp = new ActiveQuestModal();
        modalPopUp.openInGamePopup(primaryStage);
    }

    public static void main(String[] args) {
    }
}
