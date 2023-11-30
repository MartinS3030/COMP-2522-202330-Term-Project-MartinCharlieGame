package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import static java.lang.System.arraycopy;

public class ActiveQuests {
    private final static int maxQuests = 5;
    private static ActiveQuests activeQuestsInstance;
    private final Quest[] activeQuests;

    private int currentQuests;

    private ActiveQuests() {
        this.activeQuests = new Quest[maxQuests];
        this.currentQuests = 0;
    }

    public static ActiveQuests getInstance() {
        // Create the instance if it doesn't exist
        if (activeQuestsInstance == null) {
            activeQuestsInstance = new ActiveQuests();
        }
        return activeQuestsInstance;
    }

    public void addQuest(Quest quest) {
        if (currentQuests < maxQuests) {
            activeQuests[currentQuests] = quest;
            currentQuests++;
        }
    }

    public void removeQuest(Quest quest) {
        for (int i = 0; i < currentQuests; i++) {
            if (activeQuests[i] != null && activeQuests[i].equals(quest)) {
                currentQuests--;
//                for (int j = i; j < currentQuests; j++) {
//                    activeQuests[j] = activeQuests[j + 1];
//                }
                arraycopy(activeQuests, i + 1, activeQuests, i, currentQuests - i);
                activeQuests[currentQuests] = null;
                break;
            }
        }
    }

    /**
     * To be Deleted.
     */
    public void generateQuests() {
        for (int i = 0; i < currentQuests; i++) {
            activeQuests[i] = null;
        }
        currentQuests = 0;
        BulletinBoard bulletinBoard = BulletinBoard.getInstance();
        for (int i = 0; i < 3; i++) {
            addQuest(bulletinBoard.generateCommonQuest());
        }
    }

    public Quest[] getActiveQuests() {
        return activeQuests;
    }

    public int getCurrentQuests() {
        return currentQuests;
    }

    public int getMaxQuests() {
        return maxQuests;
    }

    public void getQuestsObjective() {
        for (int i = 0; i < currentQuests; i++) {
            System.out.println(activeQuests[i].getObjective());
        }
    }
}
