package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;

import java.util.Random;

public class CoordinateCalculatorImpl implements CoordinateCalculator {

    @Override
    public Coordinate getRandomCoordinate(int dimension) {
        // dimension * dimension négyzeten belül keresek random koordinátát.
        Random random = new Random();
        int mapSize = (int) Math.floor(Math.sqrt(1000));
        int x = random.nextInt(mapSize);
        int y = random.nextInt(mapSize);
        return new Coordinate(x + dimension, y + dimension);
    }

    @Override
    public Iterable<Coordinate> getAdjacentCoordinates(Coordinate coordinate, int dimension) {

        return null;
    }

    @Override
    public Iterable<Coordinate> getAdjacentCoordinates(Iterable<Coordinate> coordinates, int dimension) {
        return null;
    }
}
