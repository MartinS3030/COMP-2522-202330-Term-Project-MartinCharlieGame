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

import java.util.ArrayList;
import java.util.HashMap;

public class ActiveQuestModal implements ModalPopUp{

    private static final int MAX_QUESTS = 5;
    public ActiveQuestModal() {
    }

    public void openInGamePopup(Stage primaryStage) {
        Player player = Player.getInstance("Charlie");
        ArrayList<Quest> activeQuests =  player.getQuests();

        Popup popup = new Popup();

        VBox vbox = getMainVBox(activeQuests, popup);

        StackPane stackPane = createStackPane();
        stackPane.getChildren().add(vbox);

        popup.getContent().add(stackPane);

        // Set autoFix property to true
        popup.setAutoFix(true);

        // Add a listener to recalculate the position when the scene is resized
        primaryStage.getScene().widthProperty().addListener((obs, oldVal, newVal) -> centerPopup(primaryStage, popup));
        primaryStage.getScene().heightProperty().addListener((obs, oldVal, newVal) -> centerPopup(primaryStage, popup));

        // Show the popup
        popup.show(primaryStage);
    }

    private void centerPopup(Stage primaryStage, Popup popup) {
        // Calculate the center position of the primary stage
        double centerX = primaryStage.getX() + primaryStage.getWidth() / 2 - popup.getWidth() / 2;
        double centerY = primaryStage.getY() + primaryStage.getHeight() / 2 - popup.getHeight() / 2;

        // Set the position of the popup to the center of the primary stage
        popup.setX(centerX);
        popup.setY(centerY);
    }

    private VBox getMainVBox(ArrayList<Quest> activeQuests, Popup popup) {
        // Create VBox
        VBox vbox = new VBox(10); // Set spacing between children
        vbox.setStyle("-fx-padding: 8px;");

        HBox labelHBox = getLabelHBox();

        vbox.getChildren().add(labelHBox);

        for (Quest quest : activeQuests) {
            HBox questHBox;
            questHBox = createFilledQuestHBox(quest);
            vbox.getChildren().addAll(questHBox);
        }

        for (int i = activeQuests.size(); i < MAX_QUESTS; i++) {
            HBox questHBox;
            questHBox = createEmptyQuestHBox();
            vbox.getChildren().addAll(questHBox);
        }

        HBox closeHBox = getButtonHBox(popup);

        vbox.getChildren().addAll(closeHBox);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    private HBox getButtonHBox(Popup popup) {
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> popup.hide());
        closeButton.setStyle("-fx-background-color: rgb(28, 55, 201);" +
                             "-fx-font-family: 'Montserrat';-fx-font-size: 20px;-fx-font-weight: 700;" +
                             "-fx-padding: 5px;-fx-text-fill: rgb(29, 41, 41);-fx-border-width: 2px" +
                             ";-fx-border-color: black;");
        HBox closeHBox = new HBox(closeButton);
        closeHBox.setAlignment(Pos.CENTER);
        closeHBox.setStyle("-fx-padding: 10px;");
        return closeHBox;
    }

    private HBox getLabelHBox() {
        Label label = new Label("Active Quests");
        label.setStyle("-fx-font-family: 'Oswald';-fx-font-size: 40px;" +
                       "-fx-font-weight: 900;-fx-font-style: italic;" +
                       "-fx-text-fill: rgb(29, 41, 41);");
        HBox labelHBox = new HBox(label);
        labelHBox.setMaxWidth(384);
        labelHBox.setPrefHeight(100);
        labelHBox.setStyle(
                "-fx-background-color: rgb(28, 55, 201);" + // Background color
                "-fx-padding: 20px;"               // Padding inside the card
        );
        return labelHBox;
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
        hBox.setStyle(
                "-fx-background-color: rgb(123, 160, 165);" + // Background color
                        "-fx-background-radius: 10px;" + // Rounded corners
                        "-fx-padding: 20px;"               // Padding inside the card
        );

        // Add some content to the HBox (you can customize this part)
        Label label = new Label("Empty");
        label.setStyle("-fx-font-family: 'Oswald';-fx-font-size: 40px;-fx-font-weight: 900;-fx-text-fill: rgb(84, 97, 100);");
        hBox.getChildren().add(label);
        hBox.setAlignment(Pos.CENTER);

        return hBox;
    }


    private HBox createFilledQuestHBox(Quest quest) {
        // Create an HBox
        HBox hBox = createQuestHBox();

        VBox questLeftVBox = getQuestObjectiveVBox(quest);

        ImageView MoneyView = getImageView("file:../../resources/Items/Money.png", 60, 60);
        Label reward = new Label(Integer.toString(quest.getReward()));
        reward.setStyle("-fx-font-family: 'Montserrat';-fx-font-size: 15px;-fx-font-weight: 700;");
        hBox.getChildren().addAll(questLeftVBox, MoneyView, reward);
        hBox.setAlignment(Pos.CENTER);

        return hBox;
    }

    private VBox getQuestObjectiveVBox(Quest quest) {
        VBox questLeftVBox = new VBox();
        questLeftVBox.setPrefWidth(350);

        ImageView acceptImageView = getImageView("file:../../resources/Accept.png", 50, 50);
        ImageView cancelImageView = getImageView("file:../../resources/Cancel.png", 50, 50);

        Label questNameLabel = new Label(quest.getTitle());
        questNameLabel.setStyle("-fx-font-family: 'Montserrat';-fx-font-size: 20px;-fx-font-weight: 700; -fx-text-transform: uppercase;");

        Fish fish = quest.getObjective();
        ImageView fishImageView = getImageView("file:../../resources/Fish/" + fish.getName() + ".png", 50, 50);

        Label outOf = new Label(" /" + quest.getObjectiveAmount());
        outOf.setStyle("-fx-font-family: 'Montserrat';-fx-font-size: 25px;-fx-font-weight: 700;");

        HBox requirementHBox = new HBox(fishImageView, outOf, acceptImageView, cancelImageView);
        requirementHBox.setAlignment(Pos.CENTER_LEFT);

        questLeftVBox.getChildren().addAll(questNameLabel, requirementHBox);
        return questLeftVBox;
    }

    private ImageView getImageView(String filepath, int height, int width) {
        Image image = new Image(filepath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }

    public boolean checkQuestRequirements(Quest quest) {
        Player player = Player.getInstance("Charlie");
        HashMap<Item, Integer> inventory = player.getInventory();
        if (inventory.get(quest.getObjective().getName()) >= quest.getObjectiveAmount()) {
            return true;
        } else {
            return false;
        }
    }
}
