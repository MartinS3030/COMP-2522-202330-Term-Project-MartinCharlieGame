package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import java.io.Serializable;

/**
 * Represents a quest that the player can complete.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public class Quest implements Serializable {
    /**
     * The multiplier used to generate the ID of the quest.
     */
    public static final int ID_MULTIPLIER = 1000000;
    private final int id;
    private final String title;
    private final String giver;
    private final int reward;
    private final Fish objective;
    private final int difficulty;
    private final int objectiveAmount;
    private final String description;

    /**
     * Constructs a quest.
     *
     * @param title the title of the quest
     * @param giver the name of the NPC that gives the quest
     * @param reward the amount of money the player gets for completing the quest
     * @param objective the type of fish the player needs to catch
     * @param difficulty the difficulty of the quest
     * @param objectiveAmount the amount of fish the player needs to catch
     * @param description the description of the quest
     */
    public Quest(final String title, final String giver, final int reward, final Fish objective, final int difficulty,
                 final int objectiveAmount, final String description) {
        this.id = generateID();
        this.title = title;
        this.giver = giver;
        this.reward = reward;
        this.objective = objective;
        this.difficulty = difficulty;
        this.objectiveAmount = objectiveAmount;
        this.description = description;
    }

    /**
     * Returns the title of the quest.
     *
     * @return the title of the quest as a String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the description of the quest.
     *
     * @return the description of the quest as a String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the reward of the quest.
     *
     * @return the reward of the quest as an int
     */
    public int getReward() {
        return reward;
    }

    /**
     * Returns the objective of the quest.
     *
     * @return the objective of the quest as a Fish
     */
    public Fish getObjective() {
        return objective;
    }

    /**
     * Returns the amount of fish the player needs to catch.
     *
     * @return the amount of fish the player needs to catch as an int
     */
    public int getObjectiveAmount() {
        return objectiveAmount;
    }

    /**
     * Returns the name of the NPC that gives the quest.
     *
     * @return the name of the NPC that gives the quest as a String
     */
    public String getGiver() {
        return giver;
    }

    /**
     * Returns the difficulty of the quest.
     *
     * @return the difficulty of the quest as an int
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Returns the ID of the quest.
     *
     * @return the ID of the quest as an int
     */
    public int getID() {
        return id;
    }

    /**
     * Generates a random ID for the quest.
     *
     * @return the ID of the quest as an int
     */
    public int generateID() {
        return (int) (Math.random() * ID_MULTIPLIER);
    }
}
