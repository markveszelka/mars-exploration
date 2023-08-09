package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilder;

import java.util.ArrayList;
import java.util.List;

public class MapElementsGeneratorImpl implements MapElementsGenerator {

    MapElementBuilder mapElementFactory;

    public MapElementsGeneratorImpl(MapElementBuilder mapElementBuilder) {
        this.mapElementFactory = mapElementBuilder;
    }

    @Override
    public Iterable<MapElement> createAll(MapConfiguration mapConfig) {
        List<MapElement> mapElementsList = new ArrayList<>();
        List<MapElementConfiguration> mapElementConfigurations = mapConfig.mapElementConfigurations();

        for (MapElementConfiguration mapElementConfiguration : mapElementConfigurations) {
            int dimensionGrowth = mapElementConfiguration.dimensionGrowth();
            String name = mapElementConfiguration.name();
            String symbol = mapElementConfiguration.symbol();
            String preferredLocationSymbol = mapElementConfiguration.preferredLocationSymbol();
            List<ElementToSize> elementToSizesList = mapElementConfiguration.elementToSizes();

            for (ElementToSize element : elementToSizesList) {
                int size = element.size();
                MapElement mapElement = mapElementFactory.build(size, symbol, name, dimensionGrowth, preferredLocationSymbol);
                mapElementsList.add(mapElement);
            }
        }

        return mapElementsList;
    }
}
