package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import java.io.Serializable;

public class Fish implements Serializable, Item {
    private String name;
    private String rarity;
    private String requirementType;
    private int requirementValue;
    private double value;

    public Fish(final String name, final String rarity, final String requirementType, final int requirementValue, final double value) {
        this.name = name;
        this.rarity = rarity;
        this.requirementType = requirementType;
        this.requirementValue = requirementValue;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getRarity() {
        return rarity;
    }

    public String getRequirementType() {
        return requirementType;
    }

    public int getRequirementValue() {
        return requirementValue;
    }

    @Override
    public double getValue() {
        return value;
    }
}
