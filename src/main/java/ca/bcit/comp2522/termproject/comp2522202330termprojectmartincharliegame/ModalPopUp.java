package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class ModalPopUp{
    public ModalPopUp() {
    }
//    private Stage primaryStage;
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        this.primaryStage = primaryStage;
//        primaryStage.setTitle("In-Game Popup Example");
//
//        Button openPopupButton = new Button("Open In-Game Popup");
//        openPopupButton.setOnAction(e -> openInGamePopup());
//
//        StackPane layout = new StackPane();
//        layout.getChildren().addAll(openPopupButton);
//        layout.setAlignment(Pos.CENTER);
//        layout.setPadding(new javafx.geometry.Insets(10));
//
//        Scene scene = new Scene(layout, 300, 200);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }

    public void openInGamePopup(Stage primaryStage) {
        ActiveQuests activeQuests = ActiveQuests.getInstance();
        activeQuests.generateQuests();

        Popup popup = new Popup();

        // Create a white VBox
        VBox vbox = new VBox(10); // Set spacing between children
        vbox.setStyle("-fx-background-color: white;"); // Set background color


        for (Quest quest : activeQuests.getActiveQuests()) {
            if (quest != null) {
                HBox questHBox = createFilledQuestHBox(quest);
                vbox.getChildren().addAll(questHBox);
            } else {
                HBox questHBox = createEmptyQuestHBox();
                vbox.getChildren().addAll(questHBox);
            }
        }


        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> popup.hide());

        vbox.getChildren().addAll(closeButton);

        popup.getContent().add(vbox);

        // Calculate the center position of the primary stage
        double centerX = primaryStage.getX() + primaryStage.getWidth() / 2 - vbox.getWidth() / 2;
        double centerY = primaryStage.getY() + primaryStage.getHeight() / 2 - vbox.getHeight() / 2;

        // Set the position of the popup to the center of the primary stage
        popup.show(primaryStage, centerX, centerY);
    }

    private HBox createQuestHBox() {
        // Create an HBox
        HBox hBox = new HBox();
        hBox.setPrefWidth(400);
        hBox.setPrefHeight(75);

        return hBox;
    }

    private HBox createEmptyQuestHBox() {
        // Create an HBox
        HBox hBox = createQuestHBox();

        // Add some content to the HBox (you can customize this part)
        Label label = new Label("Empty");
        hBox.getChildren().add(label);

        return hBox;
    }


    private HBox createFilledQuestHBox(Quest quest) {
        // Create an HBox
        HBox hBox = createQuestHBox();

        VBox questLeftVBox = new VBox();
        questLeftVBox.setPrefWidth(350);

        Label questNameLabel = new Label(quest.getTitle());
        questNameLabel.setStyle("-fx-font-family: 'Montserrat';-fx-font-size: 25px;-fx-font-weight: 700; -fx-text-transform: uppercase;");

        Fish fish = quest.getObjective();

        Image fishImage = new Image("file:../../resources/Fish/" + fish.getName() + ".png");
        ImageView fishImageView = new ImageView(fishImage);
        fishImageView.setFitWidth(50);
        fishImageView.setFitHeight(50);

        Label outOf = new Label(" / " + quest.getObjectiveAmount());
        outOf.setStyle("-fx-font-family: 'Montserrat';-fx-font-size: 25px;-fx-font-weight: 700;");

        HBox requirementHBox = new HBox(fishImageView, outOf);


        questLeftVBox.getChildren().addAll(questNameLabel, requirementHBox);

        Image MoneyImage = new Image("file:../../resources/Items/Money.png");
        ImageView MoneyView = new ImageView(MoneyImage);
        MoneyView.setFitWidth(50);
        MoneyView.setFitHeight(50);

        hBox.getChildren().addAll(questLeftVBox, MoneyView, new Label(Integer.toString(quest.getReward())));
        hBox.setAlignment(Pos.CENTER);

        return hBox;
    }
}
