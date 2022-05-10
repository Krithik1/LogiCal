package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//UNIT TESTS FOR LOG CLASS
public class LogTest {
    Log l1;
    Log l2;
    int[] input1;
    int[] input2;

    @BeforeEach
    void setup() {
        input1 = new int[]{1, 0};
        l1 = new Log(1, input1, "~(A)");
        input2 = new int[]{0, 1};
        l2 = new Log(1, input2, "A");
    }

    @Test
    void testConstructor() {
        assertEquals(1, l1.getNumVariables());
        assertEquals(input1, l1.getInput());
        assertEquals("~(A)", l1.getLogic());
    }

    @Test
    void testToString() {
        assertEquals(l1.toString(), "NumVariables: 1\nInput List:  [1, 0]\nLogic: ~(A)\n");
        assertEquals(l2.toString(), "NumVariables: 1\nInput List:  [0, 1]\nLogic: A\n");
    }
}
