package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//UNIT TESTS FOR NOT CLASS
public class NOTTest {
    Logic A = new Logic("A");
    Logic B = new Logic("B");
    NOT l1;
    NOT l2;

    @BeforeEach
    void setup() {
        l1 = new NOT(A);
        l2 = new NOT(B);
    }

    @Test
    void testToString() {
        assertEquals(l1.toString(), "~A");
        assertEquals(l2.toString(), "~B");
    }
}
