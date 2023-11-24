package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;
import java.util.ArrayList;
public interface Dice {
    void roll();
    ArrayList<Integer> getFaceList();

    void removeFace(Integer face);
    void addFace(Integer face);

    Integer getFaceUpValue();
}
