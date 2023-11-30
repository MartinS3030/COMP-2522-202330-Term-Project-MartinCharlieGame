package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class BoardDisplay extends Application {
    BulletinBoard bulletinBoard = BulletinBoard.getInstance();
    ArrayList<Quest> questList = bulletinBoard.getQuests();

    @Override
    public void start(Stage primaryStage) {
        StackPane questStack = generateQuestDetails(questList.get(0));
        StackPane bulletinBoardStack = getStackPane(questStack);

        HBox root = new HBox(bulletinBoardStack, questStack);

        Scene scene = new Scene(root, 1200, 648);
        primaryStage.setTitle("Quest Display");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private StackPane getStackPane(StackPane questStack) {
        Image bulletinBoard = new Image("file:../../resources/BulletinBoard.png");
        ImageView bulletinBoardView = new ImageView(bulletinBoard);

        Label requestTitle = new Label("Requests");
        Font font = Font.loadFont("file:resources/Fonts/CinzelDecorative-Bold.ttf", 30);
        requestTitle.setFont(font);
        requestTitle.setTranslateX(200);

        VBox allQuestView = new VBox(requestTitle);

        for (int i = 0; i < questList.size(); i++) {
            HBox questDetailsView = getHBox(questList.get(i), questStack);
            allQuestView.getChildren().add(questDetailsView);
        }

        allQuestView.setSpacing(0);

        StackPane bulletinBoardStack = new StackPane(bulletinBoardView, allQuestView);
        StackPane.setAlignment(allQuestView, Pos.TOP_CENTER);
        StackPane.setMargin(allQuestView, new Insets(15, 0, 0, 22));
        StackPane.setAlignment(bulletinBoardView, Pos.TOP_CENTER);
        return bulletinBoardStack;
    }

    private HBox getHBox(Quest quest, StackPane questStack) {
        Image fishImage = new Image("file:../../resources/Fish/" + quest.getObjective().getName() + ".png");
        ImageView fishImageView = new ImageView(fishImage);

        Label questTitleView = new Label(quest.getTitle());
        questTitleView.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-alignment: center");
        questTitleView.setMaxHeight(80);
        Label questGiverView = new Label("Requester: " + quest.getGiver());
        questGiverView.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-alignment: center");
        questGiverView.setMaxHeight(80);
        Label questDifficultyView = new Label("Difficulty: " + String.valueOf(quest.getDifficulty()));
        questDifficultyView.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-alignment: center");
        questDifficultyView.setMaxHeight(80);

        HBox hbox = new HBox(fishImageView, questTitleView, questGiverView, questDifficultyView);
        hbox.setSpacing(30);
        hbox.setMaxWidth(650);
        hbox.setMaxHeight(80);
//        hbox.setStyle("-fx-border-width: 5px; -fx-border-color: brown; -fx-border-style: solid");

        hbox.setOnMouseClicked(event -> {
            StackPane updatedQuestStack = generateQuestDetails(quest);
            questStack.getChildren().set(1, updatedQuestStack);
        });

        hbox.setOnMouseEntered(event -> hbox.getScene().setCursor(Cursor.HAND));
        hbox.setOnMouseExited(event -> hbox.getScene().setCursor(Cursor.DEFAULT));

        return hbox;
    }


    public Label generateLabel(String text, int height) {
        Font font = Font.loadFont("file:resources/Fonts/CinzelDecorative-Regular.ttf", 20);
        Label label = new Label(text);
        label.setFont(font);
        label.setPrefHeight(height);
        label.setPrefWidth(430);
        label.setWrapText(true);
//        label.setStyle("-fx-border-width: 2px; -fx-border-color: black; -fx-border-style: solid");
        return label;
    }

    public StackPane generateQuestDetails(Quest quest) {
        Label questTitle = generateLabel(quest.getTitle(), 80);
        Label questGiver = generateLabel("Requester: " + quest.getGiver(), 50);
        Label questDifficulty = generateLabel("Difficulty: " + String.valueOf(quest.getDifficulty()), 50);
        Label questDescription = generateLabel("Description:\n" + quest.getDescription(), 200);
        Label questReward = generateLabel("Reward: " + String.valueOf(quest.getReward()), 100);

        VBox questDetails = new VBox(questTitle, questGiver, questDifficulty, questDescription, questReward);
        questDetails.setSpacing(0);
        questDetails.setMaxWidth(430);
        questDetails.setMaxHeight(520);

        Image questBox = new Image("file:../../resources/QuestBox.png");
        ImageView questBoxView = new ImageView(questBox);
        questBoxView.setFitHeight(648);

        StackPane questStack = new StackPane();
        StackPane.setAlignment(questDetails, Pos.TOP_CENTER);
        questStack.setMaxWidth(501);
//        questStack.setStyle("-fx-border-style: solid; -fx-border-width: 5px; -fx-border-color: navy");
//        questStack.setTranslateX(350);
        StackPane.setMargin(questDetails, new Insets(12, 0, 0, 0));

        questStack.getChildren().addAll(questBoxView, questDetails);

        return questStack;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
