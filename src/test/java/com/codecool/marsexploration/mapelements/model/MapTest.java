package com.codecool.marsexploration.mapelements.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void isSuccessfullyGenerated() {
    }

    @Test
    void setSuccessfullyGenerated() {
    }

    @Test
    void testToString() {
        String[][] testArray = new String[][]{
                {"A", "B", "C"},
                {"D", "E", "F"}
        };
        Map map = new Map(testArray);

        String expected = "ABC\nDEF";
        String actual = map.toString();

        assertEquals(expected, actual);
    }
}
