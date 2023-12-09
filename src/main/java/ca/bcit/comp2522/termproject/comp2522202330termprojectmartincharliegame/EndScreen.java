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

public class EndScreen extends Application {
    public EndScreen() {
    }
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

    protected Label getEndMessage() {
        Label endMessage = new Label("You waited for the end but it never came. Guess it was just a dream.");
        endMessage.setStyle("-fx-font-size: 50px; -fx-text-fill: white;");
        endMessage.setEffect(new javafx.scene.effect.DropShadow(10, Color.BLACK));
        endMessage.setWrapText(true);
        endMessage.setAlignment(Pos.BOTTOM_CENTER);
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
