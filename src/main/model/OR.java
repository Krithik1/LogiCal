package model;

import java.util.ArrayList;

//Represents the logical OR gate
public class OR extends Logic {
    //Fields
    String logic = "";

    //MODIFIES : this
    //EFFECTS  : constructs a NOT operator with l.size() operands
    public OR(ArrayList<Logic> l) {
        for (Logic i : l) {
            logic += "" + i.toString() + " V ";
        }
        if (!logic.equals("")) {
            logic = logic.substring(0,logic.length() - 3);
        }
    }

    //EFFECTS  : returns the logic string
    @Override
    public String toString() {
        return logic;
    }
}
