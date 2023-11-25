package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ButtonMaker {

    public static Button createButton(String label, EventHandler<ActionEvent> eventHandler, double xPosition, double yPosition) {
        Button button = new Button(label);

        button.setPrefWidth(200);
        button.setPrefHeight(75);
        button.setOpacity(0.5);

        button.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-background-color: lightblue; -fx-border-radius: 10px; -fx-font-size: 15px; -fx-text-fill: black; -fx-font-weight: bold;");

        button.setLayoutX(xPosition);
        button.setLayoutY(yPosition);

        button.setOnAction(eventHandler);

        return button;
    }
}
