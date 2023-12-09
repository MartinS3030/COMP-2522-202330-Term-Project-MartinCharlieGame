package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import java.util.HashMap;

public class FishSpecies {
    private final HashMap<Integer, Fish> fishSpecies = new java.util.HashMap<>();

    public FishSpecies() {
        populateFishSpecies();
    }

    public void populateFishSpecies() {
        fishSpecies.put(1, new Fish("Bass", "Common", "greater", 14, 150));
        fishSpecies.put(2, new Fish("Catfish", "Common", "greater", 10, 100));
        fishSpecies.put(3, new Fish("Char", "Common", "less", 20, 180));
        fishSpecies.put(4, new Fish("Clownfish", "Common", "less", 21, 150));
        fishSpecies.put(5, new Fish("Goldfish", "Common", "equalTo", 18, 360));
        fishSpecies.put(6, new Fish("Guppy", "Common", "greater", 17, 260));
        fishSpecies.put(7, new Fish("Koi", "Common", "less", 15, 210));
        fishSpecies.put(8, new Fish("Piranha", "Common", "equalTo", 19, 420));
        fishSpecies.put(9, new Fish("Red Snapper", "Common", "equalTo", 17, 420));
        fishSpecies.put(10, new Fish("Salmon", "Common", "ofakind", 2, 390));
        fishSpecies.put(11, new Fish("Sweetfish", "Common", "straight", 3, 360));
        fishSpecies.put(12, new Fish("Turtle", "Common", "less", 13, 380));
        fishSpecies.put(13, new Fish("Yellow Perch", "Common", "ofakind", 2, 350));

        fishSpecies.put(14, new Fish("Arapaima", "Rare", "ofakind", 3, 810));
        fishSpecies.put(15, new Fish("Coelacanth", "Rare", "ofakind", 3, 860));
        fishSpecies.put(16, new Fish("Dorado", "Rare", "ofakind", 4, 1170));
        fishSpecies.put(17, new Fish("Marlin", "Rare", "ofakind", 4, 1190));
        fishSpecies.put(18, new Fish("Saw Shark", "Rare", "straight", 3, 650));
        fishSpecies.put(19, new Fish("Shark", "Rare", "straight", 4, 1250));
        fishSpecies.put(20, new Fish("Sturgeon", "Rare", "evens", 3, 910));
        fishSpecies.put(21, new Fish("Tuna", "Rare", "odds", 3, 900));

        fishSpecies.put(22, new Fish("Bruce", "Legendary", "ofakind", 5, 2620));
        fishSpecies.put(23, new Fish("Dory", "Legendary", "straight", 5, 2780));
        fishSpecies.put(24, new Fish("Flounder", "Legendary", "fullHouse", 5, 2560));
        fishSpecies.put(25, new Fish("Mrs Puff", "Legendary", "greater", 28, 2800));
        fishSpecies.put(26, new Fish("Nemo", "Legendary", "less", 7, 2800));
    }

    public Fish getFish(int key) {
        return fishSpecies.get(key);
    }
}
