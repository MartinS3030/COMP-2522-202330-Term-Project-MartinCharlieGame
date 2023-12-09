package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import java.util.ArrayList;

/**
 * A class that checks if the dice values meet the requirements of a fish.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public final class CheckRequirements {
    private CheckRequirements() { }

    /**
     * Checks if the dice values meet the requirements of a fish.
     *
     * @param diceList the list of dice
     * @param fish the fish
     * @return true if the dice values meet the requirements of a fish
     */
    public static boolean checkAgainstFish(final ArrayList<Dice> diceList, final Fish fish) {
        return switch (fish.getRequirementType()) {
            case "ofakind" -> ofAKind(diceList, fish.getRequirementValue());
            case "straight" -> straight(diceList, fish.getRequirementValue());
            case "fullHouse" -> fullHouse(diceList);
            case "odds" -> oddsOnly(diceList);
            case "evens" -> evensOnly(diceList);
            case "greater" -> greaterThan(diceList, fish.getRequirementValue());
            case "less" -> lessThan(diceList, fish.getRequirementValue());
            case "equalTo" -> equalTo(diceList, fish.getRequirementValue());
            case "twoPair" -> twoPair(diceList);
            default -> false;
        };
    }

    /**
     * Checks if the dice values meet the greater than requirements of a fish.
     *
     * @param diceList the list of dice
     * @param value the value to check against as an int
     * @return true if the dice values meet the greater than requirements of a fish
     */
    public static boolean greaterThan(final ArrayList<Dice> diceList, final int value) {
        int sum = 0;
        for (Dice dice : diceList) {
            sum += dice.getFaceUpValue();
        }
        return sum > value;
    }

    /**
     * Checks if the dice values meet the less than requirements of a fish.
     *
     * @param diceList the list of dice
     * @param value the value to check against as an int
     * @return true if the dice values meet the less than requirements of a fish
     */
    public static boolean lessThan(final ArrayList<Dice> diceList, final int value) {
        int sum = 0;
        if (diceList.size() != 5) {
            return false;
        }
        for (Dice dice : diceList) {
            sum += dice.getFaceUpValue();
        }
        return sum < value;
    }

    /**
     * Checks if the dice values meet the equal to requirements of a fish.
     *
     * @param diceList the list of dice
     * @param value the value to check against as an int
     * @return true if the dice values meet the equal to requirements of a fish
     */
    public static boolean equalTo(final ArrayList<Dice> diceList, final int value) {
        int sum = 0;
        for (Dice dice : diceList) {
            sum += dice.getFaceUpValue();
        }
        return sum == value;
    }

    /**
     * Checks if the dice values meet the of a kind requirements of a fish.
     *
     * @param diceList the list of dice
     * @param value the number of dice that need to be of a kind as an int
     * @return true if the dice values meet the of a kind requirements of a fish
     */
    public static boolean ofAKind(final ArrayList<Dice> diceList, final int value) {
        int[] faceUpValues = new int[6];
        for (Dice dice : diceList) {
            faceUpValues[dice.getFaceUpValue() - 1]++;
        }
        for (int faceUpValue : faceUpValues) {
            if (faceUpValue == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the dice values meet the straight requirements of a fish.
     *
     * @param diceList the list of dice
     * @param value the number of dice required for the straight as an int
     * @return true if the dice values meet the straight requirements of a fish
     */
    public static boolean straight(final ArrayList<Dice> diceList, final int value) {
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

    /**
     * Checks if the dice values meet the full house requirements of a fish.
     *
     * @param diceList the list of dice
     * @return true if the dice values meet the full house requirements of a fish
     */
    public static boolean fullHouse(final ArrayList<Dice> diceList) {
        int[] faceUpValues = new int[6];
        for (Dice dice : diceList) {
            faceUpValues[dice.getFaceUpValue() - 1]++;
        }
        boolean threeOfAKind = false;
        boolean twoOfAKind = false;
        for (int faceUpValue : faceUpValues) {
            if (faceUpValue == 3) {
                threeOfAKind = true;
            }
            if (faceUpValue == 2) {
                twoOfAKind = true;
            }
        }
        return threeOfAKind && twoOfAKind;
    }

    /**
     * Checks if the dice values meet the odds only requirements of a fish.
     *
     * @param diceList the list of dice
     * @return true if the dice values meet the odds only requirements of a fish
     */
    public static boolean oddsOnly(final ArrayList<Dice> diceList) {
        if (diceList.size() != 5) {
            return false;
        }
        for (Dice dice : diceList) {
            if (dice.getFaceUpValue() % 2 == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the dice values meet the evens only requirements of a fish.
     *
     * @param diceList the list of dice
     * @return true if the dice values meet the evens only requirements of a fish
     */
    public static boolean evensOnly(final ArrayList<Dice> diceList) {
        if (diceList.size() != 5) {
            return false;
        }
        for (Dice dice : diceList) {
            if (dice.getFaceUpValue() % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the dice values meet the two pair requirements of a fish.
     *
     * @param diceList the list of dice
     * @return true if the dice values meet the two pair requirements of a fish
     */
    public static boolean twoPair(final ArrayList<Dice> diceList) {
        int[] faceUpValues = new int[6];
        for (Dice dice : diceList) {
            faceUpValues[dice.getFaceUpValue() - 1]++;
        }
        int pairs = 0;
        for (int faceUpValue : faceUpValues) {
            if (faceUpValue == 2) {
                pairs++;
            }
        }
        return pairs == 2;
    }
}
