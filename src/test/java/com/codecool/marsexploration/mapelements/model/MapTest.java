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
        String[][] stringArray = new String[3][4];
        stringArray[0][0] = "Row 0, Column 0";
        stringArray[0][1] = "Row 0, Column 1";
        stringArray[0][2] = "Row 0, Column 2";
        stringArray[0][3] = "Row 0, Column 3";

        stringArray[1][0] = "Row 1, Column 0";
        stringArray[1][1] = "Row 1, Column 1";
        stringArray[1][2] = "Row 1, Column 2";
        stringArray[1][3] = "Row 1, Column 3";

        stringArray[2][0] = "Row 2, Column 0";
        stringArray[2][1] = "Row 2, Column 1";
        stringArray[2][2] = "Row 2, Column 2";
        stringArray[2][3] = "Row 2, Column 3";

        String toString = "Row 0, Column 0Row 0, Column 1Row 0, Column 2Row 0, " +
                "Column 3Row 1, Column 0Row 1, Column 1Row 1, Column 2Row 1, " +
                "Column 3Row 2, Column 0Row 2, Column 1Row 2, Column 2Row 2, Column 3";

        Map map = new Map(stringArray);

        assertEquals(toString, map.toString());
    }
}