package com.codecool.marsexploration.calculators.service;

public class DimensionCalculatorImpl implements DimensionCalculator{

    @Override
    public int calculateDimension(int size, int dimensionGrowth) {
        int squareRoot = (int) Math.round(Math.sqrt(size));
        int actualDimension;
        if (squareRoot * squareRoot < size) {
            actualDimension = squareRoot + 1;
            return actualDimension + dimensionGrowth;
        }
        return squareRoot + dimensionGrowth;
    }
}
