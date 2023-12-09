package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class WinScreen extends EndScreen {
    @Override
    protected ImageView getImageView() {
        Image image = new Image("file:../../resources/WinScreen.png");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    @Override
    protected Label getEndMessage() {
        Label endMessage = new Label("You've made it! You waited for new of the wave but it never came." +
                                        "Oh well, at least you're off the island.");
        endMessage.setStyle("-fx-font-size: 50px; -fx-text-fill: white;");
        endMessage.setEffect(new javafx.scene.effect.DropShadow(10, Color.BLACK));
        endMessage.setWrapText(true);
        endMessage.setAlignment(Pos.BOTTOM_CENTER);
        return endMessage;
    }
}
