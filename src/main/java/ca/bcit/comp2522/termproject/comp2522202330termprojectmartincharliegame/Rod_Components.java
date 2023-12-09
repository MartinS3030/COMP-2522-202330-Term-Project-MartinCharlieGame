package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents rod components.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public class Rod_Components implements Dice, Serializable {
    private static final Random RANDOM_NUMBER_GENERATOR = new Random();
    private final String name;
    private final ArrayList<Integer> values;
    private int faceUpValue;

    /**
     * Constructs an object of type Rod_Components.
     *
     * @param name the name of the rod component
     * @param values the face values of the rod component
     */
    public Rod_Components(String name, ArrayList<Integer> values) {
        this.name = name;
        this.values = values;
        this.faceUpValue = values.get(RANDOM_NUMBER_GENERATOR.nextInt(values.size()));
    }

    /**
     * Constructs an object of type Rod_Components with default values.
     */
    public Rod_Components() {
        this.name = "Generic Rod Component";
        this.values = new ArrayList<>();
        int[] intArray = new int[] {1, 2, 3, 4, 5, 6};
        for (int value : intArray) {
            values.add(value);
        }
        this.faceUpValue = values.get(RANDOM_NUMBER_GENERATOR.nextInt(values.size()));
    }

    /**
     * Returns the name of the rod component.
     *
     * @return the name of the rod component
     */
    public String readName() {
        return name;
    }

    /**
     * Rolls the dice.
     */
    @Override
    public void roll() {
        faceUpValue =  values.get(RANDOM_NUMBER_GENERATOR.nextInt(values.size()));
    }

    /**
     * Returns the face values of the rod component.
     *
     * @return the face values of the rod component
     */
    @Override
    public ArrayList<Integer> getFaceList() {
        return values;
    }

    /**
     * Replaces a face value of the rod component.
     */
    @Override
    public void replaceFace(Integer face, Integer replacementFace) {
        values.set(values.indexOf(face), replacementFace);
    }

    /**
     * Returns the face up value of the rod component.
     *
     * @return the face up value of the rod component
     */
    @Override
    public Integer getFaceUpValue() {
        return faceUpValue;
    }
}
