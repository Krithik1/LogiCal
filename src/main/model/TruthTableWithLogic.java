package model;

import org.json.JSONArray;
import org.json.JSONObject;

// Represents the truth tables along with numVariables and Logic
public class TruthTableWithLogic {
    //Fields
    private int numVariables;
    private int[] truthtableinputs;
    private String logic;

    //REQUIRES: 0 < numvariables <= 4; truthtableinputs.length = 2**(numVariables)
    //MODIFIES: this
    //EFFECTS : Constructs an object with the truth table, numVariables and logic
    public TruthTableWithLogic(int numVariables, int[] truthtableinputs, String logic) {
        this.numVariables = numVariables;
        this.truthtableinputs = truthtableinputs;
        this.logic = logic;
    }

    //EFFECTS : returns the number of variables
    public int getNumVariables() {
        return numVariables;
    }

    //EFFECTS : returns the truthTableInputs
    public int[] getTruthtableinputs() {
        return truthtableinputs;
    }

    //EFFECTS : returns the logic
    public String getLogic() {
        return logic;
    }

    //EFFECTS : returns the class as a JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("numvariables", numVariables);
        json.put("truthtableinputs", inputsToJson(truthtableinputs));
        json.put("logic", logic);
        return json;
    }

    //EFFECTS : Converts the truthtableinputs to a JSONArray
    private JSONArray inputsToJson(int[] truthtableinputs) {
        JSONArray jsonArray = new JSONArray();

        for (int i : truthtableinputs) {
            jsonArray.put(i);
        }

        return jsonArray;
    }
}
