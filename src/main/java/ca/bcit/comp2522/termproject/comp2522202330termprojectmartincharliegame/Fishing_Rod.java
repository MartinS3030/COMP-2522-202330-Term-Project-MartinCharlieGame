package ca.bcit.comp2522.termproject.comp2522202330termprojectmartincharliegame;

import java.io.Serializable;
import java.util.ArrayList;

public class Fishing_Rod implements Serializable {
    private final ArrayList<Dice> Components = new ArrayList<>();

    public Fishing_Rod(Rod_Components rodComponent1, Rod_Components rodComponent2, Rod_Components rodComponent3,
                       Rod_Components rodComponent4, Rod_Components rodComponent5) {
        Components.add(rodComponent1);
        Components.add(rodComponent2);
        Components.add(rodComponent3);
        Components.add(rodComponent4);
        Components.add(rodComponent5);
    }

    public Fishing_Rod() {
        Components.add(new Rod_Components());
        Components.add(new Rod_Components());
        Components.add(new Rod_Components());
        Components.add(new Rod_Components());
        Components.add(new Rod_Components());
    }

    public ArrayList<Dice> getComponents() {
        return Components;
    }

}
