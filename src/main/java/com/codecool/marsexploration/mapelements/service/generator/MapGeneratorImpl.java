package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.mapelements.model.Map;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.placer.MapElementPlacer;

import java.util.List;

public class MapGeneratorImpl implements MapGenerator {

    private final MapElementsGenerator mapElementsGenerator;
    private final CoordinateCalculator coordinateCalculator;
    private final MapElementPlacer mapElementPlacer;
    private final Map map;

    public MapGeneratorImpl(Map map, MapElementsGenerator mapElementsGenerator, CoordinateCalculator coordinateCalculator, MapElementPlacer mapElementPlacer) {
        this.mapElementsGenerator = mapElementsGenerator;
        this.coordinateCalculator = coordinateCalculator;
        this.mapElementPlacer = mapElementPlacer;
        this.map = map;
    }

    private static boolean callCanPlaceElementMethod(Map map, Coordinate randomCord, MapElementPlacer mapElementPlacer, MapElement mapelement) {
        String[][] mapRepresentation = map.getRepresentation();

        return mapElementPlacer.canPlaceElement(
                mapelement,
                mapRepresentation,
                randomCord);
    }

    @Override
    public Map generate(MapConfiguration mapConfig) {
        int mapDimension = map.getRepresentation().length;
        String[][] mapRepresentation = map.getRepresentation();
        List<MapElement> mapElements = (List<MapElement>) mapElementsGenerator.createAll(mapConfig);
        // SORT BY SIZE, DESCENDING:
        mapElements.sort((element1, element2) -> Integer.compare(element2.getDimension(), element1.getDimension()));

        for (MapElement mapelement : mapElements) {
            Coordinate randomCord = coordinateCalculator.getRandomCoordinate(mapDimension);
            boolean canPlaceElement = callCanPlaceElementMethod(map, randomCord, mapElementPlacer, mapelement);
            if (canPlaceElement) {
                mapElementPlacer.placeElement(
                        mapelement,
                        mapRepresentation,
                        randomCord);
                mapelement.setSuccessfullyGenerated(true);
            } else {
                mapelement.setSuccessfullyGenerated(false);
                randomCord = coordinateCalculator.getRandomCoordinate(mapDimension);
                callCanPlaceElementMethod(map, randomCord, mapElementPlacer, mapelement);
            }
        }

        return map;
    }
}
