package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Represents the rod display for the shop.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public class RodShopDisplay extends RodDisplay {

    private final DiceDisplay diceDisplay;

    /**
     * Instantiates a new rod display for the shop.
     *
     * @param primaryStage the primary stage
     * @param diceDisplay  the dice display
     */
    public RodShopDisplay(final Stage primaryStage, final DiceDisplay diceDisplay) {
        super(primaryStage);
        this.diceDisplay = diceDisplay;
    }

    /**
     * Returns an event handler for handling mouse events to open a modal popup.
     *
     * @param diceIndex The index of the dice associated with the event.
     * @return The event handler for handling mouse events.
     */
    @Override
    protected EventHandler<MouseEvent> openModal(final int diceIndex) {
        return event -> {
            ModalPopUp modal;
            if (diceDisplay.getSelectedDice().isEmpty()) {
                modal = new DiceFaceModal(diceIndex);
            } else  {
                modal = new DiceFaceShopModal(diceIndex, diceDisplay);
            }
            modal.openInGamePopup(getPrimaryStage());
        };
    }
}
