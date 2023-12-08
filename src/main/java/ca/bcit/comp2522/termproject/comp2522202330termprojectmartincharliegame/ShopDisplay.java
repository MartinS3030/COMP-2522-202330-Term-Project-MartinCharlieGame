package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ShopDisplay extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Shop shop = new Shop();
        Image shopImage = new Image("file:../../resources/Shop.png");
        ImageView shopView = new ImageView(shopImage);
        shopView.setFitHeight(837);
        shopView.setFitWidth(1367);


        DiceDisplay diceDisplay = new DiceDisplay(primaryStage);

        HBox diceDisplayHBox = diceDisplay.getDiceDisplay();
        diceDisplayHBox.setAlignment(Pos.CENTER);

        StackPane root = new StackPane();
        root.getChildren().addAll(shopView);

        final int appWidth = 1367;
        final int appHeight = 837;
        Scene scene = new Scene(root, appWidth, appHeight);

        shopView.setPreserveRatio(true);
        shopView.fitHeightProperty().bind(scene.heightProperty());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
