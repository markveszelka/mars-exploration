package com.codecool.marsexploration.calculators.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.of;

class DimensionCalculatorImplTest {

    public static Stream<Arguments> parameters() {
        return Stream.of(
                of(8, 20, 3),
                of(7, 16, 3),
                of(11, 32, 5)
        );
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void calculateDimension(int expectedDimension, int size, int dimensionGrowth) {
        DimensionCalculator dimensionCalculator = new DimensionCalculatorImpl();

        int actualDimension = dimensionCalculator.calculateDimension(size, dimensionGrowth);

        assertEquals(expectedDimension, actualDimension);
    }
}