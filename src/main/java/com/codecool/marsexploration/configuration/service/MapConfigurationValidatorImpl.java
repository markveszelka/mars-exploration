package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;

import java.util.List;

public class MapConfigurationValidatorImpl implements MapConfigurationValidator {
    @Override
    public boolean validate(MapConfiguration mapConfig) {
        boolean isOverallValid = false;
        int mapSize = mapConfig.mapSize();
        List<MapElementConfiguration> mapElementConfigurationList = mapConfig.mapElementConfigurations();
        double elementToSpaceRatio = mapConfig.elementToSpaceRatio();

        boolean isRatioValid = isElementToSpaceRatioValid(mapElementConfigurationList, mapSize, elementToSpaceRatio);
        boolean isMapElementConfigValid = false;

        isMapElementConfigValid = isMapElementConfigValid(mapElementConfigurationList, isRatioValid, isMapElementConfigValid);

        if (isRatioValid && isMapElementConfigValid) {
            isOverallValid = true;
        }

        return isOverallValid;
    }

    private boolean isMapElementConfigValid(List<MapElementConfiguration> mapElementConfigurationList, boolean isRatioValid, boolean isMapElementConfigValid) {
        if (isRatioValid) {
            for (MapElementConfiguration mapElementConfig : mapElementConfigurationList) {
                isMapElementConfigValid = isElementConfigValid(mapElementConfig);
                if (!isMapElementConfigValid) break;
            }
        }
        return isMapElementConfigValid;
    }

    private boolean isElementConfigValid(MapElementConfiguration config) {
        boolean isConfigValid = false;
        boolean hasDimensionGrowth = config.dimensionGrowth() > 0;
        boolean isMultiDimensional = config.elementToSizes().stream().anyMatch(element -> element.size() > 1);

        if (isMultiDimensional && hasDimensionGrowth) {
            isConfigValid = true;
        } else if (!isMultiDimensional && !hasDimensionGrowth) {
            isConfigValid = true;
        }

        return isConfigValid;
    }

    private boolean isElementToSpaceRatioValid(List<MapElementConfiguration> mapElementConfigurationList, int mapSize, double elementToSpaceRatio) {
        boolean isValid = false;
        int totalElementsInConfig = 0;

        totalElementsInConfig = getTotalElementsInConfig(mapElementConfigurationList, totalElementsInConfig);

        if (totalElementsInConfig <= (mapSize * elementToSpaceRatio)) {
            isValid = true;
        }

        return isValid;
    }

    private static int getTotalElementsInConfig(List<MapElementConfiguration> mapElementConfigurationList, int totalElementsInConfig) {
        for (MapElementConfiguration config : mapElementConfigurationList) {
            List<ElementToSize> elementToSizes = config.elementToSizes();
            for (ElementToSize element : elementToSizes) {
                int sizePerElement = element.elementCount() * element.size();
                totalElementsInConfig += sizePerElement;
            }
        }
        return totalElementsInConfig;
    }
}
