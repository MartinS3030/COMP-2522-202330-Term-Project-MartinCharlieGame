package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;


public class DiceDisplay extends Application {

    private ImageView[] diceViews;
    private Fishing_Rod fishingRod;
    private Dice_Roller diceRoller;


    private StackPane createRoundedBorderedImageView(ImageView imageView) {
        // Create a StackPane to hold the ImageView
        StackPane stackPane = new StackPane(imageView);

        Border border = getRoundedBorder(Color.BLACK);

        // Apply the Border to the StackPane
        stackPane.setBorder(border);

        // Set the size of the StackPane to match the size of the image
        stackPane.setMaxSize(imageView.getFitWidth(), imageView.getFitHeight());

        return stackPane;
    }

    private Border getRoundedBorder(Color color) {
        // Define the corner radii for rounded borders
        CornerRadii cornerRadii = new CornerRadii(10); // Adjust the radius as needed
        BorderWidths borderWidth = new BorderWidths(15); // Adjust the width as needed

        // Create a BorderStroke with the desired color, style, width, and rounded corners
        BorderStroke borderStroke = new BorderStroke(color, BorderStrokeStyle.SOLID, cornerRadii, borderWidth);

        // Create a Border with the specified BorderStroke
        return new Border(borderStroke);
    }

    private void resetBorderColor(ImageView imageView) {
        setBorderColor(imageView, Color.BLACK);
    }

    private void setBorderColor(ImageView imageView, Color color) {
        // Apply the border color to the given ImageView
        Border border = getRoundedBorder(color);
        ((StackPane) imageView.getParent()).setBorder(border);
    }

    private ImageView getImageViews(Dice dice) {
        Image pipOne = new Image("file:../../resources//Dice/dice-six-faces-one.png");
        Image pipTwo = new Image("file:../../resources//Dice/dice-six-faces-two.png");
        Image pipThree = new Image("file:../../resources//Dice/dice-six-faces-three.png");
        Image pipFour = new Image("file:../../resources//Dice/dice-six-faces-four.png");
        Image pipFive = new Image("file:../../resources//Dice/dice-six-faces-five.png");
        Image pipSix = new Image("file:../../resources//Dice/dice-six-faces-six.png");

        return switch (dice.getFaceUpValue()) {
            case 1 -> new ImageView(pipOne);
            case 2 -> new ImageView(pipTwo);
            case 3 -> new ImageView(pipThree);
            case 4 -> new ImageView(pipFour);
            case 5 -> new ImageView(pipFive);
            default -> new ImageView(pipSix);
        };
    }

        ImageView diceView1 = new ImageView(pipSix);
        ImageView diceView2 = new ImageView(pipSix);
        ImageView diceView3 = new ImageView(pipSix);
        ImageView diceView4 = new ImageView(pipSix);
        ImageView diceView5 = new ImageView(pipSix);

        ImageView[] diceViews = {diceView1, diceView2, diceView3, diceView4, diceView5};
        return diceViews;
    }

    public void lockDice( final ActionEvent event) {
        System.out.println("Dice Locked");

    public void rollDice( final ActionEvent event) {
        System.out.println("Dice Roll");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        fishingRod = new Fishing_Rod();
        diceRoller = new Dice_Roller(fishingRod.getComponents());

        diceViews = new ImageView[fishingRod.getComponents().size()];
        for (int i = 0; i < fishingRod.getComponents().size(); i++) {
            diceViews[i] = getImageViews(fishingRod.getComponents().get(i));
        }

        for (ImageView diceView : diceViews) {
            diceView.setPreserveRatio(true); // Preserve the aspect ratio of the image
            diceView.setFitWidth(100); // Set the desired width
        }

        HBox hBox = new HBox();
        VBox[] vBox = new VBox[]{new VBox(), new VBox(), new VBox(), new VBox(), new VBox(), new VBox()};
        for (int i = 0; i < diceViews.length; i++) {
            int diceIndex = i;
            Button lockUnlock = ButtonMaker.createButton("lockUnlock", event -> lockDice(diceViews[diceIndex], fishingRod), 0, 0);
            vBox[i].getChildren().addAll(createRoundedBorderedImageView(diceViews[i]), lockUnlock);
            hBox.getChildren().add(vBox[i]);
        }
        Button rollDice = ButtonMaker.createButton("rollDice", this::rollDice, 0, 0);
        hBox.getChildren().add(rollDice);

        hBox.setSpacing(10);

        Scene scene = new Scene(hBox, 400, 300);

        primaryStage.setScene(scene);

        primaryStage.setTitle("Image Display");

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
