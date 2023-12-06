package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Font;

public class ButtonMaker {

    public static Button createButton(String label, EventHandler<ActionEvent> eventHandler, double xPosition, double yPosition) {
        Button button = new Button(label);

        button.setPrefWidth(200);
        button.setPrefHeight(75);
        button.setOpacity(0.7);
        Font font = Font.loadFont("file:resources/Fonts/PressStart2P-Regular.ttf", 10);
        button.setFont(font);
        button.setWrapText(true);
        button.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-background-color: lightblue; -fx-border-radius: 10px;");

        button.setLayoutX(xPosition);
        button.setLayoutY(yPosition);

        button.setOnAction(eventHandler);

        return button;
    }
}
