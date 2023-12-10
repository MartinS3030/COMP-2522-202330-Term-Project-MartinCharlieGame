package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Driver for the game.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public class GameDriver extends Application {
    private static final int TIME_LIMIT = 10;
    private static final int MONEY_GOAL = 7000;

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

    /**
     * Ends the game.
     *
     * @param primaryStage the stage
     */
    public void endGame(final Stage primaryStage) {
        EndScreen endScreen;
        Player player = Player.getInstance("Charlie");
        if (player.getHasBoat()) {
            endScreen = new WinScreen();
        } else {
            endScreen = new EndScreen();
        }
        endScreen.start(primaryStage);
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

    /**
     * Gets the time limit.
     *
     * @return the time limit
     */
    public static int getTimeLimit() {
        return TIME_LIMIT;
    }

    /**
     * Gets the money goal.
     *
     * @return the money goal
     */
    public static int getMoneyGoal() {
        return MONEY_GOAL;
    }
}