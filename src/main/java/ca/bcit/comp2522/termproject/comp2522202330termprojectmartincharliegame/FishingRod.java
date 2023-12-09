package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a fishing rod.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public class FishingRod implements Serializable {
    private final ArrayList<Dice> components = new ArrayList<>();

    /**
     * Constructs a fishing rod with the given components.
     *
     * @param rodComponent1 the first component
     * @param rodComponent2 the second component
     * @param rodComponent3 the third component
     * @param rodComponent4 the fourth component
     * @param rodComponent5 the fifth component
     */
    public FishingRod(final Rod_Components rodComponent1, final Rod_Components rodComponent2,
                      final Rod_Components rodComponent3, final Rod_Components rodComponent4,
                      final Rod_Components rodComponent5) {
        components.add(rodComponent1);
        components.add(rodComponent2);
        components.add(rodComponent3);
        components.add(rodComponent4);
        components.add(rodComponent5);
    }

    /**
     * Constructs a fishing rod with the given components.
     */
    public FishingRod() {
        components.add(new Rod_Components());
        components.add(new Rod_Components());
        components.add(new Rod_Components());
        components.add(new Rod_Components());
        components.add(new Rod_Components());
    }

    /**
     * Gets the components of the fishing rod.
     *
     * @return the components of the fishing rod
     */
    public ArrayList<Dice> getComponents() {
        return components;
    }

}
