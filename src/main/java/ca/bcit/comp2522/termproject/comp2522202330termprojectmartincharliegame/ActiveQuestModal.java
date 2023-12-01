package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class ActiveQuestModal implements ModalPopUp{
    public ActiveQuestModal() {
    }
    public void openInGamePopup(Stage primaryStage) {
        ActiveQuests activeQuests = ActiveQuests.getInstance();
        activeQuests.generateQuests();

        Popup popup = new Popup();

        // Create VBox
        VBox vbox = new VBox(10); // Set spacing between children
        vbox.setStyle("-fx-padding: 10px;");

        for (Quest quest : activeQuests.getActiveQuests()) {
            HBox questHBox;
            if (quest != null) {
                questHBox = createFilledQuestHBox(quest);
            } else {
                questHBox = createEmptyQuestHBox();
            }
            vbox.getChildren().addAll(questHBox);
        }


        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> popup.hide());

        vbox.getChildren().addAll(closeButton);
        vbox.setAlignment(Pos.CENTER);

        StackPane stackPane = createStackPane();
        stackPane.getChildren().add(vbox);

        popup.getContent().add(stackPane);

        // Calculate the center position of the primary stage
        double centerX = primaryStage.getX() + primaryStage.getWidth() / 2 - stackPane.getWidth() / 2;
        double centerY = primaryStage.getY() + primaryStage.getHeight() / 2 - stackPane.getHeight() / 2;

        // Set the position of the popup to the center of the primary stage
        popup.show(primaryStage, centerX, centerY);
    }

    private StackPane createStackPane() {
        VBox backgroundCard = new VBox();
        backgroundCard.setStyle(
                "-fx-background-color: rgb(183, 233, 255);" + // Background color
                        "-fx-border-color: black;" +    // Border color
                        "-fx-border-width: 8px;" +      // Border width
                        "-fx-padding: 20px;"               // Padding inside the card
        );
        // Create a StackPane
        StackPane stackPane = new StackPane(backgroundCard);
        stackPane.setPrefWidth(400);

        return stackPane;
    }

    private HBox createQuestHBox() {
        // Create an HBox
        HBox hBox = new HBox();
        hBox.setMaxWidth(350);
        hBox.setPrefHeight(100);
        hBox.setStyle(
                "-fx-background-color: rgb(10, 116, 127);" + // Background color
                "-fx-background-radius: 10px;" + // Rounded corners
                "-fx-padding: 20px;"               // Padding inside the card
        );

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
