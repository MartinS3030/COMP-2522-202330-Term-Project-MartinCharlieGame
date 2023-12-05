package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Rod_Components extends Item implements Dice, Serializable {
    private static final Random RANDOM_NUMBER_GENERATOR = new Random();
    private final String name;
    private final ArrayList<Integer> values;
    private int faceUpValue;

    public Rod_Components(String name, ArrayList<Integer> values) {
        this.name = name;
        this.values = values;
        this.faceUpValue = values.get(RANDOM_NUMBER_GENERATOR.nextInt(values.size()));
    }
    public Rod_Components() {
        this.name = "Generic Rod Component";
        this.values = new ArrayList<Integer>();
        int[] intArray = new int[] {1, 2, 3, 4, 5, 6};
        for (int value : intArray) {
            values.add(value);
        }
        this.faceUpValue = values.get(RANDOM_NUMBER_GENERATOR.nextInt(values.size()));
    }

    public String readName() {
        return name;
    }
    void Draw() {
        System.out.println("Rod_Component.Draw()");
    }

    @Override
    public void roll() {
        faceUpValue =  values.get(RANDOM_NUMBER_GENERATOR.nextInt(values.size()));
    }

    @Override
    public ArrayList<Integer> getFaceList() {
        return values;
    }

    @Override
    public void removeFace(Integer face) {
        values.remove(face);
    }

    @Override
    public void addFace(Integer face) {
        values.add(face);
    }

    @Override
    public Integer getFaceUpValue() {
        return faceUpValue;
    }
}
