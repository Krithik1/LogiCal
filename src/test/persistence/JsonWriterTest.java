package persistence;

import model.LogiCal;
import model.TruthTableWithLogic;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            LogiCal lc = new LogiCal("", "", "");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyLogiCal() {
        try {
            LogiCal lc = new LogiCal("", "", "");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLogiCal.json");
            writer.open();
            writer.write(lc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLogiCal.json");
            lc = reader.read();
            checkLogical("", new ArrayList<TruthTableWithLogic>(), "", "", lc);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLogiCal() {
        try {
            LogiCal lc = new LogiCal("abc", "xyz", "def");
            int[] inputs1 = {1, 0, 0, 1};
            TruthTableWithLogic ttwl1 = new TruthTableWithLogic(2, inputs1, "(~B ^ ~A) V (B ^ A)");
            int[] inputs2 = {1, 1, 1, 1};
            TruthTableWithLogic ttwl2 = new TruthTableWithLogic(2, inputs2, "1");
            lc.addTruthTable(ttwl1);
            lc.addTruthTable(ttwl2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLogiCal.json");
            writer.open();
            writer.write(lc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLogiCal.json");
            lc = reader.read();
            ArrayList<TruthTableWithLogic> lottwl = lc.getTruthTables();
            assertEquals(2, lottwl.size());
            checkLogical("abc", lottwl, "xyz", "def", lc);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
