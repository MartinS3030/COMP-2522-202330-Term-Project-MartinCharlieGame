package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class EndScreen extends Application {
    public EndScreen() {
    }
    public void start(Stage primaryStage) {
        ImageView imageView = getImageView();

        // Create the root layout and add the ImageView
        StackPane root = new StackPane();
        root.getChildren().addAll(imageView, getEndMessage());

        // Create the scene
        Scene scene = new Scene(root, 1200, 648);

        // Bind the fitWidth and fitHeight properties to the scene's width and height
        imageView.fitWidthProperty().bind(scene.widthProperty());
        imageView.fitHeightProperty().bind(scene.heightProperty());

        // Set up the stage
        primaryStage.setTitle("CastAway");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    protected Label getEndMessage() {
        Label endMessage = new Label("The End!");
        endMessage.setStyle("-fx-font-size: 100px; -fx-text-fill: white;");
        return endMessage;
    }

    protected ImageView getImageView() {
        Image image = new Image("file:../../resources/LoseScreen.png");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    public static void main(final String[] args) {
        launch(args);
    }

}
