package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.mapelements.model.MapElement;

import java.util.Comparator;

public class MapElementComparator implements Comparator<MapElement> {
    @Override
    public int compare(MapElement element1, MapElement element2) {
        return Integer.compare(element2.getDimension(), element1.getDimension());
    }
}
