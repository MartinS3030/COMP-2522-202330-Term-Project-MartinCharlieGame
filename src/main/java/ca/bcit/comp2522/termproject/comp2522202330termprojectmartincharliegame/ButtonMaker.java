package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Font;

/**
 * Creates buttons.
 * A class that creates buttons with a specified label, event handler, x position, and y position.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public final class ButtonMaker {
    /**
     * The width of the button as an int.
     */
    public static final int BUTTON_WIDTH = 200;
    /**
     * The height of the button as an int.
     */
    public static final int BUTTON_HEIGHT = 75;
    /**
     * The opacity of the button as a double.
     */
    public static final double BUTTON_OPACITY = 0.8;
    /**
     * The font size of the button as an int.
     */
    public static final int FONT_SIZE = 10;
    private ButtonMaker() {
    }

    /**
     * Creates a button.
     *
     * @param label the label of the button as a String
     * @param eventHandler the event handler of the button
     * @param xPosition the x position of the button as a double
     * @param yPosition the y position of the button as a double
     * @return the button as a Button object
     */
    public static Button createButton(final String label, final EventHandler<ActionEvent> eventHandler,
                                      final double xPosition, final double yPosition) {
        Button button = new Button(label);

        button.setPrefWidth(BUTTON_WIDTH);
        button.setPrefHeight(BUTTON_HEIGHT);
        button.setOpacity(BUTTON_OPACITY);
        Font font = Font.loadFont("file:resources/Fonts/PressStart2P-Regular.ttf", FONT_SIZE);
        button.setFont(font);
        button.setWrapText(true);
        button.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-background-color: lightblue;"
                + " -fx-border-radius: 10px; -fx-background-radius: 10px;");

        button.setAlignment(javafx.geometry.Pos.CENTER);

        button.setLayoutX(xPosition);
        button.setLayoutY(yPosition);

        button.setOnAction(eventHandler);

        return button;
    }
}
