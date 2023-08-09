package com.codecool.marsexploration.mapelements.service.builder;

import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorImpl;
import com.codecool.marsexploration.mapelements.model.MapElement;

public class MapElementBuilderImpl implements MapElementBuilder {

    DimensionCalculator dimensionCalculator;

    public MapElementBuilderImpl(DimensionCalculator dimensionCalculator) {
        this.dimensionCalculator = dimensionCalculator;
    }

    @Override
    public MapElement build(int size, String symbol, String name, int dimensionGrowth, String preferredLocationSymbol) {
        int dimension = dimensionCalculator.calculateDimension(size, dimensionGrowth);
        String[][] representation = new String[dimension][dimension];
        return new MapElement(
                representation,
                name,
                dimension,
                preferredLocationSymbol);
    }
}
