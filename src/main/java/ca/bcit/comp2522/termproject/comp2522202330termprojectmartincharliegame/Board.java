package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import java.util.ArrayList;

/**
 * Represents a Board.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public interface Board {
    /**
     * Generates Quests.
     */
    void generateQuests();
    /**
     * Gets the Quests.
     *
     * @return the Quests in an ArrayList
     */
    ArrayList<Quest> getQuests();
}
