package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
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

public class StartScreen extends Application {

    private Stage primaryStage;

    @Override
    public void start(final Stage primaryStage) {
        this.primaryStage = primaryStage;

        Image image = new Image("file:../../resources/StartScreen.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(648);
        imageView.setFitWidth(1200);

        Label startGame = generateLabel("Start Game", 30);
        Label loadGame = generateLabel("Load Game", 30);
        Label exitGame = generateLabel("Exit Game", 30);

        startGame.setOnMouseClicked(this::startGame);
        loadGame.setOnMouseClicked(this::loadGame);
        exitGame.setOnMouseClicked(event -> Platform.exit());

        VBox menu = new VBox( startGame, loadGame, exitGame);
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

    private void loadGame(MouseEvent mouseEvent) {
        Player loadedPlayer = Player.deserialize("file:../../resources/playerSave.txt");
        BulletinBoard loadedBulletinBoard = BulletinBoard.deserialize("file:../../resources/bulletinBoardSave.txt");
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

    private void startGame(MouseEvent event) {
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
        Player player = Player.getInstance(enteredName);
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

    public Label generateLabel(String text, int fontSize) {
        Font font = Font.loadFont("file:resources/Fonts/PressStart2P-Regular.ttf", fontSize);
        Label label = new Label(text);
        label.setFont(font);
        return label;
    }
}
