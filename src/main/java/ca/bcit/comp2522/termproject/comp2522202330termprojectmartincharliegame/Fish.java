package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import java.io.Serializable;

/**
 * Represents a Fish.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public class Fish implements Serializable, Item {
    private final String name;
    private final String rarity;
    private final String requirementType;
    private final int requirementValue;
    private final int value;

    /**
     * Constructs a Fish.
     *
     * @param name the name of the Fish
     * @param rarity the rarity of the Fish
     * @param requirementType the requirement type of the Fish
     * @param requirementValue the requirement value of the Fish
     * @param value the value of the Fish
     */
    public Fish(final String name, final String rarity, final String requirementType, final int requirementValue,
                final int value) {
        this.name = name;
        this.rarity = rarity;
        this.requirementType = requirementType;
        this.requirementValue = requirementValue;
        this.value = value;
    }

    /**
     * Returns the name of the Fish.
     *
     * @return the name of the Fish as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the rarity of the Fish.
     *
     * @return the rarity of the Fish as a String
     */
    public String getRarity() {
        return rarity;
    }

    /**
     * Returns the requirement type of the Fish.
     *
     * @return the requirement type of the Fish as a String
     */
    public String getRequirementType() {
        return requirementType;
    }

    /**
     * Returns the requirement value of the Fish.
     *
     * @return the requirement value of the Fish as an int
     */
    public int getRequirementValue() {
        return requirementValue;
    }

    /**
     * Returns the value of the Fish.
     *
     * @return the value of the Fish as an int
     */
    @Override
    public int getValue() {
        return value;
    }
}
