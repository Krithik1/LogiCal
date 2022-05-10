package model;

import java.util.ArrayList;

//Represents the logical AND gate
public class AND extends Logic {
    //Fields
    String logic = "";

    //MODIFIES : this
    //EFFECTS  : constructs an AND operator with 2 operands
    public AND(Logic l1, Logic l2) {
        logic = "" + l1.toString() + " ^ " + l2.toString() + "";
    }

    //EFFECTS  : constructs an AND operator with l.size() operands
    public AND(ArrayList<Logic> l) {
        for (Logic i : l) {
            logic += i.toString() + " ^ ";
        }
        logic = logic.substring(0,logic.length() - 3);
    }

    //EFFECTS  : returns the logic string
    @Override
    public String toString() {
        return logic;
    }
}