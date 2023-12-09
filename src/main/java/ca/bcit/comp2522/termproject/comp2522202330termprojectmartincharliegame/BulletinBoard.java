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
    public static final int COMMON_FISH_MAX = 2;
    /**
     * The minimum number of common fish required for a quest.
     */
    public static final int COMMON_FISH_MIN = 2;
    /**
     * The reward multiplier for a quest.
     */
    public static final double REWARD_MULTIPLIER = 1.5;
    /**
     * The maximum number of rare fish required for a quest.
     */
    public static final int RARE_FISH_MAX = 3;
    /**
     * The minimum number of rare fish required for a quest.
     */
    public static final int RARE_FISH_MIN = 1;
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
        if (quests.isEmpty()) {
            for (int i = 0; i < STARTING_QUESTS; i++) {
                quests.add(generateCommonQuest());
            }
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
     * Generates a rare quest.
     *
     * @return a rare quest
     */
    public Quest generateRareQuest() {
        int objectiveAmount = random.nextInt(RARE_FISH_MAX) + RARE_FISH_MIN;
        String[] questTitles = {"A Grandparent's Memory Lane",
                                "The Fish Feast",
                                "The Puzzled Ichthyologist",
                                "The Healing Waters",
                                "The Young Angler's Dream",
                                "The Festival Fish"};
        String[] questDescriptions = {" is feeling nostalgic and wishes to recreate a cherished memory of catching a"
                + " particular fish in their youth. Can you capture that special fish to bring back the sparkle of"
                + " the past?",
                " is hosting a grand fish feast for the entire village. Help make this event unforgettable by catching"
                + " the finest, most delectable fish for the feast. The reputation of your angling skills is at stake!",
                " is conducting research on fish patterns in the region. Assist them by providing a rare specimen to"
                + " unravel the mysteries of the aquatic ecosystem.",
                "The annual village festival is approaching, and the organizers need a unique fish to be the star"
                + " attraction in the fishing competition. Catch an extraordinary fish that will make this year's"
                + " festival unforgettable.",
                "believes in the therapeutic properties of a specific fish for brewing medicinal potions. Catch this"
                + " fish to contribute to the village's well-being and health.",
                "dreams of catching a rare fish to prove their worthiness among the seasoned fishermen. Help them"
                + " achieve their dream by capturing the elusive fish they desire."};
        String questTitle = questTitles[random.nextInt(6) + 1];
        String questDescription = questDescriptions[random.nextInt(6) + 1];
        Fish fish = getRequirement("rare");
        String giver = generateGiver();
        return new Quest(questTitle,
                giver, generateReward(fish.getValue(), objectiveAmount), fish,
                generateDifficulty(fish), objectiveAmount, giver + questDescription);
    }

    /**
     * Generates a legendary quest.
     *
     * @return a legendary quest
     */
    public Quest generateLegendaryQuest() {
        FishSpecies fishSpecies = new FishSpecies();
        Quest[] legendaryQuestList = {new Quest("Peaceful Shark", "Chris",
                generateReward(fishSpecies.getFish(22).getValue(), 1),
                fishSpecies.getFish(22), 3, 1,
                "The fish conservatory have noticed an increase in sharks attacking fish. Having heard of a"
                        + " shark named Bruce, that refuses to attack fish, they want you to catch it and bring it back"
                        + " for study."),
                new Quest("Lost", "Charlie", generateReward(fishSpecies.getFish(23).getValue(),
                        1), fishSpecies.getFish(23), 3, 1,
                        "A fish has come to the village asking us to help him locate his daughter, Dory. She"
                        + " has been lost in the ocean for years. Please help reunite this family."),
                new Quest("The Mermaid's Rescue Mission", "Ariel",
                        generateReward(fishSpecies.getFish(24).getValue(), 1),
                        fishSpecies.getFish(24), 3, 1,
                        "Ariel's friend Flounder got lost during an excursion to the surface world. Help her"
                        + " find her missing friend and bring him back to the Coral Kingdom."),
                new Quest("Driving Dilemma", "SpongeBob",
                        generateReward(fishSpecies.getFish(25).getValue(), 1),
                        fishSpecies.getFish(25), 3, 1,
                        "SpongeBob has come asking us to help him locate his driving school instructor,"
                                + " Mrs. Puff. He was supposed to have a driving exam with her, but he can't find her."
                                + " Help him locate his teacher so that he can pass his exam."),
                new Quest("Finding Nemo", "Marlin",
                        generateReward(fishSpecies.getFish(26).getValue(), 1),
                        fishSpecies.getFish(26), 3, 1,
                        "A frantic fish has come to the village asking for help to capture a unique specimen"
                                + " - his adventurous son, Nemo. Help catch his son and bring him to his father.")};
        int questPicker = random.nextInt(5);
        return legendaryQuestList[questPicker];
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
        final int commonMaxIndex = 13;
        final int rareStartingIndex = 14;
        final int rareMaxIndex = 5;
        final int legendaryStartingIndex = 19;
        final int legendaryMaxIndex = 3;

        fishKey = switch (rarity) {
            case "rare" -> random.nextInt(rareMaxIndex) + rareStartingIndex;
            case "legendary" -> random.nextInt(legendaryMaxIndex) + legendaryStartingIndex;
            default -> random.nextInt(commonMaxIndex) + 1;
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
        String[] givers = {"Victor", "Jake", "Grace", "Nicole", "Cam", "Alex", "Lulu", "Derek", "Corey", "Aaron",
                "Marco", "Brian", "Kate", "Mika"};
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
