package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

public class Quest {
    private final String title;
    private final String giver;
    private final int reward;
    private final Fish objective;
    private final int difficulty;
    private final int objectiveAmount;
    private final String description;

    public Quest(String title, String giver, int reward, Fish objective, int difficulty, int objectiveAmount, String description) {
        this.title = title;
        this.giver = giver;
        this.reward = reward;
        this.objective = objective;
        this.difficulty = difficulty;
        this.objectiveAmount = objectiveAmount;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getReward() {
        return reward;
    }

    public Fish getObjective() {
        return objective;
    }

    public int getObjectiveAmount() {
        return objectiveAmount;
    }

    public String getGiver() {
        return giver;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
