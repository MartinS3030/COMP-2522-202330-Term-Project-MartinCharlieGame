package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Demonstrates the use of Image and ImageView objects.
 *
 * @author Lewis & Loftus 9e
 * @author BCIT
 * @version 2022
 */
public class IntialFishingScreen extends Application {

    /**
     * Displays an image centered in a window.
     *
     * @param primaryStage a Stage
     */
    public void start(final Stage primaryStage) {

        // Instantiate an image...
        Image image = new Image("file:../../resources/Ocean.png");

        // ...and immediately wrap it in a view
        ImageView imageView = new ImageView(image);

        // Instantiate a button object
        Button push = new Button("Cast");

        push.setPrefWidth(150); // Set your desired width
        push.setPrefHeight(75
        ); // Set your desired height

        push.setOpacity(0.5);

        push.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-background-color: lightblue; -fx-border-radius: 10px");

        // Determine what happens when we press the button
        push.setOnAction(this::processButtonPress);

        // Declare and initialize locals when we need them
        // Make sure they are final
        final int viewX = 0;
        final int viewY = 0;
        final int viewWidth = 1200;
        final int viewHeight = 648;

        // Set the size of the ImageView using a Rectangle2D
        imageView.setViewport(new Rectangle2D(viewX, viewY, viewWidth, viewHeight));

        StackPane root = new StackPane();
        root.getChildren().addAll(imageView, push);

        // Add the Group to a Scene
        final int appWidth = 1200;
        final int appHeight = 648;
        Scene scene = new Scene(root, appWidth, appHeight);

        // Add the Scene to the Stage and display it.
        primaryStage.setTitle("CastAway");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void processButtonPress(final ActionEvent event) {
        System.out.println("Hello World!");
    }
}
