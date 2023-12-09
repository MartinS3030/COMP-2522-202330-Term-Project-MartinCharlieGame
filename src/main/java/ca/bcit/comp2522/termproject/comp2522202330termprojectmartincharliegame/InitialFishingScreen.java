package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

/**
 * Displays the initial fishing screen.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public class InitialFishingScreen extends Application {
    /**
     * The width of the stage.
     */
    public static final int STAGE_WIDTH = 1200;
    /**
     * The height of the stage.
     */
    public static final int STAGE_HEIGHT = 648;
    /**
     * Starts the initial fishing screen.
     *
     * @param primaryStage the stage
     */
    public void start(final Stage primaryStage) {

        Image image = new Image("file:../../resources/Ocean.png");

        ImageView imageView = new ImageView(image);

        Button cast = ButtonMaker.createButton("Cast", this::castReel, 0, 0);
        Button viewRod = ButtonMaker.createButton("View Rod", this::viewRod, 0, 0);
        Button viewInventory = ButtonMaker.createButton("View Inventory", this::viewInventory, 0, 0);
        Button viewQuests = ButtonMaker.createButton("View Quests", this::viewQuests, 0, 0);
        Button endDay = ButtonMaker.createButton("End Day", this::endDay, 0, 0);

        VBox buttonBox = new VBox(cast, viewRod, viewInventory, viewQuests, endDay);
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        buttonBox.setSpacing(2);

        buttonBox.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-padding: 10px;");

        VBox infoBox = getInfoBox();

        final int viewX = 0;
        final int viewY = 0;

        imageView.setViewport(new Rectangle2D(viewX, viewY, STAGE_WIDTH, STAGE_HEIGHT));

        StackPane root = new StackPane();
        root.getChildren().addAll(imageView, infoBox, buttonBox);

        final int appWidth = 1200;
        final int appHeight = 648;
        Scene scene = new Scene(root, appWidth, appHeight);

        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(scene.heightProperty());

        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.getScene().widthProperty().addListener((obs, oldVal, newVal) -> centerInfoBox(infoBox,
                primaryStage, buttonBox));
        primaryStage.getScene().heightProperty().addListener((obs, oldVal, newVal) -> centerInfoBox(infoBox,
                primaryStage, buttonBox));
    }

    /**
     * Gets the info box.
     *
     * @return the info box as a VBox
     */
    private VBox getInfoBox() {
        VBox infoBox = new VBox();
        Player player = Player.getInstance("Charlie");
        int daysLeft = GameDriver.getTimeLimit() - player.getDate();

        Label daysLeftLabel = new Label(String.format("Days Left: %d", daysLeft));
        daysLeftLabel.setStyle("-fx-font-family: 'Oswald';-fx-font-size: 40px;"
                + "-fx-font-weight: 900;-fx-font-style: italic;"
                + "-fx-text-fill: rgb(231, 54, 70);");
        daysLeftLabel.setEffect(new DropShadow(15, Color.BLACK));

        Label castsLeftLabel = new Label(String.format("Casts Left: %d", player.getCastLeft()));
        castsLeftLabel.setStyle("-fx-font-family: 'Oswald';-fx-font-size: 40px;"
                + "-fx-font-weight: 900;-fx-font-style: italic;"
                + "-fx-text-fill: rgb(231, 54, 70);");
        castsLeftLabel.setEffect(new DropShadow(15, Color.BLACK));

        Label moneyLabel = new Label(String.format("Current Gold: %d / %d", player.getMoney(),
                GameDriver.getMoneyGoal()));
        moneyLabel.setStyle("-fx-font-family: 'Oswald';-fx-font-size: 40px;"
                + "-fx-font-weight: 900;-fx-font-style: italic;"
                + "-fx-text-fill: rgb(231, 54, 70);");
        moneyLabel.setEffect(new DropShadow(15, Color.BLACK));

        infoBox.getChildren().addAll(daysLeftLabel, castsLeftLabel, moneyLabel);
        infoBox.setSpacing(10);
        infoBox.setAlignment(Pos.TOP_CENTER);

        return infoBox;
    }

    /**
     * Centers the info box.
     *
     * @param infoBox     the info box
     * @param primaryStage the stage
     * @param buttonBox   the button box
     */
    private void centerInfoBox(final VBox infoBox, final Stage primaryStage, final VBox buttonBox) {
        double centerX = primaryStage.getX() + primaryStage.getWidth() / 2 - infoBox.getWidth() / 2
                + buttonBox.getWidth();
        double centerY = primaryStage.getY() + primaryStage.getHeight() / 2 - infoBox.getHeight() / 2;

        infoBox.setLayoutX(centerX);
        infoBox.setLayoutY(centerY);
    }

    /**
     * Transitions to the fish display.
     *
     * @param event the event
     */
    public void castReel(final ActionEvent event) {
        if (Player.getInstance("Charlie").getCastOfTheDay() >= 5) {
            return;
        }
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

    /**
     * Transitions to the rod display.
     *
     * @param event the event
     */
    public void viewRod(final ActionEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500));

        fadeTransition.setNode(((Node) event.getSource()).getScene().getRoot());

        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        fadeTransition.setOnFinished(e -> {
            ViewRod viewRod = new ViewRod();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            viewRod.start(currentStage);
        });

        fadeTransition.play();
    }

    /**
     * Transitions to the inventory display.
     *
     * @param event the event
     */
    public void viewInventory(final ActionEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500));

        fadeTransition.setNode(((Node) event.getSource()).getScene().getRoot());

        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        fadeTransition.setOnFinished(e -> {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            InventoryDisplay inventoryDisplay = new InventoryDisplay();
            inventoryDisplay.start(currentStage);
        });

        fadeTransition.play();
    }

    /**
     * Opens the active quests display modal.
     *
     * @param event the event
     */
    public void viewQuests(final ActionEvent event) {
        ModalPopUp modalPopUp = new ActiveQuestModal();
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        modalPopUp.openInGamePopup(currentStage);
    }

    /**
     * Ends the day.
     *
     * @param event the event
     */
    public void endDay(final ActionEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500));

        fadeTransition.setNode(((Node) event.getSource()).getScene().getRoot());

        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        fadeTransition.setOnFinished(e -> {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            VillageDisplay villageDisplay = new VillageDisplay();
            villageDisplay.start(currentStage);
        });

        fadeTransition.play();
    }
}
