package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Optional;

/**
 * Displays the start screen.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public class StartScreen extends Application {
    /**
     * The width of the stage.
     */
    public static final int STAGE_WIDTH = 1200;
    /**
     * The height of the stage.
     */
    public static final int STAGE_HEIGHT = 648;
    /**
     * The font size of the text.
     */
    public static final int FONT_SIZE = 30;

    /**
     * Starts the start screen.
     *
     * @param primaryStage the stage to display the start screen on
     */
    @Override
    public void start(final Stage primaryStage) {
        Image image = new Image("file:../../resources/StartScreen.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(STAGE_HEIGHT);
        imageView.setFitWidth(STAGE_WIDTH);

        Label startGame = generateLabel("Start Game", FONT_SIZE);
        Label loadGame = generateLabel("Load Game", FONT_SIZE);
        Label exitGame = generateLabel("Exit Game", FONT_SIZE);

        startGame.setOnMouseClicked(this::startGame);
        loadGame.setOnMouseClicked(this::loadGame);
        exitGame.setOnMouseClicked(event -> Platform.exit());

        VBox menu = new VBox(startGame, loadGame, exitGame);
        menu.setSpacing(50);
        menu.setAlignment(Pos.CENTER);
        menu.setMaxHeight(600);
        menu.setMaxWidth(600);

        StackPane root = new StackPane(imageView, menu);
        StackPane.setMargin(menu, new Insets(350, 0, 0, 0));

        final int appWidth = 1200;
        final int appHeight = 648;
        Scene scene = new Scene(root, appWidth, appHeight);

        primaryStage.setTitle("CastAway");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Loads the game.
     *
     * @param mouseEvent the mouse event
     */
    private void loadGame(final MouseEvent mouseEvent) {
        Player loadedPlayer;
        BulletinBoard loadedBulletinBoard;

        try {
            loadedPlayer = Player.getInstance("Charlie");
            loadedBulletinBoard = BulletinBoard.getInstance();
            Player tempPlayer = Player.deserialize("file:../../resources/playerSave.txt");
            BulletinBoard tempBulletinBoard = BulletinBoard.deserialize("file:../../resources/bulletinBoardSave.txt");

            if (tempPlayer != null) {
                loadedPlayer.setMoney(tempPlayer.getMoney());
                loadedPlayer.setDate(tempPlayer.getDate());
                loadedPlayer.setCastOfTheDay(tempPlayer.getCastOfTheDay());
                loadedPlayer.getInventory().putAll(tempPlayer.getInventory());
                loadedPlayer.getQuests().clear();
                loadedPlayer.getQuests().addAll(tempPlayer.getQuests());
            }

            if (tempBulletinBoard != null) {
                loadedBulletinBoard.getQuests().clear();
                loadedBulletinBoard.getQuests().addAll(tempBulletinBoard.getQuests());
            }
        } catch (Exception e) {
            Platform.runLater(() -> {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("No save data found.");
                errorAlert.showAndWait();
            });
            return;
        }

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500));
        fadeTransition.setNode(((Node) mouseEvent.getSource()).getScene().getRoot());
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        fadeTransition.setOnFinished(e -> {
            Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            VillageDisplay villageDisplay = new VillageDisplay();
            villageDisplay.start(currentStage);
        });

        fadeTransition.play();
    }


    /**
     * Starts the game.
     *
     * @param event the mouse event
     */
    private void startGame(final MouseEvent event) {
        String enteredName = "";

        while (enteredName.isEmpty()) {
            TextInputDialog inputDialog = new TextInputDialog();
            inputDialog.setHeaderText(null);
            inputDialog.setTitle(null);
            inputDialog.setContentText("Enter your name:");
            Optional<String> name = inputDialog.showAndWait();

            if (name.isPresent()) {
                enteredName = name.get();
            } else {
                System.out.println("User canceled or closed the dialog.");
                return;
            }
        }

        System.out.println("User entered: " + enteredName);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500));

        fadeTransition.setNode(((Node) event.getSource()).getScene().getRoot());

        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        fadeTransition.setOnFinished(e -> {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            IntroScreen gameStartDisplay = new IntroScreen();

            gameStartDisplay.start(currentStage);
        });

        fadeTransition.play();
    }

    /**
     * Generates a label with the specified text and font size.
     *
     * @param text the text to display
     * @param fontSize the font size
     * @return the label
     */
    public Label generateLabel(final String text, final int fontSize) {
        Font font = Font.loadFont("file:resources/Fonts/PressStart2P-Regular.ttf", fontSize);
        Label label = new Label(text);
        label.setFont(font);
        return label;
    }
}
