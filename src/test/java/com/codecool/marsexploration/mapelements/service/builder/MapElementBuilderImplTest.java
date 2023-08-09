package com.codecool.marsexploration.mapelements.service.builder;

import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorImpl;
import com.codecool.marsexploration.mapelements.model.MapElement;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapElementBuilderImplTest {

    @Test
    void build() {
        // ARRANGE
        DimensionCalculator dimensionCalculator = new DimensionCalculatorImpl();
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl();
        MapElementBuilder mapElementBuilder = new MapElementBuilderImpl(dimensionCalculator, coordinateCalculator);

        // ACT
        MapElement mapElement = mapElementBuilder.build(
                20,
                "#",
                "mountain",
                3,
                "");

        String[][] representation = mapElement.getRepresentation();
        int actualDimension = representation.length;
        int expectedDimension = dimensionCalculator.calculateDimension(20, 3);

        // ASSERT
        int counter = 0;
        for (String[] row : representation) {
            for (String element : row) {
                if (element.equals("#")) counter++;
            }
        }

        assertEquals(20, counter);
        assertEquals(expectedDimension, actualDimension);

    }
}