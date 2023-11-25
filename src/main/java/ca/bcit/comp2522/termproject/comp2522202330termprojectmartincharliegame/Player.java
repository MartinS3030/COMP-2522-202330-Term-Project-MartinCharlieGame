package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import java.util.ArrayList;

/**
 * A class that represents a player.
 *
 * @author Charlie Martin
 * @version 2023
 */
public class Player {
    private static Player instance;
    private final String name;
    private ArrayList<Reward> inventory = new ArrayList<>();
//    private Fishing_Rod rod = new Fishing_Rod();

    private Player(String name) {
        this.name = name;
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
}
