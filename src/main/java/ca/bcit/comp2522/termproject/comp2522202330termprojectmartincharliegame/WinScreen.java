package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WinScreen extends EndScreen    {
    @Override
    protected ImageView getImageView() {
        Image image = new Image("file:../../resources/WinScreen.png");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    @Override
    protected Label getEndMessage() {
        Label endMessage = new Label("You've made it off the island!");
        endMessage.setStyle("-fx-font-size: 100px; -fx-text-fill: white;");
        return endMessage;
    }
}
