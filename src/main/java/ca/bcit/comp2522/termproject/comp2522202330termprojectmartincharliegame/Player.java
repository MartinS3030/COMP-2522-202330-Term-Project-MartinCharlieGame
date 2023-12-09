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
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public class Player implements Serializable {
    private static Player instance;
    private final String name;
    private final HashMap<String, Item> inventory = new HashMap<>();
    private final ArrayList<Quest> activeQuests = new ArrayList<>();
    private final FishingRod rod;
    private static int money;
    private static int date;
    private int castOfTheDay = 0;
    private final int MAX_CAST = 5;
    private final int SIDES_OF_DICE = 6;
    private boolean hasBoat = false;

    private Player(String name) {
        this.name = name;
        Rod_Components base = new Rod_Components("Basic Base", basicDie());
        Rod_Components rodComponent = new Rod_Components("Basic Rod", basicDie());
        Rod_Components reel = new Rod_Components("Basic Reel", basicDie());
        Rod_Components line = new Rod_Components("Basic Line", basicDie());
        Rod_Components hook = new Rod_Components("Basic Hook", basicDie());

        this.rod = new FishingRod(base, rodComponent, reel, line, hook);
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

    /**
     * Returns whether the player has a boat or not.
     *
     * @return boolean value of whether the player has a boat or not
     */
    public boolean getHasBoat() {
        return hasBoat;
    }

    /**
     * The fishing rod of the player.
     *
     * @return the fishing rod of the player
     */
    public FishingRod getRod() {
        return rod;
    }

    /**
     * Sets the date of the game.
     *
     * @param date the date of the game
     */
    public void setDate(int date) {
        Player.date = date + 1;
    }

    /**
     * Returns the date of the game.
     *
     * @return the date of the game
     */
    public int getDate() {
        return Player.date;
    }

    /**
     * Sets the cast of the day.
     *
     * @param castOfTheDay the cast of the day
     */
    public void setCastOfTheDay(int castOfTheDay) {
        this.castOfTheDay = castOfTheDay;
    }

    /**
     * Returns the cast of the day.
     *
     * @return the cast of the day
     */
    public int getCastOfTheDay() {
        return castOfTheDay;
    }

    /**
     * Returns the max cast of the day.
     *
     * @return the max cast of the day
     */
    public int getCastLeft() {
        return MAX_CAST - castOfTheDay;
    }

    /**
     * Creates a ArrayList that represents a basic die.
     */
    public ArrayList<Integer> basicDie() {
        ArrayList<Integer> basicDie = new ArrayList<>();
        for (int i = 1; i <= SIDES_OF_DICE; i++) {
            basicDie.add(i);
        }
        return basicDie;
    }

    /**
     * Adds a quest to the player.
     *
     * @param quest the quest to be added
     */
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

    /**
     * Removes a quest from the player.
     *
     * @param quest the quest to be removed
     */
    public void removeQuest(Quest quest) {
        for (int i = 0; i < activeQuests.size(); i++) {
            if (activeQuests.get(i).getID() == quest.getID()) {
                activeQuests.remove(i);
                break;
            }
        }
    }

    /**
     * Returns the active quests of the player.
     *
     * @return the active quests of the player
     */
    public ArrayList<Quest> getQuests() {
        return activeQuests;
    }

    /**
     * Adds an item to the inventory.
     *
     * @param item the item to be added
     */
    public void addInventory(Item item) {
        if (inventory.containsKey(item.getName())) {
            Item itemInInventory = inventory.get(item.getName());
            itemInInventory.setAmount(itemInInventory.getAmount() + item.getAmount());
        } else {
            inventory.put(item.getName(), item);
        }
    }

    /**
     * Returns the inventory of the player.
     *
     * @return the inventory of the player
     */
    public HashMap<String, Item> getInventory() {
        return inventory;
    }

    /**
     * Sets the money of the player.
     *
     * @param money the new money amount of the player
     */
    public void setMoney(int money) {
        Player.money = money;
    }

    /**
     * Returns money of the player.
     *
     * @return the money of the player
     */
    public int getMoney() {
        return money;
    }

    /**
     * Buys the boat if the player has enough money.
     */
    public void buyBoat() {
        if (money >= GameDriver.getMoneyGoal()) {
            money -= GameDriver.getMoneyGoal();
            hasBoat = true;
        }
    }

    /**
     * Serializes the player.
     *
     * @param filename the name of the file to be serialized
     */
    public void serialize(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
            System.out.println("Serialization completed. Player data saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserializes the player.
     *
     * @param filename the name of the file to be deserialized
     * @return the deserialized player
     */
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
