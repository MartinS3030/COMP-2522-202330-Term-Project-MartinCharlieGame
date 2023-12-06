package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import java.util.ArrayList;

public class Shop {
    ArrayList<Item> items = new ArrayList<>();

    public Shop() {

    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public void purchaseItem(Item item) {
        Player player = Player.getInstance("Charlie");
        player.setMoney(player.getMoney() - item.getValue());
    }
}
