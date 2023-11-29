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

}
