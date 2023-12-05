package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

        startGame.setOnMouseClicked(event -> showGameStartDisplay());
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



    public Label generateLabel(String text, int fontSize) {
        Font font = Font.loadFont("file:resources/Fonts/PressStart2P-Regular.ttf", fontSize);
        Label label = new Label(text);
        label.setFont(font);
        return label;
    }
}
