package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.stage.Stage;

public class Shop {
    private static Shop shopInstance = null;
    private static final int ITEM_COST = 1000;

    private final DiceDisplay diceDisplay;

    private Shop() {
        this.diceDisplay = new DiceDisplay();
    }

    public static Shop getInstance() {
        if (shopInstance == null) {
            shopInstance = new Shop();
        }
        return shopInstance;
    }

    public int getItemCost() {
        return ITEM_COST;
    }

    public static void resetShop() {
        shopInstance = null;
    }

    public DiceDisplay getDiceDisplay() {
        return diceDisplay;
    }


    public void purchaseItem() {
        Player player = Player.getInstance("Charlie");
        player.setMoney(player.getMoney() - ITEM_COST);
    }

}
