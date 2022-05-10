package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//UNIT TESTS FOR LOGIC CLASS
public class LogicTest {
    Logic l1;
    Logic l2;

    @BeforeEach
    void setup() {
        l1 = new Logic();
        l2 = new Logic("A");
    }

    @Test
    void testToString() {
        assertEquals(l1.toString(), "");
        assertEquals(l2.toString(), "A");
    }
}
