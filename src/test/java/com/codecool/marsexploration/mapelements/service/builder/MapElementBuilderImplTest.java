package com.codecool.marsexploration.mapelements.service.builder;

import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorImpl;
import com.codecool.marsexploration.mapelements.model.MapElement;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

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
        // ASSERT
        String[][] representation = mapElement.getRepresentation();
        for (String [] rep : representation) {
//            System.out.println(Arrays.toString(rep));
        }
    }
}