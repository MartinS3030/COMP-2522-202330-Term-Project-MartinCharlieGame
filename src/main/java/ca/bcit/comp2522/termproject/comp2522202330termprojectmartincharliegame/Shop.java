package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

/**
 * Represents the shop.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public class Shop {
    private static Shop shopInstance = null;
    private static final int ITEM_COST = 400;

    private final DiceDisplay diceDisplay;

    private Shop() {
        this.diceDisplay = new DiceDisplay();
    }

    /**
     * Gets the shop instance.
     *
     * @return the shop instance
     */
    public static Shop getInstance() {
        if (shopInstance == null) {
            shopInstance = new Shop();
        }
        return shopInstance;
    }

    /**
     * Gets the item cost.
     *
     * @return the item cost
     */
    public int getItemCost() {
        return ITEM_COST;
    }

    /**
     * Resets the shop.
     */
    public static void resetShop() {
        shopInstance = null;
    }

    /**
     * Gets the dice display.
     *
     * @return the dice display
     */
    public DiceDisplay getDiceDisplay() {
        return diceDisplay;
    }

    /**
     * Purchases an item.
     */
    public void purchaseItem() {
        Player player = Player.getInstance("Charlie");
        player.setMoney(player.getMoney() - ITEM_COST);
    }

}
