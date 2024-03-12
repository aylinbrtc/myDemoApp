package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

public class AppTest {

    @Test
    public void testNullIntegerArrays() {
        App app = new App();
        assertThrows(IllegalArgumentException.class, () -> app.Decoder(null, null, new ArrayList<>(), 1));
    }

    @Test
    public void testNullStringArray() {
        App app = new App();
        assertThrows(IllegalArgumentException.class, () -> app.Decoder(new ArrayList<>(), new ArrayList<>(), null, 1));
    }

    @Test
    public void testInvalidCharacters() {
        App app = new App();
        assertThrows(ArithmeticException.class, () -> app.Decoder(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(Arrays.asList("a**", "a*c")), 1));
    }

    @Test
    public void testDifferentLengthArrays() {
        ArrayList<Integer> intArray1 = new ArrayList<>();
        intArray1.add(1);
        intArray1.add(2);
        intArray1.add(3);

        ArrayList<Integer> intArray2 = new ArrayList<>();
        intArray2.add(4);
        intArray2.add(5);

        ArrayList<String> stringArray = new ArrayList<>();
        stringArray.add("aylin");
        stringArray.add("AAA");
        stringArray.add("000");
        stringArray.add("aaa");

        int value = 10;

        App app = new App();
        ArrayList<String> result = app.Decoder(intArray1, intArray2, stringArray, value);

        assertEquals(4, result.size());
    }

    @Test
    public void testPositiveShiftValue() {
        ArrayList<Integer> intArray1 = new ArrayList<>();
        intArray1.add(1);
        intArray1.add(2);
        intArray1.add(3);

        ArrayList<Integer> intArray2 = new ArrayList<>();
        intArray2.add(4);
        intArray2.add(5);
        intArray2.add(6);

        ArrayList<String> stringArray = new ArrayList<>();
        stringArray.add("AAA");
        stringArray.add("000");
        stringArray.add("aaa");

        int value = 2;

        App app = new App();
        ArrayList<String> result = app.Decoder(intArray1, intArray2, stringArray, value);

        assertEquals("FHJ", result.get(0));
        assertEquals("791", result.get(1));
        assertEquals("jln", result.get(2));
    }

    @Test
    public void testNegativeShiftValue() {
        ArrayList<Integer> intArray1 = new ArrayList<>();
        intArray1.add(1);
        intArray1.add(2);
        intArray1.add(3);

        ArrayList<Integer> intArray2 = new ArrayList<>();
        intArray2.add(4);
        intArray2.add(5);
        intArray2.add(6);

        ArrayList<String> stringArray = new ArrayList<>();
        stringArray.add("AAA");
        stringArray.add("000");
        stringArray.add("aaa");

        int value = -2;

        App app = new App();
        ArrayList<String> result = app.Decoder(intArray1, intArray2, stringArray, value);

        assertEquals("FDB", result.get(0));
        assertEquals("753", result.get(1));
        assertEquals("jhf", result.get(2));
    }
}
