package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import java.util.ArrayList;
import java.util.List;

public class CheckRequirements {

    public static boolean checkAgainstFish(ArrayList<Dice> diceList, Fish fish) {
        return switch (fish.getRequirementType()) {
            case "ofakind" -> ofAKind(diceList, fish.getRequirementValue());
            case "straight" -> straight(diceList, fish.getRequirementValue());
            case "fullHouse" -> fullHouse(diceList);
            case "oddsOnly" -> oddsOnly(diceList);
            case "evensOnly" -> evensOnly(diceList);
            case "greater" -> greaterThan(diceList, fish.getRequirementValue());
            case "less" -> lessThan(diceList, fish.getRequirementValue());
            case "equalTo" -> equalTo(diceList, fish.getRequirementValue());
            case "twoPair" -> twoPair(diceList);
            default -> false;
        };
    }
    public static boolean greaterThan(ArrayList<Dice> diceList, int value) {
        int sum = 0;
        for (Dice dice : diceList) {
            sum += dice.getFaceUpValue();
        }
        return sum > value;
    }

    public static boolean lessThan(ArrayList<Dice> diceList, int value) {
        int sum = 0;
        for (Dice dice : diceList) {
            sum += dice.getFaceUpValue();
        }
        return sum < value;
    }

    public static boolean equalTo(ArrayList<Dice> diceList, int value) {
        int sum = 0;
        for (Dice dice : diceList) {
            sum += dice.getFaceUpValue();
        }
        return sum == value;
    }

    public static boolean ofAKind(ArrayList<Dice> diceList, int value) {
        int[] faceUpValues = new int[6];
        for (Dice dice : diceList) {
            faceUpValues[dice.getFaceUpValue() - 1]++;
        }
        for (int i = 0; i < faceUpValues.length; i++) {
            if (faceUpValues[i] == value) {
                return true;
            }
        }
        return false;
    }

    public static boolean straight(ArrayList<Dice> diceList, int value) {
        int[] faceUpValues = new int[6];

        for (Dice dice : diceList) {
            faceUpValues[dice.getFaceUpValue() - 1]++;
        }

        for (int i = 0; i <= faceUpValues.length - value; i++) {
            boolean consecutive = true;
            for (int j = 0; j < value; j++) {
                if (faceUpValues[i + j] == 0) {
                    consecutive = false;
                    break;
                }
            }
            if (consecutive) {
                return true;
            }
        }

        return false;
    }



    public static boolean fullHouse(ArrayList<Dice> diceList) {
        int[] faceUpValues = new int[6];
        for (Dice dice : diceList) {
            faceUpValues[dice.getFaceUpValue() - 1]++;
        }
        boolean threeOfAKind = false;
        boolean twoOfAKind = false;
        for (int i = 0; i < faceUpValues.length; i++) {
            if (faceUpValues[i] == 3) {
                threeOfAKind = true;
            }
            if (faceUpValues[i] == 2) {
                twoOfAKind = true;
            }
        }
        return threeOfAKind && twoOfAKind;
    }

    public static boolean oddsOnly(ArrayList<Dice> diceList) {
        for (Dice dice : diceList) {
            if (dice.getFaceUpValue() % 2 == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean evensOnly(ArrayList<Dice> diceList) {
        for (Dice dice : diceList) {
            if (dice.getFaceUpValue() % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean twoPair(ArrayList<Dice> diceList) {
        int[] faceUpValues = new int[6];
        for (Dice dice : diceList) {
            faceUpValues[dice.getFaceUpValue() - 1]++;
        }
        int pairs = 0;
        for (int i = 0; i < faceUpValues.length; i++) {
            if (faceUpValues[i] == 2) {
                pairs++;
            }
        }
        return pairs == 2;
    }

    public static void main(String[] args) {
        ArrayList<Dice> diceList = new ArrayList<Dice>();
        for (int i = 0; i < 5; i++) {
            diceList.add(new Rod_Components());
        }
//        print dice values
        for (Dice dice : diceList) {
            System.out.println(dice.getFaceUpValue());
        }
        CheckRequirements checkRequirements = new CheckRequirements();
        System.out.println("ofAKind: " + checkRequirements.ofAKind(diceList, 3));
        System.out.println("straight: " + checkRequirements.straight(diceList, 3));
        System.out.println("full house: " + checkRequirements.fullHouse(diceList));
        System.out.println("odds only: " + checkRequirements.oddsOnly(diceList));
        System.out.println("evens only: " + checkRequirements.evensOnly(diceList));
        System.out.println("greater than: " + checkRequirements.greaterThan(diceList, 10));
        System.out.println("less than: " + checkRequirements.lessThan(diceList, 10));
        System.out.println("equal to: " + checkRequirements.equalTo(diceList, 15));
        System.out.println("two pair: " + checkRequirements.twoPair(diceList));
    }
}
