package model;

//Represents the logical NOT gate
public class NOT extends Logic {
    //Fields
    String logic = "";

    //REQUIRES : l.toString() is not empty
    //MODIFIES : this
    //EFFECTS  : constructs a NOT operator with 1 operand
    public NOT(Logic l) {
        logic = "~" + l.toString() + "";
    }

    //EFFECTS  : returns the logic string
    @Override
    public String toString() {
        return logic;
    }
}
