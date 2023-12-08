package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Represents a bulletin board that holds quests.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public final class BulletinBoard implements Board, Serializable {
    /**
     * The number of starting quests.
     */
    public static final int STARTING_QUESTS = 5;
    /**
     * The maximum number of common fish required for a quest.
     */
    public static final int COMMON_FISH_MAX = 4;
    /**
     * The minimum number of common fish required for a quest.
     */
    public static final int COMMON_FISH_MIN = 3;
    /**
     * The reward multiplier for a quest.
     */
    public static final double REWARD_MULTIPLIER = 1.5;
    private static BulletinBoard instance;
    private final ArrayList<Quest> quests = new ArrayList<>();
    private final Random random = new Random();

    private BulletinBoard() {
        generateQuests();
    }

    /**
     * Gets the instance of the bulletin board.
     *
     * @return the instance of the bulletin board
     */
    public static BulletinBoard getInstance() {
        if (instance == null) {
            instance = new BulletinBoard();
        }
        return instance;
    }

    /**
     * Generates the basic 5 quests for the bulletin board.
     */
    @Override
    public void generateQuests() {
        for (int i = 0; i < STARTING_QUESTS; i++) {
            quests.add(generateCommonQuest());
        }
        for (Quest quest : quests) {
            System.out.println(quest.getTitle());
            System.out.println(quest.getDescription());
            System.out.println(quest.getReward());
            System.out.println(quest.getObjective().getName());
            System.out.println(quest.getObjectiveAmount());
            System.out.println();
        }
    }

    /**
     * Generates a common quest.
     *
     * @return a common quest
     */
    public Quest generateCommonQuest() {
        int objectiveAmount = random.nextInt(COMMON_FISH_MAX) + COMMON_FISH_MIN;
        Fish fish = getRequirement("common");
        String giver = generateGiver();
        return new Quest("Catch " + objectiveAmount + " " + fish.getName(),
                giver, generateReward(fish.getValue(), objectiveAmount), fish,
                generateDifficulty(fish), objectiveAmount, giver + " wants you to help them catch "
                + objectiveAmount + " " + fish.getName() + ". They would really appreciate your help with this ASAP!");
    }

    /**
     * Generates a fish requirement for the quest.
     *
     * @param rarity the rarity of the fish as a string
     * @return a fish requirement for the quest
     */
    public Fish getRequirement(final String rarity) {
        int fishKey;
        FishSpecies fishSpecies = new FishSpecies();

        fishKey = switch (rarity) {
            case "common" -> random.nextInt(13) + 1;
            case "rare" -> random.nextInt(5) + 14;
            case "legendary" -> random.nextInt(3) + 19;
            default -> random.nextInt(13) + 1;
        };

        return fishSpecies.getFish(fishKey);
    }


    /**
     * Gets the quests on the bulletin board.
     *
     * @return the quests on the bulletin board
     */
    @Override
    public ArrayList<Quest> getQuests() {
        return quests;
    }

    /**
     * Generates a reward for the quest.
     *
     * @param value the value of the fish as a double
     * @param objectiveAmount the amount of fish required as an int
     * @return a reward for the quest as an int
     */
    public int generateReward(final double value, final int objectiveAmount) {
        return (int) Math.floor(value * (REWARD_MULTIPLIER + random.nextDouble() * objectiveAmount));
    }

    /**
     * Generates a giver for the quest.
     *
     * @return a giver for the quest as a string
     */
    public String generateGiver() {
        String[] givers = {"Bob", "Joe", "Steve", "Bill", "John", "Jack", "J"};
        return givers[(int) (Math.random() * givers.length)];
    }

    /**
     * Generates a difficulty for the quest.
     *
     * @param objective the objective of the quest as a fish
     * @return a difficulty for the quest as an int
     */
    public int generateDifficulty(final Fish objective) {
        return switch (objective.getRarity()) {
            case "common" -> 1;
            case "rare" -> 2;
            case "legendary" -> 3;
            default -> 0;
        };
    }

    /**
     * Adds a quest to the bulletin board.
     *
     * @param quest the quest to be added
     */
    public void addQuest(final Quest quest) {
        quests.add(quest);
    }

    /**
     * Removes a quest from the bulletin board.
     *
     * @param quest the quest to be removed
     */
    public void removeQuest(final Quest quest) {
        quests.remove(quest);
    }

    /**
     * Serializes the bulletin board object.
     *
     * @param filename the name of the file to be serialized to as a string
     */
    public void serialize(final String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
            System.out.println("Serialization completed. BulletinBoard data saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserializes the bulletin board object.
     *
     * @param filename the name of the file to be deserialized from as a string
     * @return the deserialized bulletin board object
     */
    public static BulletinBoard deserialize(final String filename) {
        BulletinBoard bulletinBoard = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            bulletinBoard = (BulletinBoard) in.readObject();
            System.out.println("Deserialization completed. BulletinBoard data loaded from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bulletinBoard;
    }
}
