package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import java.util.ArrayList;
import java.util.Random;

public class BulletinBoard implements Board{
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

}
