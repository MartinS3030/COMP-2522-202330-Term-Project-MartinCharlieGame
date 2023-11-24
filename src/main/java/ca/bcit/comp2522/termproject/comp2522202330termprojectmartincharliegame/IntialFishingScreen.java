package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

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

        Image image = new Image("file:../../resources/Ocean.png");

        ImageView imageView = new ImageView(image);

        Button cast = ButtonMaker.createButton("Cast", this::castReel);
        Button viewRod = ButtonMaker.createButton("View Rod", this::viewRod);
        Button viewInventory = ButtonMaker.createButton("View Inventory", this::viewInventory);
        Button viewQuests = ButtonMaker.createButton("View Quests", this::viewQuests);
        Button endDay = ButtonMaker.createButton("End Day", this::endDay);

        final int viewX = 0;
        final int viewY = 0;
        final int viewWidth = 1200;
        final int viewHeight = 648;

        imageView.setViewport(new Rectangle2D(viewX, viewY, viewWidth, viewHeight));

        StackPane root = new StackPane();
        root.getChildren().addAll(imageView, cast);

        final int appWidth = 1200;
        final int appHeight = 648;
        Scene scene = new Scene(root, appWidth, appHeight);

        primaryStage.setTitle("CastAway");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void castReel(final ActionEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500));

        fadeTransition.setNode(((Node) event.getSource()).getScene().getRoot());

        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        fadeTransition.setOnFinished(e -> {
            FishDisplay fishDisplay = new FishDisplay();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            fishDisplay.start(currentStage);
        });

        fadeTransition.play();
    }

    public void viewRod(final ActionEvent event) {
        System.out.println("View Rod");
    }

    public void viewInventory(final ActionEvent event) {
        System.out.println("View Inventory");
    }

    public void viewQuests(final ActionEvent event) {
        System.out.println("View Quests");
    }

    public void endDay(final ActionEvent event) {
        System.out.println("End Day");
    }
}
