package com.codecool.marsexploration.output.service;

import com.codecool.marsexploration.mapelements.model.Map;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class MapFileWriterImpl implements MapFileWriter {

    private final Map map;
    private final String fileDir;

    public MapFileWriterImpl(Map map, String fileDir) {
        this.map = map;
        this.fileDir = fileDir;
    }

    @Override
    public void writeMapFile(Map map, String fileDir) {
        String[][] representation = map.getRepresentation();
        String formattedMap = convertRepresentationToString(representation);
        String fileName = "custom.map";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileDir + "/" + fileName));
            writer.write(formattedMap);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Map file written successfully.");
    }

    public void executeWrite() {
        writeMapFile(map, fileDir);
    }

    private String convertRepresentationToString(String[][] map) {
        StringBuilder formattedMap = new StringBuilder();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                formattedMap.append(map[i][j]);
            }

            if (i < map.length - 1) {
                formattedMap.append("\n");
            }
        }
        return formattedMap.toString();
    }
}
