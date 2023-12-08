package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Displays the intro screen.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public class IntroScreen extends Application {
    private final String name = Player.getInstance("Charlie").getName();

    /**
     * Starts the intro screen.
     *
     * @param primaryStage the stage
     */
    @Override
    public void start(final Stage primaryStage) {
        List<String> paragraphs = getParagraphs();
        List<Label> labelList = createLabels(paragraphs);

        Button next = ButtonMaker.createButton("Begin!", this::nextScreen, 0, 0);
        VBox text = setupTextPane(labelList, next);

        StackPane root = new StackPane();
        StackPane.setMargin(labelList.get(0), new javafx.geometry.Insets(50, 50, 50, 50));
        root.getChildren().addAll(text);
        root.setStyle("-fx-background-color: #87CEEB;");

        final int appWidth = 1200;
        final int appHeight = 648;
        Scene scene = new Scene(root, appWidth, appHeight);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Tide's Up, Funds Up!");
        primaryStage.show();

        List<FadeTransition> labelFadeTransitions = createLabelFadeTransitions(labelList);
        FadeTransition buttonFadeTransition = createButtonFadeTransition(next);

        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(labelFadeTransitions);
        sequentialTransition.getChildren().add(buttonFadeTransition);

        sequentialTransition.play();
    }

    /**
     * Creates a list of labels.
     *
     * @param paragraphs the paragraphs as strings
     * @return the list of labels
     */
    private List<Label> createLabels(final List<String> paragraphs) {
        List<Label> labelList = new ArrayList<>();
        for (String paragraph : paragraphs) {
            Label paragraphLabel = new Label(paragraph);
            Font font = Font.loadFont("file:resources/Fonts/PressStart2P-Regular.ttf", 16);
            paragraphLabel.setFont(font);
            paragraphLabel.setWrapText(true);
            paragraphLabel.setOpacity(0);
            labelList.add(paragraphLabel);
        }
        return labelList;
    }

    /**
     * Sets up the text pane.
     *
     * @param labelList the list of labels
     * @param next the next button
     * @return the text pane as a VBox
     */
    private VBox setupTextPane(final List<Label> labelList, final Button next) {
        VBox text = new VBox();
        text.setSpacing(10);

        for (Label label : labelList) {
            text.getChildren().add(label);
        }

        text.getChildren().add(next);
        text.setPadding(new javafx.geometry.Insets(20, 50, 20, 50));
        VBox.setMargin(next, new javafx.geometry.Insets(0, 0, 0, 480));

        return text;
    }

    /**
     * Creates a list of fade transitions for the labels.
     *
     * @param labelList the list of labels
     * @return the list of fade transitions
     */
    private List<FadeTransition> createLabelFadeTransitions(final List<Label> labelList) {
        List<FadeTransition> labelFadeTransitions = new ArrayList<>();
        for (Label label : labelList) {
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.5), label);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.setDelay(Duration.seconds(0.5));
            labelFadeTransitions.add(fadeTransition);
        }
        return labelFadeTransitions;
    }

    /**
     * Creates a fade transition for the button.
     *
     * @param next the next button
     * @return the fade transition
     */
    private FadeTransition createButtonFadeTransition(final Button next) {
        FadeTransition buttonFadeTransition = new FadeTransition(Duration.seconds(1), next);
        buttonFadeTransition.setFromValue(0);
        buttonFadeTransition.setToValue(1);
        return buttonFadeTransition;
    }

    /**
     * Gets the paragraphs.
     *
     * @return the paragraphs
     */
    private List<String> getParagraphs() {
        List<String> paragraphs = new ArrayList<>();

        paragraphs.add("The wall of water towers over you. It hangs still, but you know it's barrelling towards you with its crushing weight. There is no escaping it as it engulfs the world, swallows the sky, and devours the sun.");

        paragraphs.add("You wake up wet and tangled, thinking you've ended up at the briny bottom amongst the seaweeds. You come to realize it was just the bed sheets and your cold sweat. It couldn't have been real, could it?! You are sure that with time, it will fade away like a bad dream.");

        paragraphs.add("No! It WAS real! The premonition does not fade, and your conviction has only steeled that it was a future yet to pass. There's no way to convince anyone else of the truth. They will think you have gone MAD! You must save yourself and get off of this island.");

        paragraphs.add("You have 10 days before The Wave comes and takes your small island village. You must sell enough fish to make enough money to buy a boat, to get off of this doom island.");
        return paragraphs;
    }

    /**
     * Goes to the next screen.
     *
     * @param event the event
     */
    private void nextScreen(final ActionEvent event) {
        VillageDisplay.fade(event);
    }
}
