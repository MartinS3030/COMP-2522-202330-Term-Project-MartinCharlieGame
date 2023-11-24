package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.layout.HBox;

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

        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
