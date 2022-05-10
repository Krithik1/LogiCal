package model;

import java.util.Arrays;

//Represents a log
public class Log {
    //Fields
    private int numVariables;
    private int[] input;
    private String logic;

    //MODIFIES : this
    //EFFECTS  : Creates a log object with the numvariables, truth table and logic
    public Log(int numVariables, int[] input, String logic) {
        this.numVariables = numVariables;
        this.input = input;
        this.logic = logic;
    }

    //EFFECTS  : returns the number of variables
    public int getNumVariables() {
        return numVariables;
    }

    //EFFECTS  : returns the list of inputs
    public int[] getInput() {
        return input;
    }

    //EFFECTS  : returns the logic
    public String getLogic() {
        return logic;
    }

    //EFFECTS  : returns the log as a string
    @Override
    public String toString() {
        String s = "";
        s += "NumVariables: " + numVariables + "\n";
        s += "Input List:  " + Arrays.toString(input) + "\nLogic: " + logic + "\n";
        return s;
    }
}
