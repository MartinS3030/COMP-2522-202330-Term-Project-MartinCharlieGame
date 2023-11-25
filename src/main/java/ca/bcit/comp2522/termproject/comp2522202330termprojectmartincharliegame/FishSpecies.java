package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import java.util.HashMap;

public class FishSpecies {
    private final HashMap<Integer, Fish> fishSpecies = new java.util.HashMap<>();

    public FishSpecies() {
        populateFishSpecies();
    }

    public void populateFishSpecies() {
        fishSpecies.put(1, new Fish("Bass", "Common", "greater", 18, 1.0));
        fishSpecies.put(2, new Fish("Catfish", "Common", "greater", 15, 1.0));
        fishSpecies.put(3, new Fish("Char", "Common", "equal", 1, 1.0));
        fishSpecies.put(4, new Fish("Clownfish", "Common", "equal", 1, 1.0));
        fishSpecies.put(5, new Fish("Goldfish", "Common", "equal", 1, 1.0));
        fishSpecies.put(6, new Fish("Guppy", "Common", "equal", 1, 1.0));
        fishSpecies.put(7, new Fish("Koi", "Common", "equal", 1, 1.0));
        fishSpecies.put(8, new Fish("Piranha", "Common", "equal", 1, 1.0));
        fishSpecies.put(9, new Fish("Red Snapper", "Common", "equal", 1, 1.0));
        fishSpecies.put(10, new Fish("Salmon", "Common", "equal", 1, 1.0));
        fishSpecies.put(11, new Fish("Sweetfish", "Common", "equal", 1, 1.0));
        fishSpecies.put(12, new Fish("Turtle", "Common", "equal", 1, 1.0));
        fishSpecies.put(13, new Fish("Yellow Perch", "Common", "equal", 1, 1.0));

        fishSpecies.put(14, new Fish("Arapaima", "Rare", "ofakind", 1, 1.0));
        fishSpecies.put(15, new Fish("Coelacanth", "Rare", "ofakind", 1, 1.0));
        fishSpecies.put(16, new Fish("Dorado", "Rare", "ofakind", 1, 1.0));
        fishSpecies.put(17, new Fish("Marlin", "Rare", "ofakind", 1, 1.0));
        fishSpecies.put(18, new Fish("Saw Shark", "Rare", "ofakind", 1, 1.0));
        fishSpecies.put(19, new Fish("Shark", "Rare", "ofakind", 1, 1.0));
        fishSpecies.put(20, new Fish("Sturgeon", "Rare", "ofakind", 1, 1.0));
        fishSpecies.put(21, new Fish("Tuna", "Rare", "ofakind", 1, 1.0));

        fishSpecies.put(22, new Fish("Bruce", "Legendary", "straight", 5, 1.0));
        fishSpecies.put(23, new Fish("Dory", "Legendary", "straight", 3, 1.0));
        fishSpecies.put(24, new Fish("Flounder", "Legendary", "straight", 4, 1.0));
        fishSpecies.put(25, new Fish("Mr. Krabs", "Legendary", "straight", 1, 1.0));
        fishSpecies.put(26, new Fish("Mrs. Puff", "Legendary", "straight", 2, 1.0));
        fishSpecies.put(27, new Fish("Nemo", "Legendary", "straight", 1, 1.0));
    }

    public Fish getFish(int key) {
        return fishSpecies.get(key);
    }
}
