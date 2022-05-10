package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

//UNIT TESTS FOR AND CLASS
public class ANDTest {
    Logic A = new Logic("A");
    Logic B = new Logic("B");
    Logic C = new Logic("C");
    AND l1;
    AND l2;

    @BeforeEach
    void setup() {
        l1 = new AND(A, B);
        ArrayList<Logic> lst = new ArrayList<Logic>();
        lst.add(A);
        lst.add(B);
        lst.add(C);
        l2 = new AND(lst);
    }

    @Test
    void testToString() {
        assertEquals(l1.toString(), "A ^ B");
        assertEquals(l2.toString(), "A ^ B ^ C");
    }
}
