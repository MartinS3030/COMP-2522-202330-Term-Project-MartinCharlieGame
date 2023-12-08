package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ShopDisplay extends Application {

    private static Label gold;

    @Override
    public void start(Stage primaryStage) {
        Shop shop = Shop.getInstance();
        Image shopImage = new Image("file:../../resources/Shop.png");
        ImageView shopView = new ImageView(shopImage);
        shopView.setFitHeight(648);
        shopView.setFitWidth(1200);


        DiceDisplay diceDisplay = shop.getDiceDisplay();

        HBox diceDisplayHBox = diceDisplay.getDiceDisplay();
        diceDisplayHBox.setTranslateY(50);
        diceDisplayHBox.setAlignment(Pos.TOP_CENTER);

        RodShopDisplay rodDisplay = new RodShopDisplay(primaryStage,diceDisplay);
        HBox rodDisplayHBox = rodDisplay.getRodDisplayHBox();
        rodDisplayHBox.setTranslateY(300);
        rodDisplayHBox.setAlignment(Pos.BOTTOM_CENTER);

        VBox shopVBox = new VBox(diceDisplayHBox, rodDisplayHBox);

        Image backButton = new Image("file:../../resources/backButton.png");
        ImageView backButtonView = new ImageView(backButton);
        backButtonView.setFitWidth(75);
        backButtonView.setFitHeight(75);
        backButtonView.setOnMouseClicked(this::back);

        Label costLabel = new Label(String.format("Buy a dice face above to replace your own for %d gold", shop.getItemCost()));
        costLabel.setStyle("-fx-font-family: 'Oswald';-fx-font-size: 40px;"
                + "-fx-font-weight: 900;-fx-font-style: italic;"
                + "-fx-text-fill: black;");
        costLabel.setEffect(new DropShadow(15, Color.WHITE));

        Image image = new Image("file:../../resources/Items/Money.png");
        ImageView MoneyView = new ImageView(image);
        MoneyView.setFitWidth(60);
        MoneyView.setFitHeight(60);
        gold = new Label(Integer.toString(Player.getInstance("cah").getMoney()));
        gold.setStyle("-fx-font-family: 'Montserrat';-fx-font-size: 15px;-fx-font-weight: 700;");
        HBox hBox = new HBox();
        hBox.getChildren().addAll(MoneyView, gold);
        hBox.setPrefSize(100, 100);
        hBox.setTranslateX(-50);
        hBox.setAlignment(Pos.TOP_RIGHT);

        StackPane root = new StackPane();
        root.getChildren().addAll(shopView, hBox, shopVBox, costLabel, backButtonView);
        StackPane.setAlignment(costLabel, Pos.CENTER);
        StackPane.setAlignment(hBox, Pos.TOP_RIGHT);
        StackPane.setAlignment(backButtonView, Pos.TOP_LEFT);


        final int appWidth = 1100;
        final int appHeight = 648;
        Scene scene = new Scene(root, appWidth, appHeight);

        shopView.setPreserveRatio(true);
        shopView.fitHeightProperty().bind(scene.heightProperty());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void refreshMoney() {
        gold.setText(Integer.toString(Player.getInstance("cah").getMoney()));
    }

    public void back(final MouseEvent event) {
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
