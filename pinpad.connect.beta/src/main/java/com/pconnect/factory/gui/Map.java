package com.pconnect.factory.gui;

import java.awt.image.BufferedImage;

public class Map {

    private int[][] map = null;

    public BufferedImage[] IMG_CASE = null;

    public int CASE_SIZE = 32;

    public Map(final int[][] map) {
        this.map = map;
    }

    public int getHeight(){
        return map.length;
    }

    public BufferedImage getImageRepresentation(final int col, final int lig) {
        return IMG_CASE[map[col][lig]];
    }

    public int[][] getMap() {
        return map;
    }

    public int getMapLengthInPixels() {
        return map.length*CASE_SIZE;
    }

    public int getMapWidthInPixels() {
        return map[0].length * CASE_SIZE;
    }

    public int getWidth(){
        return map[0].length;
    }

    public void setMap(final int[][] map) {
        this.map = map;
    }

}
