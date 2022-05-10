package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//Unit Tests for LogiCal class
public class LogiCalTest {
    LogiCal lc;
    TruthTableWithLogic ttwl1;
    TruthTableWithLogic ttwl2;

    @BeforeEach
    void setup() {
        lc = new LogiCal("abc", "", "");
        int[] inputs1 = {1, 0, 0, 1};
        ttwl1 = new TruthTableWithLogic(2, inputs1, "(~B ^ ~A) V (B ^ A)");
        int[] inputs2 = {1, 1, 1, 1};
        ttwl2 = new TruthTableWithLogic(2, inputs2, "1");
    }

    @Test
    void testAddTruthTable() {
        ArrayList<TruthTableWithLogic> lottwl = new ArrayList<>();
        lc.addTruthTable(ttwl1);
        lottwl.add(ttwl1);
        assertEquals(lottwl, lc.getTruthTables());
        lc.addTruthTable(ttwl2);
        lottwl.add(ttwl2);
        assertEquals(lottwl, lc.getTruthTables());
    }

    @Test
    void testUpdateLogs() {
        lc.updateLogs("abc");
        assertEquals("abc", lc.getLogsString());
        lc.updateLogs("def");
        assertEquals("abcdef", lc.getLogsString());
    }

    @Test
    void testUpdateImpLogs() {
        lc.updateImpLogs("abc");
        assertEquals("abc", lc.getImplolString());
        lc.updateImpLogs("def");
        assertEquals("def", lc.getImplolString());
    }

    @Test
    void testToJson() {
        lc.addTruthTable(ttwl1);
        lc.addTruthTable(ttwl2);
        lc.updateLogs("abc");
        lc.updateImpLogs("def");

        ArrayList<TruthTableWithLogic> lottwl = new ArrayList<>();
        lottwl.add(ttwl1);
        lottwl.add(ttwl2);
        JSONArray jsonArr = new JSONArray();
        for (TruthTableWithLogic ttwl : lottwl) {
            jsonArr.put(ttwl.toJson());
        }
        JSONObject json = new JSONObject();
        json.put("name", lc.getName());
        json.put("truthtables", jsonArr);
        json.put("listoflogs", "abc");
        json.put("listofimplogs", "def");

        assertTrue(lc.toJson().similar(json));
    }
}
