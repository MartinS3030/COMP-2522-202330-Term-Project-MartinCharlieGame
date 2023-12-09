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

/**
 * A modal that displays the player's active quests.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public class ActiveQuestModal implements ModalPopUp {
    private static final int MAX_QUESTS = 5;
    private Stage primaryStage;
    private final Player player = Player.getInstance("Charlie");
    private final Popup popup = new Popup();
    /**
     * Creates a new ActiveQuestModal.
     */
    public ActiveQuestModal() {
    }

    /**
     * Opens the modal.
     *
     * @param stage the stage
     */
    public void openInGamePopup(final Stage stage) {
        this.primaryStage = stage;
        displayUI();
    }

    /**
     * Displays the UI.
     */
    private void displayUI() {
        ArrayList<Quest> activeQuests =  player.getQuests();

        VBox vbox = getMainVBox(activeQuests, popup);

        StackPane stackPane = createStackPane();
        stackPane.getChildren().add(vbox);

        popup.getContent().clear();
        popup.getContent().add(stackPane);

        popup.setAutoFix(true);

        primaryStage.getScene().widthProperty().addListener((obs, oldVal, newVal) -> centerPopup(primaryStage, popup));
        primaryStage.getScene().heightProperty().addListener((obs, oldVal, newVal) -> centerPopup(primaryStage, popup));

        popup.show(primaryStage);
    }

    /**
     * Centers the popUp.
     *
     * @param stage the stage
     * @param popUp the popUp
     */
    private void centerPopup(final Stage stage, final Popup popUp) {
        double centerX = stage.getX() + stage.getWidth() / 2 - popUp.getWidth() / 2;
        double centerY = stage.getY() + stage.getHeight() / 2 - popUp.getHeight() / 2;

        popUp.setX(centerX);
        popUp.setY(centerY);
    }

    /**
     * Gets the main VBox.
     *
     * @param activeQuests the active quests
     * @param popUp        the popUp
     * @return the main VBox
     */
    private VBox getMainVBox(final ArrayList<Quest> activeQuests, final Popup popUp) {
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

        HBox closeHBox = getButtonHBox(popUp);

        vbox.getChildren().addAll(closeHBox);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    /**
     * Gets the button HBox.
     *
     * @param popUp the popUp
     * @return the button HBox
     */
    private HBox getButtonHBox(final Popup popUp) {
        Button closeButton = new Button("Close");
        closeButton.setOnMouseClicked(e -> popUp.hide());
        closeButton.setStyle("-fx-background-color: rgb(28, 55, 201);"
                    + "-fx-font-family: 'Montserrat';-fx-font-size: 20px;-fx-font-weight: 700;"
                    + "-fx-padding: 5px;-fx-text-fill: rgb(29, 41, 41);-fx-border-width: 2px"
                    + ";-fx-border-color: black;");
        HBox closeHBox = new HBox(closeButton);
        closeHBox.setAlignment(Pos.CENTER);
        closeHBox.setStyle("-fx-padding: 10px;");
        return closeHBox;
    }

    /**
     * Gets the label HBox.
     *
     * @return the label HBox
     */
    private HBox getLabelHBox() {
        Label label = new Label("Active Quests");
        label.setStyle("-fx-font-family: 'Oswald';-fx-font-size: 40px;"
                    + "-fx-font-weight: 900;-fx-font-style: italic;"
                    + "-fx-text-fill: rgb(29, 41, 41);");
        HBox labelHBox = new HBox(label);
        labelHBox.setMaxWidth(384);
        labelHBox.setPrefHeight(100);
        labelHBox.setStyle(
                "-fx-background-color: rgb(28, 55, 201); -fx-padding: 20px;"
        );
        return labelHBox;
    }

    /**
     * Creates a StackPane.
     *
     * @return the StackPane
     */
    private StackPane createStackPane() {
        VBox backgroundCard = new VBox();
        backgroundCard.setStyle(
                "-fx-background-color: rgb(183, 233, 255); -fx-border-color: black; -fx-border-width: 8px;"
                + "-fx-padding: 20px;"
        );

        StackPane stackPane = new StackPane(backgroundCard);
        stackPane.setPrefWidth(400);

        return stackPane;
    }

    /**
     * Creates a quest HBox.
     *
     * @return the quest HBox
     */
    private HBox createQuestHBox() {
        // Create an HBox
        HBox hBox = new HBox();
        hBox.setMaxWidth(350);
        hBox.setPrefHeight(100);
        hBox.setStyle(
                "-fx-background-color: rgb(10, 116, 127); -fx-background-radius: 10px; -fx-padding: 20px;"
        );

        return hBox;
    }

    /**
     * Creates an empty quest HBox.
     *
     * @return the empty quest HBox
     */
    private HBox createEmptyQuestHBox() {
        // Create an HBox
        HBox hBox = createQuestHBox();
        hBox.setStyle(
                "-fx-background-color: rgb(123, 160, 165); -fx-background-radius: 10px; -fx-padding: 20px;"
        );

        Label label = new Label("Empty");
        label.setStyle("-fx-font-family: 'Oswald';-fx-font-size: 40px;-fx-font-weight: 900; "
                + "-fx-text-fill: rgb(84, 97, 100);");
        hBox.getChildren().add(label);
        hBox.setAlignment(Pos.CENTER);

        return hBox;
    }

    /**
     * Creates a filled quest HBox.
     *
     * @param quest the quest
     * @return the filled quest HBox
     */
    private HBox createFilledQuestHBox(final Quest quest) {
        // Create an HBox
        HBox hBox = createQuestHBox();

        VBox questLeftVBox = getQuestObjectiveVBox(quest);

        ImageView moneyView = getImageView("file:../../resources/Items/Money.png", 60, 60);
        Label reward = new Label(Integer.toString(quest.getReward()));
        reward.setStyle("-fx-font-family: 'Montserrat';-fx-font-size: 15px;-fx-font-weight: 700;");
        hBox.getChildren().addAll(questLeftVBox, moneyView, reward);
        hBox.setAlignment(Pos.CENTER);

        return hBox;
    }

    /**
     * Gets the quest objective VBox.
     *
     * @param quest the quest
     * @return the quest objective VBox
     */
    private VBox getQuestObjectiveVBox(final Quest quest) {
        VBox questLeftVBox = new VBox();
        questLeftVBox.setPrefWidth(350);

        ImageView acceptImageView = getImageView("file:../../resources/Accept.png", 50, 50);
        ImageView cancelImageView = getImageView("file:../../resources/Cancel.png", 50, 50);

        acceptImageView.setOnMouseClicked(event -> {
            if (checkQuestRequirements(quest)) {
                player.setMoney(player.getMoney() + quest.getReward());
                player.removeQuest(quest);
                displayUI();
            }
        });

        cancelImageView.setOnMouseClicked(event -> {
            BulletinBoard bulletinBoard = BulletinBoard.getInstance();
            player.removeQuest(quest);
            bulletinBoard.addQuest(quest);
            displayUI();
        });

        Label questNameLabel = new Label(quest.getTitle());
        questNameLabel.setStyle("-fx-font-family: 'Montserrat';-fx-font-size: 20px;-fx-font-weight: 700;"
                + " -fx-text-transform: uppercase;");

        Item fish = quest.getObjective();
        ImageView fishImageView = getImageView("file:../../resources/Fish/" + fish.getName() + ".png", 50, 50);

        int fishAmount = 0;
        if (player.getInventory().containsKey(fish.getName())) {
            fishAmount = player.getInventory().get(fish.getName()).getAmount();
        }
        Label outOf = new Label(" " + fishAmount + "/" + quest.getObjectiveAmount());
        outOf.setStyle("-fx-font-family: 'Montserrat';-fx-font-size: 25px;-fx-font-weight: 700;");

        HBox requirementHBox = new HBox(fishImageView, outOf, acceptImageView, cancelImageView);
        requirementHBox.setAlignment(Pos.CENTER_LEFT);

        questLeftVBox.getChildren().addAll(questNameLabel, requirementHBox);
        return questLeftVBox;
    }

    /**
     * Gets an ImageView.
     *
     * @param filepath the filepath as a String
     * @param height   the height as an int
     * @param width    the width as an int
     * @return the ImageView
     */
    private ImageView getImageView(final String filepath, final int height, final int width) {
        Image image = new Image(filepath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }

    /**
     * Checks if the quest requirements are met.
     *
     * @param quest the quest
     * @return true if the quest requirements are met, false otherwise
     */
    public boolean checkQuestRequirements(final Quest quest) {
        HashMap<String, Item> inventory = player.getInventory();
        return inventory.get(quest.getObjective().getName()).getAmount() >= quest.getObjectiveAmount();
    }
}
