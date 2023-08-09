package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorImpl;
import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilder;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilderImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapElementsGeneratorImplTest {

    private static MapConfiguration getConfiguration() {
        final String mountainSymbol = "#";
        final String pitSymbol = "&";
        final String mineralSymbol = "%";
        final String waterSymbol = "*";

        MapElementConfiguration mountainsCfg = new MapElementConfiguration(
                mountainSymbol,
                "mountain",
                List.of(
                        new ElementToSize(2, 20),
                        new ElementToSize(1, 30)
                ),
                3,
                ""
        );

        MapElementConfiguration pitsCfg = new MapElementConfiguration(
                pitSymbol,
                "pits",
                List.of(
                        new ElementToSize(2, 10),
                        new ElementToSize(1, 20)
                ),
                10,
                ""
        );

        List<MapElementConfiguration> elementsCfg = List.of(mountainsCfg, pitsCfg);
        return new MapConfiguration(625, 0.5, elementsCfg);
    }

    @Test
    void createAll() { // HAS TO BE WRITTEN WITH MOCKING
        // ARRANGE
        DimensionCalculator dimensionCalculator = new DimensionCalculatorImpl();
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl();
        MapElementBuilder mapElementBuilder = new MapElementBuilderImpl(dimensionCalculator, coordinateCalculator);
        MapElementsGenerator mapElementsGenerator = new MapElementsGeneratorImpl(mapElementBuilder);

        MapConfiguration mapConfig = getConfiguration();

        // ACT
        List<MapElement> mapElements = (List<MapElement>) mapElementsGenerator.createAll(mapConfig);

        // ASSERT
        int expected = 6;
        int actual = mapElements.size();

        assertEquals(expected, actual);
    }
}