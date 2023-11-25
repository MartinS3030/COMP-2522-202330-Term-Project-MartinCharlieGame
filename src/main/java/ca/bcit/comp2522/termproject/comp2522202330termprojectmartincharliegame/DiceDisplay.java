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


public class DiceDisplay extends Application {

    private StackPane createRoundedBorderedImageView(ImageView imageView) {
        // Create a StackPane to hold the ImageView
        StackPane stackPane = new StackPane(imageView);

        // Define the corner radii for rounded borders
        CornerRadii cornerRadii = new CornerRadii(10); // Adjust the radius as needed

        BorderWidths borderWidth = new BorderWidths(15); // Adjust the width as needed

        // Create a BorderStroke with the desired color, style, width, and rounded corners
        BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, cornerRadii, borderWidth);

        // Create a Border with the specified BorderStroke
        Border border = new Border(borderStroke);

        // Apply the Border to the StackPane
        stackPane.setBorder(border);

        // Set the size of the StackPane to match the size of the image
        stackPane.setMaxSize(imageView.getFitWidth(), imageView.getFitHeight());

        return stackPane;
    }


    private ImageView[] getImageViews() {
        Image pipOne = new Image("file:../../resources//Dice/dice-six-faces-one.png");
        Image pipTwo = new Image("file:../../resources//Dice/dice-six-faces-two.png");
        Image pipThree = new Image("file:../../resources//Dice/dice-six-faces-three.png");
        Image pipFour = new Image("file:../../resources//Dice/dice-six-faces-four.png");
        Image pipFive = new Image("file:../../resources//Dice/dice-six-faces-five.png");
        Image pipSix = new Image("file:../../resources//Dice/dice-six-faces-six.png");


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

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Fishing_Rod fishingRod = new Fishing_Rod();


        ImageView[] diceViews = getImageViews();

        for (ImageView diceView : diceViews) {
            diceView.setPreserveRatio(true); // Preserve the aspect ratio of the image
            diceView.setFitWidth(100); // Set the desired width
        }

        HBox hBox = new HBox();
        VBox[] vBox = new VBox[]{new VBox(), new VBox(), new VBox(), new VBox(), new VBox()};
        for (int i = 0; i < diceViews.length; i++) {
            Button lockUnlock = ButtonMaker.createButton("lockUnlock", this::lockDice, 0, 0);
            vBox[i].getChildren().addAll(createRoundedBorderedImageView(diceViews[i]), lockUnlock);
            hBox.getChildren().add(vBox[i]);
        }
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
