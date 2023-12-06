package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class BulletinBoard implements Board, Serializable {
    private static BulletinBoard instance;
    private final ArrayList<Quest> quests = new ArrayList<>();
    private final Player player = Player.getInstance("Charlie");
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
        for (int i = 0; i < 5; i++) {
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

    public Quest generateCommonQuest() {
        int objectiveAmount = random.nextInt(4) + 3;
        Fish fish = getRequirement("common");
        String giver = generateGiver();
        return new Quest("Catch " + objectiveAmount + " " + fish.getName(),
                giver, generateReward(fish.getValue(), objectiveAmount), fish,
                generateDifficulty(fish), objectiveAmount, giver + " wants you to help them catch " + objectiveAmount + " " + fish.getName() + ". They would really appreciate your help with this ASAP!");
    }

    public Fish getRequirement(String rarity) {
        int fishKey;
        FishSpecies fishSpecies = new FishSpecies();

        if (rarity.equals("common")) {
            fishKey = random.nextInt(13) + 1;
        } else if (rarity.equals("rare")) {
            fishKey = random.nextInt(5) + 14;
        } else if (rarity.equals("legendary")) {
            fishKey = random.nextInt(3) + 19;
        } else {
            fishKey = random.nextInt(13) + 1;
        }

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

    public void newQuests() {
        System.out.println("working on it");
    }

    public int generateReward(double value, int objectiveAmount) {
        return (int) Math.floor(value * (1.5 + random.nextDouble() * 0.5 * objectiveAmount));
    }

    public String generateGiver() {
        String[] givers = {"Bob", "Joe", "Steve", "Bill", "John", "Jack", "J"};
        return givers[(int) (Math.random() * givers.length)];
    }

    public int generateDifficulty(Fish objective) {
        int difficulty = 0;
        if (objective.getRarity().equals("common")) {
            difficulty = 1;
        } else if (objective.getRarity().equals("rare")) {
            difficulty = 2;
        } else if (objective.getRarity().equals("legendary")) {
            difficulty = 3;
        }
        return difficulty;
    }

    public void removeQuest(Quest quest) {
        quests.remove(quest);
    }

    public void serialize(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
            System.out.println("Serialization completed. BulletinBoard data saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Deserialization method
    public static BulletinBoard deserialize(String filename) {
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
