package persistence;

import model.LogiCal;
import model.TruthTableWithLogic;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            LogiCal lc = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyLogiCal() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLogiCal.json");
        try {
            assertTrue(reader.checkEmpty());
        } catch (IOException e) {
            fail("Not supposed to happen");
        }
    }

    @Test
    void testReaderGeneralLogiCal() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLogiCal.json");
        try {
            LogiCal lc = reader.read();
            assertFalse(reader.checkEmpty());
            assertEquals("Krithik's Workspace", lc.getName());
            ArrayList<TruthTableWithLogic> lottwl = lc.getTruthTables();
            assertEquals(2, lottwl.size());
            checkLogical(lc.getName(), lottwl, "NumVariables: 1\nInput List:  [1, 1]\nLogic: 1\n\nNumVariables: 2\nInput List:  [1, 0, 0, 0]\nLogic: (~B ^ ~A)\n\n", "", lc);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
