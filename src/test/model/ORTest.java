package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

//UNIT TESTS FOR OR CLASS
public class ORTest {
    Logic A = new Logic("A");
    Logic B = new Logic("B");
    Logic C = new Logic("C");
    OR l1;
    OR l2;

    @BeforeEach
    void setup() {
        ArrayList<Logic> orlst1 = new ArrayList<Logic>();
        orlst1.add(A);
        orlst1.add(B);
        l1 = new OR(orlst1);
        orlst1.add(C);
        l2 = new OR(orlst1);
    }

    @Test
    void testToString() {
        assertEquals(l1.toString(), "A V B");
        assertEquals(l2.toString(), "A V B V C");
    }
}
