package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ViewRod extends Application {
    @Override
    public void start(Stage primaryStage) {

        RodDisplay rodDisplay = new RodDisplay(primaryStage);
        HBox mainBox = rodDisplay.getRodDisplayHBox();

        Image docks = new Image("file:../../resources/Docks.png");
        ImageView docksView = new ImageView(docks);

        Region rectangle = new Region();
        rectangle.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        rectangle.setPrefSize(1200, 648);

        StackPane innerStackPane = new StackPane(mainBox);
        innerStackPane.setTranslateY(100);

        Image backButton = new Image("file:../../resources/backButton.png");
        ImageView backButtonView = new ImageView(backButton);
        backButtonView.setFitWidth(75);
        backButtonView.setFitHeight(75);
        backButtonView.setOnMouseClicked(this::back);

        StackPane.setAlignment(backButtonView, Pos.TOP_LEFT);

        StackPane stackPane = new StackPane(docksView, innerStackPane, backButtonView);

        Scene scene = new Scene(stackPane, 1200, 648);

        primaryStage.setScene(scene);

        primaryStage.setTitle("Fishing Rod Components");

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), stackPane);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();

        primaryStage.show();
    }

    public void back(final MouseEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500));

        fadeTransition.setNode(((Node) event.getSource()).getScene().getRoot());

        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        fadeTransition.setOnFinished(e -> {
            InitialFishingScreen fishDisplay = new InitialFishingScreen();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            fishDisplay.start(currentStage);
        });

        fadeTransition.play();
    }
}
