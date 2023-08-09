package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateCalculatorImplTest {

    private CoordinateCalculator coordinateCalculator;
    private int dimension;


    @BeforeEach
    void setUp() {
        coordinateCalculator = new CoordinateCalculatorImpl();
        dimension = 5;
    }

    @Test
    void getRandomCoordinate() {
        Random random = new Random();
        int x = random.nextInt(dimension);
        int y = random.nextInt(dimension);
        Coordinate expected = new Coordinate(x, y);

        Coordinate actual = coordinateCalculator.getRandomCoordinate(dimension);

        assertNotNull(actual);
    }

    @Test
    void getAdjacentCoordinates() {
        List<Coordinate> actualList = new ArrayList<>();
        List<Coordinate> expectedList = new ArrayList<>();
        dimension = 1;
        Coordinate coordinate = new Coordinate(3, 4);
        Iterable<Coordinate> expected = List.of(
                new Coordinate(2, 3),
                new Coordinate(2, 4),
                new Coordinate(2, 5),
                new Coordinate(3, 3),
                new Coordinate(3, 4),
                new Coordinate(3, 5),
                new Coordinate(4, 3),
                new Coordinate(4, 4),
                new Coordinate(4, 5)
        );

        Iterable<Coordinate> actual = coordinateCalculator.getAdjacentCoordinates(coordinate, dimension);
        expected.forEach(expectedList::add);
        actual.forEach(actualList::add);

        assertEquals(expectedList.size(), actualList.size());
        for (Coordinate expectedCoord : expectedList) {
            assertTrue(actualList.contains(expectedCoord));
        }
    }
}
