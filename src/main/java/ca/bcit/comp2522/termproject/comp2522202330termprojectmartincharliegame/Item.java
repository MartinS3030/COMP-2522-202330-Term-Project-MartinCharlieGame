package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

/**
 * Represents an interface for items.
 *
 * @author Martin Siu, Charlie Zhang
 * @version 2023
 */
public interface Item {
    public int getValue();
    public String getName();
    public int getAmount();

    public void setAmount(int amount);
}
