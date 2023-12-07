package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import javafx.util.Duration;

public class VillageDisplay extends Application {
    @Override
    public void start(Stage primaryStage) {
        Image village = new Image("file:../../resources/village.png");
        ImageView villageView = new ImageView(village);
        villageView.setFitHeight(648);
        villageView.setFitWidth(1200);

        Button bulletinBoard = ButtonMaker.createButton("Bulletin Board", this::showBulletinBoard, 0, 0);
        Button shop = ButtonMaker.createButton("Shop", this::showShop, 0, 0);
        Button viewQuests = ButtonMaker.createButton("View Quests", this::showBulletinBoard, 0, 0);
        Button viewInventory = ButtonMaker.createButton("View Inventory", this::showBulletinBoard, 0, 0);
        Button sleep = ButtonMaker.createButton("Sleep", this::showSleep, 0, 0);
        Button buyBoat = ButtonMaker.createButton("Buy Boat", this::showBulletinBoard, 0, 0);
        Button save = ButtonMaker.createButton("Save", this::showSave, 0, 0);

        VBox buttonBox = new VBox(bulletinBoard, shop, viewQuests, viewInventory, sleep, buyBoat, save);
        buttonBox.setAlignment(javafx.geometry.Pos.CENTER);
        buttonBox.setSpacing(10);

        StackPane root = new StackPane(villageView, buttonBox);
        Scene scene = new Scene(root, 1200, 648);
        primaryStage.setTitle("Village");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void showBulletinBoard(ActionEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500));

        fadeTransition.setNode(((Node) event.getSource()).getScene().getRoot());

        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        fadeTransition.setOnFinished(e -> {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            BoardDisplay boardDisplay = new BoardDisplay();
            boardDisplay.start(currentStage);
        });

        fadeTransition.play();
    }

    public void showShop(ActionEvent event) {
        System.out.println("Shop");
    }

    public void showSleep(ActionEvent event) {
        System.out.println("Sleep");
    }

    public void showSave(ActionEvent event) {
        System.out.println("Save");
    }
}
