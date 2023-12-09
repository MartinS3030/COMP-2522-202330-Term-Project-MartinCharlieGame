package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;
import java.util.ArrayList;

/**
 * An interface that represents a die.
 *
 * @author Charlie Martin
 * @version 2023
 */
public interface Dice {
    /**
     * Rolls the die.
     */
    void roll();

    /**
     * Gets the face list.
     *
     * @return the face list
     */
    ArrayList<Integer> getFaceList();

    /**
     * Gets the face up value.
     *
     * @return the face up value
     */
    Integer getFaceUpValue();

    /**
     * Removes a dice face.
     *
     * @param face the face value
     */
    void removeFace(Integer face);

    /**
     * Adds a dice face.
     *
     * @param face the face value
     */
    void addFace(Integer face);

    /**
     * Replaces a face.
     *
     * @param face the face to replace
     * @param replacementFace the face to replace it with
     */
    void replaceFace(Integer face, Integer replacementFace);
}
