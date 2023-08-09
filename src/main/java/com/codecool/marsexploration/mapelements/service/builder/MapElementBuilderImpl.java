package com.codecool.marsexploration.mapelements.service.builder;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.mapelements.model.MapElement;

import java.util.Arrays;

public class MapElementBuilderImpl implements MapElementBuilder {

    private final DimensionCalculator dimensionCalculator;
    private final CoordinateCalculator coordinateCalculator;

    public MapElementBuilderImpl(DimensionCalculator dimensionCalculator, CoordinateCalculator coordinateCalculator) {
        this.dimensionCalculator = dimensionCalculator;
        this.coordinateCalculator = coordinateCalculator;
    }

    @Override
    public MapElement build(int size, String symbol, String name, int dimensionGrowth, String preferredLocationSymbol) {
        int dimension = dimensionCalculator.calculateDimension(size, dimensionGrowth);
        String[][] representation = new String[dimension][dimension];

        replaceNullWithEmptyStrings(representation);

        Coordinate randomCoordinate = coordinateCalculator.getRandomCoordinate(dimension);
        System.out.println(randomCoordinate.x());
        System.out.println(randomCoordinate.y());
//        Iterable<Coordinate> adjacentCoordinates = coordinateCalculator.getAdjacentCoordinates(randomCoordinate, dimension);
//        System.out.println(adjacentCoordinates);
//        Iterable<Coordinate> moreAdjacentCoordinates = coordinateCalculator.getAdjacentCoordinates(adjacentCoordinates, dimension);
//        System.out.println(moreAdjacentCoordinates);

//        System.out.println(name);
//        for (String [] rep : representation) {
//            System.out.println(Arrays.toString(rep));
//        }

        return new MapElement(
                representation,
                name,
                dimension,
                preferredLocationSymbol);
    }

    private static void replaceNullWithEmptyStrings(String[][] representation) {
        for (String[] row : representation) {
            Arrays.fill(row, "");
        }
    }
}
