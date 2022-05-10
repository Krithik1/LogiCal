package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//UNIT TESTS FOR LISTOFLOG CLASS
public class ListOfLogsTest {
    ListOfLogs lol1;
    ListOfLogs lol2;
    int[] input1 = {1, 0};
    Log l1 = new Log(1, input1, "~(A)");
    int[] input2 = {0, 1};
    Log l2 = new Log(1, input2, "A");
    int[] input3 = {1, 1};
    Log l3 = new Log(1, input3, "1");

    @BeforeEach
    void setup() {
        lol1 = new ListOfLogs();
        lol2 = new ListOfLogs();
        lol1.addLog(l1);
        lol1.addLog(l2);
    }

    @Test
    void testAddLog() {
        ArrayList<Log> lst = new ArrayList<Log>();
        lst.add(l1);
        lst.add(l2);
        assertEquals(lol1.getLogs(), lst);
        lst.add(l3);
        lol2.addLog(l1);
        lol2.addLog(l2);
        lol2.addLog(l3);
        assertEquals(lol2.getLogs(), lst);
    }

    @Test
    void testDeleteLog() {
        ArrayList<Log> lst = new ArrayList<Log>();
        lst.add(l1);
        lst.add(l2);
        lol2.markImportant(l1);
        lol2.markImportant(l2);
        lol2.markImportant(l3);
        lol2.deleteLog(2);
        assertEquals(lol2.getImportant(), lst);
    }

    @Test
    void testMarkImportant() {
        ArrayList<Log> lst = new ArrayList<Log>();
        lst.add(l1);
        lol1.markImportant(l1);
        assertEquals(lol1.getImportant(), lst);
    }

    @Test
    void testToStringLogs() {
        assertEquals(lol1.toStringLogs(), "NumVariables: 1\nInput List:  [1, 0]\nLogic: ~(A)\n\nNumVariables: 1\nInput List:  [0, 1]\nLogic: A\n\n");
    }

    @Test
    void testToStringImpLogs() {
        lol1.markImportant(l2);
        assertEquals(lol1.toStringImportantLogs(), "NumVariables: 1\nInput List:  [0, 1]\nLogic: A\n\n");
    }
}
