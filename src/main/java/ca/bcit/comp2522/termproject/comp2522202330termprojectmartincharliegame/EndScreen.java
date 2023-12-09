package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Represents the endgame screen.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public class EndScreen extends Application {
    /**
     * Constructs an EndScreen.
     */
    public EndScreen() {
    }

    /**
     * Starts the EndScreen.
     *
     * @param primaryStage the Stage
     */
    public void start(Stage primaryStage) {
        ImageView imageView = getImageView();

        // Create the root layout and add the ImageView
        StackPane root = new StackPane();
        VBox messageBox = new VBox(getEndMessage());
        messageBox.setMaxWidth(1000);
        messageBox.setAlignment(Pos.BOTTOM_CENTER);
        messageBox.setPadding(new javafx.geometry.Insets(0, 0, 100, 0));
        root.getChildren().addAll(imageView, messageBox);

        // Create the scene
        Scene scene = new Scene(root, 1200, 800);

        // Bind the fitWidth and fitHeight properties to the scene's width and height
        imageView.fitWidthProperty().bind(scene.widthProperty());
        imageView.fitHeightProperty().bind(scene.heightProperty());

        // Set up the stage
        primaryStage.setTitle("CastAway");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Returns the end message.
     *
     * @return the end message as a Label
     */
    protected Label getEndMessage() {
        Label endMessage = new Label("You waited for the end but it never came. Guess it was just a dream.");
        endMessage.setStyle("-fx-font-size: 50px; -fx-text-fill: white;");
        endMessage.setEffect(new javafx.scene.effect.DropShadow(10, Color.BLACK));
        endMessage.setWrapText(true);
        endMessage.setAlignment(Pos.BOTTOM_CENTER);
        return endMessage;
    }

    /**
     * Returns the background ImageView.
     *
     * @return the ImageView of the background
     */
    protected ImageView getImageView() {
        Image image = new Image("file:../../resources/LoseScreen.png");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    /**
     * Launches the EndScreen.
     *
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        launch(args);
    }

}
