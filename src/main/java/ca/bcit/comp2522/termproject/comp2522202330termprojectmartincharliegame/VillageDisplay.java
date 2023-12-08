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

/**
 * Represents the village that the player can interact with.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public class VillageDisplay extends Application {
    /**
     * The width of the stage.
     */
    public static final int STAGE_WIDTH = 1200;
    /**
     * The height of the stage.
     */
    public static final int STAGE_HEIGHT = 648;
    private final Player player = Player.getInstance("Charlie");
    private Stage primaryStage;

    /**
     * Launches the stage.
     *
     * @param primaryStage the stage
     */
    @Override
    public void start(final Stage primaryStage) {
        this.primaryStage = primaryStage;
        Image village = new Image("file:../../resources/village.png");
        ImageView villageView = new ImageView(village);
        villageView.setFitHeight(STAGE_HEIGHT);
        villageView.setFitWidth(STAGE_WIDTH);

        Button bulletinBoard = ButtonMaker.createButton("Bulletin Board", this::showBulletinBoard, 0, 0);
        Button shop = ButtonMaker.createButton("Shop", this::showShop, 0, 0);
        Button viewQuests = ButtonMaker.createButton("View Quests", this::showBulletinBoard, 0, 0);
        Button viewInventory = ButtonMaker.createButton("View Inventory", this::showBulletinBoard, 0, 0);
        Button sleep = ButtonMaker.createButton("Sleep", this::sleep, 0, 0);
        Button buyBoat = ButtonMaker.createButton("Buy Boat", this::showBulletinBoard, 0, 0);
        Button save = ButtonMaker.createButton("Save", this::save, 0, 0);

        VBox buttonBox = new VBox(bulletinBoard, shop, viewQuests, viewInventory, sleep, buyBoat, save);
        buttonBox.setAlignment(javafx.geometry.Pos.CENTER);
        buttonBox.setSpacing(10);

        StackPane root = new StackPane(villageView, buttonBox);
        Scene scene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT);
        primaryStage.setTitle("Village");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Shows the bulletin board.
     *
     * @param event the event
     */
    public void showBulletinBoard(final ActionEvent event) {
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

    /**
     * Shows the shop.
     *
     * @param event the event
     */
    public void showShop(final ActionEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500));

        fadeTransition.setNode(((Node) event.getSource()).getScene().getRoot());

        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        fadeTransition.setOnFinished(e -> {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            ShopDisplay shopDisplay = new ShopDisplay();
            shopDisplay.start(currentStage);

        });

        fadeTransition.play();
    }

    /**
     * Transitions to the fishing screen of the next day.
     *
     * @param event the event
     */
    public void sleep(final ActionEvent event) {
            player.setDate(player.getDate());
            if (player.getDate() > GameDriver.getTimeLimit()) {
                GameDriver gameDriver = new GameDriver();
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                gameDriver.endGame(currentStage);
            } else {
                player.setCastOfTheDay(0);
                Shop.resetShop();
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                InitialFishingScreen initialFishingScreen = new InitialFishingScreen();
                initialFishingScreen.start(currentStage);
            }
        fade(event);
    }

    /**
     * Fades the screen.
     *
     * @param event the event
     */
    static void fade(final ActionEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500));

        fadeTransition.setNode(((Node) event.getSource()).getScene().getRoot());

        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        fadeTransition.setOnFinished(e -> {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            InitialFishingScreen initialFishingScreen = new InitialFishingScreen();
            initialFishingScreen.start(currentStage);
        });

        fadeTransition.play();
    }

    /**
     * Saves the game.
     *
     * @param event the event
     */
    public void save(final ActionEvent event) {
        BulletinBoard bulletinBoard = BulletinBoard.getInstance();

        player.serialize("file:../../resources/playerSave.txt");
        bulletinBoard.serialize("file:../../resources/bulletinBoardSave.txt");
    }
}
