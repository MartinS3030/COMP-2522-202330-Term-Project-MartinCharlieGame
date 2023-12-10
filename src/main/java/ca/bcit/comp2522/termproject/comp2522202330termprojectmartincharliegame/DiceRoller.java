package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import java.util.ArrayList;

/**
 * Represents a class that rolls dice.
 *
 * @author Martin Siu, Charlie Zhang
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
}
