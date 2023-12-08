package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RodShopDisplay extends RodDisplay{

    private final DiceDisplay diceDisplay;
    public RodShopDisplay(Stage primaryStage, DiceDisplay diceDisplay) {
        super(primaryStage);
        this.diceDisplay = diceDisplay;
    }

    @Override
    protected EventHandler<MouseEvent> openModal(int DiceIndex) {
        return event -> {
            ModalPopUp modal;
            if (diceDisplay.getSelectedDice().isEmpty()) {
                modal = new DiceFaceModal(DiceIndex);
            } else  {
                modal = new DiceFaceShopModal(DiceIndex, diceDisplay);
            }
            modal.openInGamePopup(getPrimaryStage());
        };
    }
}
