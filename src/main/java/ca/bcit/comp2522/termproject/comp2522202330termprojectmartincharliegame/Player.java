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
    private final HashMap<Item, Integer> inventory = new HashMap<>();
    private ArrayList<Quest> activeQuests = new ArrayList<>();
    private Fishing_Rod rod;
    private int money = 0;
    private int date = 1;
    private int castOfTheDay = 0;
    private final int MAX_CAST = 5;

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
        this.date = date + 1;
    }

    public int getDate() {
        return date;
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
        ArrayList basicDie = new ArrayList<Integer>();
        basicDie.add(1);
        basicDie.add(2);
        basicDie.add(3);
        basicDie.add(4);
        basicDie.add(5);
        basicDie.add(6);
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
        if (inventory.containsKey(item)) {
            inventory.put(item, inventory.get(item) + 1);
        } else {
            inventory.put(item, 1);
        }
    }

    public HashMap<Item, Integer> getInventory() {
        return inventory;
    }

    public void addMoney(int money) {
        this.money += money;
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
