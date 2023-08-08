package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CoordinateCalculatorImpl implements CoordinateCalculator {

    @Override
    public Coordinate getRandomCoordinate(int dimension) {
        Random random = new Random();
        int x = random.nextInt(dimension);
        int y = random.nextInt(dimension);
        return new Coordinate(x, y);
    }

    @Override
    public Iterable<Coordinate> getAdjacentCoordinates(Coordinate coordinate, int dimension) {
        List<Coordinate> adjacentCoordinates = new ArrayList<>();

        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            for (int yOffset = -1; yOffset <= 1; yOffset++) {
                int newX = coordinate.x() + xOffset;
                int newY = coordinate.y() + yOffset;

                if (newX >= 0 && newX < dimension && newY >= 0 && newY < dimension &&
                        (xOffset != 0 || yOffset != 0)) {
                    adjacentCoordinates.add(new Coordinate(newX, newY));
                }
            }
        }

        return adjacentCoordinates;
    }

    @Override
    public Iterable<Coordinate> getAdjacentCoordinates(Iterable<Coordinate> coordinates, int dimension) {
        List<Coordinate> allAdjacentCoordinates = new ArrayList<>();

        for (Coordinate coordinate : coordinates) {
            Iterable<Coordinate> adjacentCoordinates = getAdjacentCoordinates(coordinate, dimension);
            allAdjacentCoordinates.addAll((List<Coordinate>) adjacentCoordinates);
        }

        return allAdjacentCoordinates;
    }
}
