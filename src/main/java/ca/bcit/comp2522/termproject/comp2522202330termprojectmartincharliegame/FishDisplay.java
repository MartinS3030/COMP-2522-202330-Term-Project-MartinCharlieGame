package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class FishDisplay extends Application {
    private ArrayList<Fish> fishList = new ArrayList<>();
    private Random random = new Random();

    @Override
    public void start(Stage primaryStage) {
        Image oceanImage = new Image("file:../../resources/Ocean.png");
        ImageView oceanImageView = new ImageView(oceanImage);

        generateFish();
        for (Fish fish : fishList) {
            System.out.println(fish.getName());
        }

        HBox fishContainer = new HBox();
        fishContainer.setSpacing(70);

        for (Fish fish : fishList) {
            Image fishImage = new Image("file:../../resources/Fish/" + fish.getName() + ".png");
            ImageView fishImageView = new ImageView(fishImage);

            fishImageView.setFitWidth(100);
            fishImageView.setFitHeight(100);

            fishImageView.setTranslateY(50);
            fishImageView.setTranslateX(200);

            fishContainer.getChildren().add(fishImageView);
        }

        StackPane root = new StackPane();
        root.getChildren().addAll(oceanImageView, fishContainer);

        Scene scene = new Scene(root, 1200, 648);
        primaryStage.setTitle("Fish Display");

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), root);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void generateFish() {
        int fishKey = 0;
        int count = 0;
        while (count < 5) {
            int rarity = random.nextInt(100) + 1;
            if (rarity <= 50) {
                fishKey = random.nextInt(13) + 1;
            } else if (rarity > 50) {
                fishKey = random.nextInt(8) + 14;
            }
//            } else if (rarity <= 90) {
//                fishKey = random.nextInt(5) + 22;
//            }
            count++;
            FishSpecies fishSpecies = new FishSpecies();
            Fish generatedFish = fishSpecies.getFish(fishKey);
            fishList.add(generatedFish);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
