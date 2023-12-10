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
public final class Player implements Serializable {
    /**
     * The starting money of the player.
     */
    public static final int STARTING_MONEY = 1000;
    /**
     * The max casts of the player.
     */
    public static final int MAX_CASTS = 5;
    /**
     * The max quests of the player.
     */
    public static final int MAX_QUESTS = 5;
    /**
     * The sides of the dice.
     */
    public static final int SIDES_OF_DICE = 6;
    private static Player instance;
    private static int money;
    private static int date;
    private final String name;
    private final HashMap<String, Item> inventory = new HashMap<>();
    private final ArrayList<Quest> activeQuests = new ArrayList<>();
    private final FishingRod rod;
    private int castOfTheDay = 0;
    private boolean hasBoat = false;

    private Player(final String name) {
        this.name = name;
        RodComponents base = new RodComponents("Basic Base", basicDie());
        RodComponents rodComponent = new RodComponents("Basic Rod", basicDie());
        RodComponents reel = new RodComponents("Basic Reel", basicDie());
        RodComponents line = new RodComponents("Basic Line", basicDie());
        RodComponents hook = new RodComponents("Basic Hook", basicDie());

        this.rod = new FishingRod(base, rodComponent, reel, line, hook);
    }

    /**
     * Gets the instance of the player.
     *
     * @param name the name of the player
     * @return the instance of the player
     */
    public static Player getInstance(final String name) {
        if (instance == null) {
            instance = new Player(name);
            money = STARTING_MONEY;
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
    public void setDate(final int date) {
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
    public void setCastOfTheDay(final int castOfTheDay) {
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
        return MAX_CASTS - castOfTheDay;
    }

    /**
     * Creates a ArrayList that represents a basic die.
     *
     * @return the ArrayList that represents a basic die
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
     * @throws IllegalArgumentException if the quest is already accepted or the quest limit is reached
     */
    public void addQuests(final Quest quest) {
        for (Quest activeQuest : activeQuests) {
            if (activeQuest.getID() == (quest.getID())) {
                throw new IllegalArgumentException("Quest already accepted");
            }
        }
        if (activeQuests.size() < MAX_QUESTS) {
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
    public void removeQuest(final Quest quest) {
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
    public void addInventory(final Item item) {
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
    public void setMoney(final int money) {
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
    public void serialize(final String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
            System.out.println("Serialization completed. Player data saved to " + filename);
        } catch (IOException e) {
            System.out.println("Serialization failed. Player data not saved to " + filename);
        }
    }

    /**
     * Deserializes the player.
     *
     * @param filename the name of the file to be deserialized
     * @return the deserialized player
     */
    public static Player deserialize(final String filename) {
        Player player = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            player = (Player) in.readObject();
            System.out.println("Deserialization completed. Player data loaded from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Deserialization failed. Player data not loaded from " + filename);
        }
        return player;
    }
}
