package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class GameStartDisplay extends Application {
    @Override
    public void start(Stage primaryStage) {
        List<String> paragraphs = List.of(
                "Oh, swell! Another day in the thrilling metropolis of Aqua-Laughs, where the sun always shines, and the impending doom just adds that extra dash of excitement. So, you, our clairvoyant protagonist, had this gut feeling that a tidal wave is just itching to crash our little island paradise in a measly 30 days. No biggie, right?",
                "Rather than checking with the village's top-notch weather seagull or, you know, consulting literally anyone else, you've decided to embark on the quest of a lifetime: scraping together enough shiny sea-currency to buy a boat. Because, clearly, the solution to every problem is to sail away like a mariner with a one-way ticket to awkward island.",
                "Get ready for the adventure of a lifetime, or at least the next 30 days of your life, as you become the go-to lackey for your fellow islanders' bizarre requests. From finding lost treasure (spoiler: it's probably a soggy sandwich) to organizing crab races, your days are now a delightful circus of aquatic absurdity. Just remember, not everyone's convinced about your so-called sixth sense, and some might think you're just riding the wave of delusion.",
                "Will you accumulate enough seaweed-covered moolah to buy your grand escape vessel, or will Aqua-Laughs be your forever watery home? Strap in for the sarcastic spectacle of \"Tide's Up, Funds Up!\" Where the laughs are as salty as the ocean, and the quests are as fishy as your plan to outrun a tidal wave. Good luck, oh chosen one of the tides!"
        );

        List<Label> labelList = new ArrayList<>();
        VBox text = new VBox();
        text.setSpacing(10);

        for (String paragraph : paragraphs) {
            Label paragraphLabel = new Label(paragraph);
            Font font = Font.loadFont("file:resources/Fonts/PressStart2P-Regular.ttf", 16);
            paragraphLabel.setFont(font);
            paragraphLabel.setWrapText(true);
            paragraphLabel.setOpacity(0);
            labelList.add(paragraphLabel);
            text.getChildren().add(paragraphLabel);
        }

        Button next = ButtonMaker.createButton("Begin!", this::nextScreen, 0, 0);
        text.getChildren().add(next);
        VBox.setMargin(next, new javafx.geometry.Insets(0, 0, 0, 480));

        text.setPadding(new javafx.geometry.Insets(20, 50, 20, 50));

        StackPane root = new StackPane();
        StackPane.setMargin(labelList.get(0), new javafx.geometry.Insets(50, 50, 50, 50));
        root.getChildren().addAll(text);
        root.setStyle("-fx-background-color: #87CEEB;");

        final int appWidth = 1200;
        final int appHeight = 648;
        Scene scene = new Scene(root, appWidth, appHeight);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Tide's Up, Funds Up!");
        primaryStage.show();

        List<FadeTransition> labelFadeTransitions = new ArrayList<>();
        for (int i = 0; i < labelList.size(); i++) {
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.5), labelList.get(i));
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.setDelay(Duration.seconds(0.5));
            labelFadeTransitions.add(fadeTransition);
        }

        FadeTransition buttonFadeTransition = new FadeTransition(Duration.seconds(1), next);
        buttonFadeTransition.setFromValue(0);
        buttonFadeTransition.setToValue(1);

        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(labelFadeTransitions);
        sequentialTransition.getChildren().add(buttonFadeTransition);

        sequentialTransition.play();
    }

    private void nextScreen(ActionEvent event) {
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
}
