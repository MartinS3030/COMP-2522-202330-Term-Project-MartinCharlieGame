package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * A class that represents a player.
 *
 * @author Charlie Martin
 * @version 2023
 */
public class Player implements Serializable {
    private static Player instance;
    private final String name;
    private final HashMap<String, Item> inventory = new HashMap<>();
    private final ArrayList<Quest> activeQuests = new ArrayList<>();
    private final Fishing_Rod rod;
    private static int money;
    private static int date;
    private int castOfTheDay = 0;
    private final int MAX_CAST = 5;
    private final int SIDES_OF_DICE = 6;

    private Player(String name) {
        this.name = name;
        Rod_Components base = new Rod_Components("Basic Base", basicDie());
        Rod_Components rodComponent = new Rod_Components("Basic Rod", basicDie());
        Rod_Components reel = new Rod_Components("Basic Reel", basicDie());
        Rod_Components line = new Rod_Components("Basic Line", basicDie());
        Rod_Components hook = new Rod_Components("Basic Hook", basicDie());

        this.rod = new Fishing_Rod(base, rodComponent, reel, line, hook);
    }

    /**
     * Gets the instance of the player.
     *
     * @param name the name of the player
     * @return the instance of the player
     */
    public static Player getInstance(String name) {
        if (instance == null) {
            instance = new Player(name);
            money = 1000;
            date = 1;
        }
        return instance;
    }

    /**
     * Returns the name of the player.
     *
     * @return the name of the player as a String
     */
    public String getName() {
        return name;
    }

    public Fishing_Rod getRod() {
        return rod;
    }

    public void setDate(int date) {
        Player.date = date + 1;
    }

    public int getDate() {
        return Player.date;
    }

    public void setCastOfTheDay(int castOfTheDay) {
        this.castOfTheDay = castOfTheDay;
    }

    public int getCastOfTheDay() {
        return castOfTheDay;
    }

    public int getCastLeft() {
        return MAX_CAST - castOfTheDay;
    }

    public ArrayList<Integer> basicDie() {
        ArrayList<Integer> basicDie = new ArrayList<>();
        for (int i = 1; i <= SIDES_OF_DICE; i++) {
            basicDie.add(i);
        }
        return basicDie;
    }

    public void addQuests (Quest quest) {
        for (Quest activeQuest : activeQuests) {
            if (activeQuest.getID() == (quest.getID())) {
                throw new IllegalArgumentException("Quest already accepted");
            }
        }
        if (activeQuests.size() < 5) {
            activeQuests.add(quest);
        } else {
            throw new IllegalArgumentException("Quest limit reached");
        }
    }

    public void removeQuest(Quest quest) {
        for (int i = 0; i < activeQuests.size(); i++) {
            if (activeQuests.get(i).getID() == quest.getID()) {
                activeQuests.remove(i);
                break;
            }
        }
    }

    public ArrayList<Quest> getQuests() {
        return activeQuests;
    }

    public void addInventory(Item item) {
        if (inventory.containsKey(item.getName())) {
            Item itemInInventory = inventory.get(item.getName());
            itemInInventory.setAmount(itemInInventory.getAmount() + item.getAmount());
        } else {
            inventory.put(item.getName(), item);
        }
    }

    public HashMap<String, Item> getInventory() {
        return inventory;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void serialize(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
            System.out.println("Serialization completed. Player data saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Player deserialize(String filename) {
        Player player = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            player = (Player) in.readObject();
            System.out.println("Deserialization completed. Player data loaded from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return player;
    }
}
