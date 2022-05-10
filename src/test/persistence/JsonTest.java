package persistence;

import model.LogiCal;
import model.TruthTableWithLogic;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    public void checkLogical(String name, ArrayList<TruthTableWithLogic> lottwl, String lolstring, String implolstring, LogiCal logical) {
        assertEquals(name, logical.getName());
        assertEquals(lottwl, logical.getTruthTables());
        assertEquals(lolstring, logical.getLogsString());
        assertEquals(implolstring, logical.getImplolString());
    }
}
