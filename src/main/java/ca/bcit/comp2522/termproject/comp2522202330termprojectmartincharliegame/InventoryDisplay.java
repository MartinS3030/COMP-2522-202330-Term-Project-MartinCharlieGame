package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashMap;

public class InventoryDisplay extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a GridPane to display the Fish grid
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10); // Horizontal gap between columns
        gridPane.setVgap(10); // Vertical gap between rows
        gridPane.setPadding(new Insets(10)); // Padding around the grid

        HashMap<String, Item> playerInventory = Player.getInstance("Charlie").getInventory();

        final int[] colCounter = {0};
        final int[] rowCounter = {0};
        playerInventory.values()
                .forEach(fish -> {
                    if (colCounter[0] > 7) {
                        colCounter[0] = 0;
                        rowCounter[0]++;
                    }

                    Label nameLabel = new Label(fish.getName());
                    nameLabel.setStyle("-fx-font-family: 'Montserrat';-fx-font-size: 25px;-fx-font-weight: 700;");

                    HBox hBox = getFishHBox(fish);

                    VBox vBox = new VBox(nameLabel, hBox);
                    vBox.setAlignment(javafx.geometry.Pos.CENTER);

                    gridPane.add(vBox, colCounter[0]++, rowCounter[0]);
                });

        ImageView backgroundView = new ImageView(new Image("file:../../resources/Inventory.jpg"));

        Image backButton = new Image("file:../../resources/backButton.png");
        ImageView backButtonView = new ImageView(backButton);
        backButtonView.setFitWidth(75);
        backButtonView.setFitHeight(75);
        backButtonView.setOnMouseClicked(this::back);

        StackPane.setAlignment(backButtonView, Pos.TOP_RIGHT);

        // Create a StackPane to hold the GridPane
        StackPane stackPane = new StackPane(backgroundView, gridPane, backButtonView);
        // Create a Scene and set it on the primaryStage
        Scene scene = new Scene(stackPane, 1200, 600);
        primaryStage.setScene(scene);

        // Set the title of the window
        primaryStage.setTitle("Fish Grid Example");

        // Show the primaryStage
        primaryStage.show();
    }

    private HBox getFishHBox(Item fish) {
        ImageView imageView = new ImageView(new Image("file:../../resources/Fish/" + fish.getName() + ".png"));
        imageView.setFitWidth(75);
        imageView.setFitHeight(75);

        Label amount = new Label(String.valueOf(fish.getAmount()));
        amount.setStyle("-fx-font-family: 'Montserrat';-fx-font-size: 25px;-fx-font-weight: 700;");

        HBox hBox = new HBox(imageView, amount);
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

    public void back(final MouseEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500));

        fadeTransition.setNode(((Node) event.getSource()).getScene().getRoot());

        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        fadeTransition.setOnFinished(e -> {
            InitialFishingScreen fishDisplay = new InitialFishingScreen();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            fishDisplay.start(currentStage);
        });

        fadeTransition.play();
    }
}
