package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;

import java.util.ArrayList;

/**
 * Represents the dice face modal for the shop display.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public class DiceFaceShopModal extends DiceFaceModal{
    private final DiceDisplay diceDisplay;

    /**
     * Constructs a DiceFaceShopModal.
     *
     * @param diceFace the dice face
     * @param diceDisplay the dice display
     */
    public DiceFaceShopModal(int diceFace, DiceDisplay diceDisplay) {
        super(diceFace);
        this.diceDisplay = diceDisplay;
    }

    /**
     * Returns the dice face views.
     *
     * @param dice the dice
     * @param popup the popup
     * @return images of the dice faces as an ArrayList of ImageView
     */
    @Override
    protected ArrayList<ImageView> createDiceFaceViews(Dice dice, Popup popup) {
        ArrayList<ImageView> diceViews = new ArrayList<>();
        for (Integer face : dice.getFaceList()) {
            ImageView diceView= DiceFace.getDiceFaceImage(face);
            diceView.setFitWidth(50);
            diceView.setFitHeight(50);
            Dice replacementDice = diceDisplay.getSelectedDice().get(0);
            diceView.setOnMouseClicked(mouseEvent -> {
                if (Player.getInstance("").getMoney() >= Shop.getInstance().getItemCost()) {
                    dice.replaceFace(face, replacementDice.getFaceUpValue());
                    diceDisplay.getSelectedDice().clear();
                    diceDisplay.addDiceToUsedDice(replacementDice);
                    Shop.getInstance().purchaseItem();
                    ShopDisplay.refreshMoney();
                    popup.hide();
                    DiceFaceModal.clearOpenedModal();
                }
            });

            Rectangle clip = new Rectangle(diceView.getFitWidth(), diceView.getFitHeight());
            clip.setArcWidth(5);  // Adjust the arc width to control the roundness
            clip.setArcHeight(5); // Adjust the arc height to control the roundness

            // Apply the clip to the ImageView
            diceView.setClip(clip);
            diceViews.add(diceView);
        }

        return diceViews;
    }
}
