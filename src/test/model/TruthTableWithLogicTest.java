package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Unit Tests for TruthTableWithLogic Class
public class TruthTableWithLogicTest {
    TruthTableWithLogic ttwl1;

    @BeforeEach
    void setup() {
        int[] inputs = {1,0,0,1};
        ttwl1 = new TruthTableWithLogic(2, inputs, "(~B ^ ~A) V (B ^ A)");
    }

    @Test
    void testToJson() {
        JSONArray arr = new JSONArray();
        for (int i : ttwl1.getTruthtableinputs()) {
            arr.put(i);
        }
        JSONObject json = ttwl1.toJson();
        json.put("numvariables", ttwl1.getNumVariables());
        json.put("truthtableinputs", arr);
        json.put("logic", ttwl1.getLogic());
        assertTrue(ttwl1.toJson().similar(json));
    }
}
