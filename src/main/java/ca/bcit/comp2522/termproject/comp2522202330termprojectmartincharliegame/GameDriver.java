package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * HelloJavaFX, an introduction to JavaFX.
 *
 * Note that this class extends javafx.application.Application.
 *
 * Every JavaFX application is a subclass of the Application class. The
 * Application class is an abstract class.  It contains an abstract method
 * called start(Stage primaryStage).  This abstract method must be
 * implemented.  This is where we assemble our interface.
 *
 *
 * @author Lewis & Loftus 9e
 * @author BCIT
 * @version 2022
 */
public class GameDriver extends Application {

    private static final int TIME_LIMIT = 10;
    private static final int MONEY_GOAL = 1000;

    /**
     * Creates and displays two Text objects in a JavFX Window.
     *
     * The start method is public and it returns nothing (void).  We
     * create our JavaFX application by instantiating Components,
     * assembling them into Groups, and then adding the root Group
     * to a Scene.  Finally, we add the scene to the Stage, and
     * instruct the stage to display the Scene.
     *
     * Do you appreciate the theatre/stagecraft metaphors? :D
     *
     * @param primaryStage contains the Scene
     */
    public void start(final Stage primaryStage) {
        StartScreen startScreen = new StartScreen();
        startScreen.start(primaryStage);
    }

    public void endGame(final Stage primaryStage) {
        Player player = Player.getInstance("Charlie");
        if (player.getMoney() >= MONEY_GOAL) {
            EndScreen winScreen = new WinScreen();
            winScreen.start(primaryStage);
        } else {
            EndScreen loseScreen = new EndScreen();
            loseScreen.start(primaryStage);
        }

    }

    /**
     * Launches the JavaFX application.  We still need a main method in our
     * JavaFX applications.  The main method must do one thing.  Pass
     * the command line arguments (args) to the launch method inherited from
     * the Application class.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        launch(args);
    }

    public static int getTimeLimit() {
        return TIME_LIMIT;
    }

    public static int getMoneyGoal() {
        return MONEY_GOAL;
    }

    public void serializePlayer() {
        Player player = Player.getInstance("Charlie");

        for (Quest quest : player.getQuests()) {
            System.out.println(quest.getTitle());
        }
        player.addQuests(new Quest("Catch 5 Common Fish", "Charlie", 5, new Fish("Common Fish", "common", "none", 0, 0), 0, 0, "Catch 5 Common Fish"));
        player.addQuests(new Quest("Catch 5 Rare Fish", "Charlie", 5, new Fish("Rare Fish", "rare", "none", 0, 0), 0, 0, "Catch 5 Rare Fish"));
        for (Quest quest : player.getQuests()) {
            System.out.println(quest.getTitle());
        }
        player.serialize("file:../../resources/playerSave.txt");
        //        Player loadedPlayer = Player.deserialize("file:../../resources/playerSave.txt");
//        for (Quest quest : loadedPlayer.getQuests()) {
//            System.out.println(quest.getTitle());
//        }
    }

    public void serializeBoard() {
        BulletinBoard bulletinBoard = BulletinBoard.getInstance();

        for (Quest quest : bulletinBoard.getQuests()) {
            System.out.println(quest.getTitle());
        }

        bulletinBoard.serialize("file:../../resources/bulletinBoardSave.txt");
        //        BulletinBoard loadedBulletinBoard = BulletinBoard.deserialize("file:../../resources/bulletinBoardSave.txt");
//        for (Quest quest : loadedBulletinBoard.getQuests()) {
//            System.out.println(quest.getTitle());
//        }
    }
}