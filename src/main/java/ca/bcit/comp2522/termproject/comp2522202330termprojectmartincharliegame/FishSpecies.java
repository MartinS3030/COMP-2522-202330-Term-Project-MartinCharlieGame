package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import java.util.HashMap;

public class FishSpecies {
    private final HashMap<Integer, Fish> fishSpecies = new java.util.HashMap<>();

    public FishSpecies() {
        populateFishSpecies();
    }

    public void populateFishSpecies() {
        fishSpecies.put(1, new Fish("Bass", "Common", "greater", 3, 1));
        fishSpecies.put(2, new Fish("Catfish", "Common", "greater", 3, 1));
        fishSpecies.put(3, new Fish("Char", "Common", "less", 3, 1));
        fishSpecies.put(4, new Fish("Clownfish", "Common", "less", 3, 1));
        fishSpecies.put(5, new Fish("Goldfish", "Common", "evensOnly", 3, 1));
        fishSpecies.put(6, new Fish("Guppy", "Common", "oddsOnly", 3, 1));
        fishSpecies.put(7, new Fish("Koi", "Common", "fullHouse", 3, 1));
        fishSpecies.put(8, new Fish("Piranha", "Common", "equalTo", 1, 1));
        fishSpecies.put(9, new Fish("Red Snapper", "Common", "straight", 3, 1));
        fishSpecies.put(10, new Fish("Salmon", "Common", "equalTo", 6, 1));
        fishSpecies.put(11, new Fish("Sweetfish", "Common", "straight", 4, 1));
        fishSpecies.put(12, new Fish("Turtle", "Common", "ofakind", 2, 1));
        fishSpecies.put(13, new Fish("Yellow Perch", "Common", "ofakind", 3, 1));

        fishSpecies.put(14, new Fish("Arapaima", "Rare", "ofakind", 2, 1));
        fishSpecies.put(15, new Fish("Coelacanth", "Rare", "ofakind", 2, 1));
        fishSpecies.put(16, new Fish("Dorado", "Rare", "ofakind", 3, 1));
        fishSpecies.put(17, new Fish("Marlin", "Rare", "ofakind", 3, 1));
        fishSpecies.put(18, new Fish("Saw Shark", "Rare", "straight", 4, 1));
        fishSpecies.put(19, new Fish("Shark", "Rare", "straight", 4, 1));
        fishSpecies.put(20, new Fish("Sturgeon", "Rare", "fullHouse", 1, 1));
        fishSpecies.put(21, new Fish("Tuna", "Rare", "fullHouse", 1, 1));

        fishSpecies.put(22, new Fish("Bruce", "Legendary", "straight", 5, 1));
        fishSpecies.put(23, new Fish("Dory", "Legendary", "straight", 3, 1));
        fishSpecies.put(24, new Fish("Flounder", "Legendary", "straight", 4, 1));
        fishSpecies.put(25, new Fish("Mr. Krabs", "Legendary", "straight", 1, 1));
        fishSpecies.put(26, new Fish("Mrs. Puff", "Legendary", "straight", 2, 1));
        fishSpecies.put(27, new Fish("Nemo", "Legendary", "straight", 1, 1));
    }

    public Fish getFish(int key) {
        return fishSpecies.get(key);
    }

    public HashMap<Integer, Fish> getFishSpecies() {
        return fishSpecies;
    }
}
