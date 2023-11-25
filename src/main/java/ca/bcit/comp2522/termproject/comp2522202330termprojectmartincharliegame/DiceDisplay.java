package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class DiceDisplay {
    private final Color BORDER_COLOR = Color.BLACK;
    private final Color LOCKED_COLOR = Color.RED;
    private ImageView[] diceViews;
    private Fishing_Rod fishingRod;
    private Dice_Roller diceRoller;
    private HBox hBox;
    private VBox[] vBox;
    private boolean rollingInProgress = false;

    public DiceDisplay() {
        fishingRod = new Fishing_Rod();
        diceRoller = new Dice_Roller(fishingRod.getComponents());

        diceViews = new ImageView[fishingRod.getComponents().size()];
        updateDiceView();

        hBox = new HBox();
        vBox = new VBox[]{new VBox(), new VBox(), new VBox(), new VBox(), new VBox(), new VBox()};
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
        if (rollingInProgress) {
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
        if (rollingInProgress) {
            return;
        }
        rollingInProgress = true;
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
                rollingInProgress = false;
            });

            // Add the timeline to the sequential transition
            sequentialTransition.getChildren().add(timeline);
        }

        // Play the sequential transition
        sequentialTransition.play();
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
    public HBox getDiceDisplay() {
        for (int i = 0; i < diceViews.length; i++) {
            int diceIndex = i;
            Button lockUnlock = ButtonMaker.createButton("lockUnlock", event -> lockDice(diceViews[diceIndex], fishingRod), 0, 0);
            lockUnlock.setMaxWidth(130);
            vBox[i].getChildren().addAll(createRoundedBorderedImageView(diceViews[i], BORDER_COLOR), lockUnlock);
            hBox.getChildren().add(vBox[i]);
        }
        Button rollDice = ButtonMaker.createButton("rollDice", this::rollDice, 0, 0);
        rollDice.setMaxWidth(130);
        vBox[diceViews.length].getChildren().add(rollDice);
        hBox.getChildren().add(vBox[diceViews.length]);

        hBox.setSpacing(25);

        return hBox;
    }

    public static void main(String[] args) {
    }
}
