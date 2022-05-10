package persistence;

import model.LogiCal;
import model.TruthTableWithLogic;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//Represents the JSONReader class
public class JsonReader {
    private String source;

    // The following method is taken from the JsonReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: returns true if file is empty else false
    public boolean checkEmpty() throws IOException {
        String jsonData = readFile(source);
        if (jsonData.length() == 0) {
            return true;
        }
        return false;
    }

    // The following method is taken from the JsonReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: reads logical from file and returns it;
    // throws IOException if an error occurs reading data from file
    public LogiCal read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLogicCal(jsonObject);
    }

    // The following method is taken from the JsonReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // The following method is taken from the JsonReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: parses logical from JSON object and returns it
    private LogiCal parseLogicCal(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String lolstring = jsonObject.getString("listoflogs");
        String implolstring = jsonObject.getString("listofimplogs");
        LogiCal lc = new LogiCal(name, lolstring, implolstring);
        addTruthTables(lc, jsonObject);
        return lc;
    }

    // The following method is taken from the JsonReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: lc
    // EFFECTS: parses truthtables from JSON object and adds them to logical
    private void addTruthTables(LogiCal lc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("truthtables");
        for (Object json : jsonArray) {
            JSONObject nextTruthTable = (JSONObject) json;
            addTruthTable(lc, nextTruthTable);
        }
    }

    // MODIFIES: lc
    // EFFECTS: parses truthtable from JSON object and adds it to logical
    private void addTruthTable(LogiCal lc, JSONObject jsonObject) {
        int numVariables = jsonObject.getInt("numvariables");
        JSONArray truthTableInputs = jsonObject.getJSONArray("truthtableinputs");
        int[] inputs = new int[truthTableInputs.length()];
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = truthTableInputs.optInt(i);
        }
        String logic  = jsonObject.getString("logic");
        TruthTableWithLogic ttwl = new TruthTableWithLogic(numVariables, inputs, logic);
        lc.addTruthTable(ttwl);
    }
}
