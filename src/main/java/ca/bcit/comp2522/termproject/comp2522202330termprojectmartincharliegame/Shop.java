package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import java.util.ArrayList;

public class Shop {
    ArrayList<Dice> items = new ArrayList<>();
    private static final int ITEM_COST = 200;

    public Shop() {
        generateItems();
    }

    public ArrayList<Dice> getItems() {
        return this.items;
    }

    public void purchaseItem(Dice item) {
        Player player = Player.getInstance("Charlie");
        player.setMoney(player.getMoney() - ITEM_COST);
    }

    public void generateItems() {
        Fishing_Rod dice = new Fishing_Rod();
        for (Dice die : dice.getComponents()) {
            die.roll();
            items.add(die);
        }
    }
}
