package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//UNIT TESTS FOR KARNAUGHMAP CLASS
class KarnaughMapTest {
    KarnaughMap kmap11;
    KarnaughMap kmap12;
    KarnaughMap kmap13;
    KarnaughMap kmap14;
    KarnaughMap kmap21;
    KarnaughMap kmap22;
    KarnaughMap kmap23;
    KarnaughMap kmap24;
    KarnaughMap kmap25;
    KarnaughMap kmap26;
    KarnaughMap kmap31;
    KarnaughMap kmap32;
    KarnaughMap kmap33;
    KarnaughMap kmap34;
    KarnaughMap kmap35;
    KarnaughMap kmap36;
    KarnaughMap kmap41;
    KarnaughMap kmap42;
    KarnaughMap kmap43;
    KarnaughMap kmap44;
    KarnaughMap kmap45;
    KarnaughMap kmap46;
    KarnaughMap kmap47;
    KarnaughMap kmap48;
    KarnaughMap kmap49;
    KarnaughMap kmap50;

    @BeforeEach
    void setup() {
        int[] input11 = {0, 0};
        kmap11 = new KarnaughMap(1, input11);
        int[] input12 = {0, 1};
        kmap12 = new KarnaughMap(1, input12);
        int[] input13 = {1, 0};
        kmap13 = new KarnaughMap(1, input13);
        int[] input14 = {1, 1};
        kmap14 = new KarnaughMap(1, input14);
        int[] input21 = {1, 1, 1, 1};
        kmap21 = new KarnaughMap(2, input21);
        int[] input22 = {1, 1, 1, 0};
        kmap22 = new KarnaughMap(2, input22);
        int[] input23 = {0, 1, 1, 1};
        kmap23 = new KarnaughMap(2, input23);
        int[] input24 = {1, 0, 0, 1};
        kmap24 = new KarnaughMap(2, input24);
        int[] input25 = {0, 1, 1, 0};
        kmap25 = new KarnaughMap(2, input25);
        int[] input26 = {0, 1, 0, 1};
        kmap26 = new KarnaughMap(2, input26);
        int[] input31 = {1, 1, 1, 0, 1, 1, 1, 1};
        kmap31 = new KarnaughMap(3, input31);
        int[] input32 = {1, 1, 0, 1, 0, 1, 1, 1};
        kmap32 = new KarnaughMap(3, input32);
        int[] input33 = {1, 1, 1, 1, 1, 0, 1, 0};
        kmap33 = new KarnaughMap(3, input33);
        int[] input34 = {0, 0, 1, 0, 1, 1, 0, 1};
        kmap34 = new KarnaughMap(3, input34);
        int[] input35 = {0, 1, 0, 1, 1, 0, 1, 0};
        kmap35 = new KarnaughMap(3, input35);
        int[] input36 = {1, 0, 1, 1, 0, 1, 1, 1};
        kmap36 = new KarnaughMap(3, input36);
        int[] input41 = {1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1};
        kmap41 = new KarnaughMap(4, input41);
        int[] input42 = {0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0};
        kmap42 = new KarnaughMap(4, input42);
        int[] input43 = {0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0};
        kmap43 = new KarnaughMap(4, input43);
        int[] input44 = {1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0};
        kmap44 = new KarnaughMap(4, input44);
        int[] input45 = {1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0};
        kmap45 = new KarnaughMap(4, input45);
        int[] input46 = {1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1};
        kmap46 = new KarnaughMap(4, input46);
        int[] input47 = {0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        kmap47 = new KarnaughMap(4, input47);
        int[] input48 = {0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1};
        kmap48 = new KarnaughMap(4, input48);
        int[] input49 = {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0};
        kmap49 = new KarnaughMap(4, input49);
        int[] input50 = {0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0};
        kmap50 = new KarnaughMap(4, input50);
    }

    @Test
    void testFormLogic() {
        assertEquals(kmap11.formLogic(), "0");
        assertEquals(kmap12.formLogic(), "A");
        assertEquals(kmap13.formLogic(), "~A");
        assertEquals(kmap14.formLogic(), "1");
        assertEquals(kmap21.formLogic(), "1");
        assertEquals(kmap22.formLogic(), "(~B) V (~A)");
        assertEquals(kmap23.formLogic(), "(B) V (~B ^ A)");
        assertEquals(kmap24.formLogic(), "(~B ^ ~A) V (B ^ A)");
        assertEquals(kmap25.formLogic(), "(~B ^ A) V (B ^ ~A)");
        assertEquals(kmap26.formLogic(), "(~B ^ A) V (B ^ A)");
        assertEquals(kmap31.formLogic(), "(~A) V (B) V (A ^ ~B ^ C)");
        assertEquals(kmap32.formLogic(), "(~A ^ ~C) V (~A ^ B) V (B ^ C) V (A ^ C) V (A ^ ~B ^ ~C)");
        assertEquals(kmap33.formLogic(), "(~A ^ ~C) V (~A ^ ~B) V (B ^ ~C) V (A ^ ~C) V (A ^ B)");
        assertEquals(kmap34.formLogic(), "(~A ^ C) V (A ^ B ^ ~C) V (A ^ ~B ^ C)");
        assertEquals(kmap35.formLogic(), "(~A ^ B ^ ~C) V (A ^ ~B ^ ~C) V (~A ^ ~B ^ C) V (A ^ B ^ C)");
        assertEquals(kmap36.formLogic(), "(A) V (~A ^ ~B ^ ~C) V (~A ^ B ^ C)");
        assertEquals(kmap41.formLogic(), "(~C ^ ~D ^ ~A ^ ~B) V (C ^ ~D ^ ~A ^ ~B) V (~C ^ D ^ ~A ^ B) V (C ^ D ^ A ^ B) V (~C ^ ~D ^ A ^ ~B) V (C ^ ~D ^ A ^ ~B)");
        assertEquals(kmap42.formLogic(), "(C ^ D ^ B) V (C ^ D ^ A) V (A ^ ~B ^ C) V (~C ^ D ^ ~A ^ ~B) V (~C ^ ~D ^ ~A ^ B)");
        assertEquals(kmap43.formLogic(), "(C ^ D ^ ~A ^ ~B) V (C ^ ~D ^ ~A ^ B) V (~C ^ ~D ^ A ^ B) V (~C ^ D ^ A ^ ~B)");
        assertEquals(kmap44.formLogic(), "(~C ^ ~D ^ ~A) V (~A ^ ~B ^ ~C) V (~C ^ ~D ^ B) V (~C ^ ~D ^ A) V (~A ^ ~B ^ D) V (~A ^ ~B ^ C) V (C ^ D ^ A) V (C ^ ~D ^ ~A)");
        assertEquals(kmap45.formLogic(), "(~A ^ ~C) V (C ^ D ^ B)");
        assertEquals(kmap46.formLogic(), "(~C ^ ~D ^ ~A) V (~A ^ B ^ ~C) V (~C ^ D ^ B) V (~A ^ B ^ D) V (~C ^ D ^ A) V (C ^ D ^ ~A) V (~A ^ B ^ C) V (C ^ ~D ^ B) V (C ^ ~D ^ A) V (C ^ ~D ^ ~A ^ ~B) V (C ^ ~D ^ A ^ B)");
        assertEquals(kmap47.formLogic(), "(B ^ ~C) V (A ^ ~C) V (B ^ D) V (A ^ D) V (~A ^ C) V (B ^ C) V (A ^ C)");
        assertEquals(kmap48.formLogic(), "(A ^ ~B ^ ~C) V (A ^ ~B ^ C)");
        assertEquals(kmap49.formLogic(), "(A ^ ~B ^ D)");
        assertEquals(kmap50.formLogic(), "(C ^ D ^ ~A ^ B) V (~C ^ D ^ A ^ B) V (C ^ D ^ A ^ ~B)");
    }
}