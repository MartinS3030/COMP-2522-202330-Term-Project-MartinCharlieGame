package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller for the hello view.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}