package com.codecool.marsexploration.mapelements.model;

public class Map {
    private String[][] representation;

    private boolean successfullyGenerated;

    public Map(String[][] representation) {
        this.representation = representation;
    }

    public boolean isSuccessfullyGenerated() {
        return successfullyGenerated;
    }

    public String[][] getRepresentation() {
        return representation;
    }

    public void setSuccessfullyGenerated(boolean successfullyGenerated) {
        this.successfullyGenerated = successfullyGenerated;
    }

    private static String createStringRepresentation(String[][] arr) {
        StringBuilder sb = new StringBuilder();
        for(String[] s1 : arr){
            for(String s2 : s1){
                sb.append(s2);
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return createStringRepresentation(representation);
    }
}

