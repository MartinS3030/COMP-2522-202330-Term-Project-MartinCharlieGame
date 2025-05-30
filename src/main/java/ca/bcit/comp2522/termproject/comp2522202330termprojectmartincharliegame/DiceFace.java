package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a dice face.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public final class DiceFace {
    private DiceFace() { }

    /**
     * Returns an ImageView of the dice face.
     *
     * @param diceFace the dice face
     * @return an ImageView of the dice face
     */
    public static ImageView getDiceFaceImage(final Integer diceFace) {
        Image pipOne = new Image("file:../../resources//Dice/dice-six-faces-one.png");
        Image pipTwo = new Image("file:../../resources//Dice/dice-six-faces-two.png");
        Image pipThree = new Image("file:../../resources//Dice/dice-six-faces-three.png");
        Image pipFour = new Image("file:../../resources//Dice/dice-six-faces-four.png");
        Image pipFive = new Image("file:../../resources//Dice/dice-six-faces-five.png");
        Image pipSix = new Image("file:../../resources//Dice/dice-six-faces-six.png");

        return switch (diceFace) {
            case 1 -> new ImageView(pipOne);
            case 2 -> new ImageView(pipTwo);
            case 3 -> new ImageView(pipThree);
            case 4 -> new ImageView(pipFour);
            case 5 -> new ImageView(pipFive);
            default -> new ImageView(pipSix);
        };
    }
}
