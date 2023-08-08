package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;

import java.util.List;

public class MapConfigurationValidatorImpl implements MapConfigurationValidator {
    @Override
    public boolean validate(MapConfiguration mapConfig) {
        boolean isConfigValid = false;
        int mapSize = mapConfig.mapSize();
        List<MapElementConfiguration> mapElementConfigurationList = mapConfig.mapElementConfigurations();
        double elementToSpaceRatio = mapConfig.elementToSpaceRatio();
        int totalElementsInConfig = 0;
        System.out.println("ELEMENT TO SPACE RATIO: " + elementToSpaceRatio * 100 + "%");

        for (MapElementConfiguration config : mapElementConfigurationList) {
            List<ElementToSize> elementToSizes = config.elementToSizes();
            for (ElementToSize element : elementToSizes) {
                int sizePerElement = element.elementCount() * element.size();
                totalElementsInConfig += sizePerElement;

                if (totalElementsInConfig <= (mapSize * elementToSpaceRatio)) {
                    if (config.dimensionGrowth() > 0 && element.size() > 1) {
                        isConfigValid = true;
                    }
                }
            }
        }
        System.out.println("VALID NUMBER OF ELEMENTS!");
        System.out.println("CURRENT RATIO: " + ((double) totalElementsInConfig / mapSize) * 100 + "%");
        return isConfigValid;
    }
}
