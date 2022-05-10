package model;

//Represents a general logic gate
public class Logic {
    //Fields
    private String logic = "";

    //MODIFIES : this
    //EFFECTS  : creates a logic operator with no operands
    public Logic() {
        this.logic = "";
    }

    //EFFECTS  : creates a logic operator with 1 operand
    public Logic(String logic) {
        this.logic = logic;
    }

    //EFFECTS  : returns the logic string
    @Override
    public String toString() {
        return logic;
    }
}
