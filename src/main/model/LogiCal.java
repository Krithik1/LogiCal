package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Represents the LogiCal class that has to be written in the json class
public class LogiCal implements Writable {
    //Fields
    private String name;
    private ArrayList<TruthTableWithLogic> truthTables;
    private String lolstring;
    private String implolstring;

    //MODIFIES: this
    //EFFECTS : constructs the LogiCal class
    public LogiCal(String name, String lolstring, String implolstring) {
        this.name = name;
        truthTables = new ArrayList<TruthTableWithLogic>();
        this.lolstring = lolstring;
        this.implolstring = implolstring;
    }

    //EFFECTS : returns the name of the workspace
    public String getName() {
        return name;
    }

    //EFFECTS : returns the list of truth tables with logic
    public ArrayList<TruthTableWithLogic> getTruthTables() {
        return truthTables;
    }

    //EFFECTS : returns the list of logs as a string
    public String getLogsString() {
        return lolstring;
    }

    //EFFECTS : returns the list of important logs as a string
    public String getImplolString() {
        return implolstring;
    }

    //MODIFIES: this
    //EFFECTS : adds the truth table to the list of truth tables
    public void addTruthTable(TruthTableWithLogic t) {
        truthTables.add(t);
    }

    //MODIFIES: this
    //EFFECTS : updates the list of logs
    public void updateLogs(String lolstring) {
        this.lolstring += lolstring;
    }

    //MODIFIES: this
    //EFFECTS : updates the list of important logs
    public void updateImpLogs(String implolstring) {
        this.implolstring = implolstring;
    }

    //EFFECTS : returns the class as a json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("truthtables", truthTablesToJson());
        json.put("listoflogs", lolstring);
        json.put("listofimplogs", implolstring);
        return json;
    }

    //EFFECTS : transforms the truth table to json
    private JSONArray truthTablesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (TruthTableWithLogic t : truthTables) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
