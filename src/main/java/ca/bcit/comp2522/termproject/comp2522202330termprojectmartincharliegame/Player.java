package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import java.util.ArrayList;

public class Player {
    private static Player instance;
    private final String name;
    private ArrayList<Reward> inventory = new ArrayList<>();
//    private Fishing_Rod rod = new Fishing_Rod();

    private Player(String name) {
        this.name = name;
    }

    public static Player getInstance(String name) {
        if (instance == null) {
            instance = new Player(name);
        }
        return instance;
    }

    public String getName() {
        return name;
    }
}
