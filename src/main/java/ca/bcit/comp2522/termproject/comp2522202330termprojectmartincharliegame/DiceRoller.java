package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a class that rolls dice.
 *
 * @author Martin Siu
 * @version 2023
 */
public class DiceRoller {
    private final ArrayList<Dice> diceList;
    private final ArrayList<Dice> lockedDiceList;
    private final ArrayList<Dice> usedDiceList;

    /**
     * Constructs a DiceRoller.
     *
     * @param fishingRod the dice to roll
     */
    public DiceRoller(final ArrayList<Dice> fishingRod) {
        diceList = fishingRod;
        lockedDiceList = new ArrayList<>();
        usedDiceList = new ArrayList<>();
    }

    /**
     * Resets the dice.
     */
    public void resetDice() {
        lockedDiceList.clear();
        usedDiceList.clear();
    }

    /**
     * Locks a dice.
     *
     * @param dice the dice to lock
     */
    public void lockDice(final Dice dice) {
        lockedDiceList.add(dice);
    }

    /**
     * Unlocks a dice.
     *
     * @param dice the dice to unlock
     */
    public void unlockDice(final Dice dice) {
        lockedDiceList.remove(dice);
    }

    /**
     * Uses a dice.
     *
     * @param dice the dice to use
     */
    public void useDice(final Dice dice) {
        usedDiceList.add(dice);
    }

    /**
     * Checks if a dice is locked.
     *
     * @param dice the dice to check
     * @return true if the dice is locked
     */
    public boolean isLocked(final Dice dice) {
        return lockedDiceList.contains(dice);
    }

    /**
     * Rolls the dice.
     */
    public void rollDice() {
        for (Dice dice : diceList) {
            if (!isLocked(dice) && !usedDiceList.contains(dice)) {
                dice.roll();
            }
        }
    }

    /**
     * Gets the result of the dice roll.
     *
     * @return the result of the dice roll
     */
    public ArrayList<Integer> getResult() {
        ArrayList<Integer> result = new ArrayList<>();
        for (Dice dice : diceList) {
            result.add(dice.getFaceUpValue());
        }
        return result;
    }

    public static void main(String[] args) {

        int gameMode = 0;
        Scanner scanner = new Scanner(System.in);

        ArrayList<Dice> diceList = new ArrayList<Dice>();
        for (int i = 0; i < 5; i++) {
            diceList.add(new Rod_Components());
        }

        DiceRoller diceRoller = new DiceRoller(diceList);

        for (Dice dice : diceList) {
            System.out.println(dice.getFaceUpValue());
        }

        while (gameMode != 1) {
            System.out.println("1 to lock, 0 to unlock");
            for (int i = 0; i < diceList.size(); i++) {
                System.out.println("Dice " + i + ": ");
                if (scanner.hasNextInt()) {
                    if (scanner.nextInt() == 1) {
                        diceRoller.lockDice(diceList.get(i));
                    } else {
                        diceRoller.unlockDice(diceList.get(i));
                    }
                } else {
                    diceRoller.unlockDice(diceList.get(i));
                }
            }
            diceRoller.rollDice();
            for (Dice dice : diceList) {
                System.out.println(dice.getFaceUpValue());
            }
        }
    }
}
