package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import java.util.ArrayList;
import java.util.Scanner;

public class Dice_Roller {
    private ArrayList<Dice> diceList;
    private final ArrayList<Dice> lockedDiceList;
    private final ArrayList<Dice> unlockedDiceList;

    public Dice_Roller(ArrayList<Dice> fishingRod) {
        diceList = fishingRod;
        lockedDiceList = new ArrayList<Dice>();
        unlockedDiceList = new ArrayList<Dice>();
    }

    public void lockDice(Dice dice) {
            lockedDiceList.add(dice);
            unlockedDiceList.remove(dice);
    }

    public void unlockDice(Dice dice) {
        unlockedDiceList.add(dice);
        lockedDiceList.remove(dice);
    }

    public void rollDice() {
        for (Dice dice : unlockedDiceList) {
            dice.roll();
        }
    }

    public ArrayList<Integer> getResult() {
        ArrayList<Integer> result = new ArrayList<Integer>();
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

        Dice_Roller diceRoller = new Dice_Roller(diceList);

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
