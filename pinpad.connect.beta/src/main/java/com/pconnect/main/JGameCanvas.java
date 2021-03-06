package com.pconnect.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

enum Tile {
    GRASS, GRASS_STONE, GRASS_BAGS, T3, T4, T5, T6, T7, T8, T9,
    TREE, TREE_CHOMP, TREE_DEAD, T13, T14, T15, T16, T17, T18, T19,
    ROAD_H, ROAD_V, ROAD_HV_DOWN, ROAD_HV_UP, ROAD_VH_RIGHT, ROAD_VH_LEFT, ROAD_CROSS, T27, T28, T29,
    WALL, WALL_POSTER, WALL_END_RIGHT, WALL_END_LEFT, T34, T35, T36, T37, T38, T39,
    T40, T41, T42, T43, T44, T45, T46, T47, T48, T49,
    NEWS, T51,      RES_1, RES_2, BUSS_1, BUSS_2, HOSP_1, HOSP_2, MARK_1, MARK_2,
    PIZZ_1, PIZZ_2, RES_3, RES_4, BUSS_3, BUSS_4, HOSP_3, HOSP_4, MARK_3, MARK_4,
    PIZZ_3, PIZZ_4, RES_5, RES_6, BUSS_5, BUSS_6, HOSP_5, HOSP_6, MARK_5, MARK_6
}

public class JGameCanvas extends JPanel{
    private static final int tW = 32; // tile width
    private static final int tH = 32; // tile height
    private static final Tile map[][] =
        {{Tile.TREE,Tile.TREE, Tile.TREE, Tile.ROAD_V, Tile.GRASS, Tile.TREE, Tile.TREE_DEAD, Tile.GRASS_STONE, Tile.TREE, Tile.TREE},
        {Tile.WALL, Tile.WALL_POSTER, Tile.WALL_END_RIGHT , Tile.ROAD_V, Tile.WALL_END_LEFT, Tile.WALL, Tile.WALL_END_RIGHT, Tile.TREE_CHOMP, Tile.GRASS_STONE, Tile.GRASS_STONE},
        {Tile.GRASS,Tile.GRASS, Tile.GRASS_STONE, Tile.ROAD_V, Tile.GRASS, Tile.GRASS, Tile.GRASS, Tile.GRASS, Tile.GRASS, Tile.GRASS},
        {Tile.PIZZ_1,Tile.PIZZ_2, Tile.GRASS, Tile.ROAD_V, Tile.GRASS, Tile.GRASS, Tile.GRASS, Tile.GRASS, Tile.GRASS, Tile.GRASS},
        {Tile.PIZZ_3,Tile.PIZZ_4, Tile.GRASS, Tile.ROAD_V, Tile.GRASS, Tile.GRASS, Tile.MARK_1, Tile.MARK_2, Tile.HOSP_1, Tile.HOSP_2},
        {Tile.ROAD_H,Tile.ROAD_H, Tile.ROAD_H, Tile.ROAD_VH_LEFT, Tile.TREE, Tile.TREE_DEAD, Tile.MARK_3, Tile.MARK_4, Tile.HOSP_3, Tile.HOSP_4},
        {Tile.GRASS,Tile.BUSS_1, Tile.BUSS_2, Tile.ROAD_V, Tile.TREE, Tile.NEWS, Tile.MARK_5, Tile.MARK_6, Tile.HOSP_5, Tile.HOSP_6},
        {Tile.GRASS,Tile.BUSS_3, Tile.BUSS_4, Tile.ROAD_VH_RIGHT, Tile.ROAD_H, Tile.ROAD_H, Tile.ROAD_H, Tile.ROAD_H, Tile.ROAD_H, Tile.ROAD_H},
        {Tile.GRASS,Tile.BUSS_5, Tile.BUSS_6, Tile.ROAD_V, Tile.GRASS, Tile.GRASS, Tile.GRASS, Tile.GRASS, Tile.GRASS, Tile.GRASS},
        {Tile.GRASS,Tile.GRASS, Tile.GRASS, Tile.ROAD_V, Tile.GRASS, Tile.GRASS, Tile.GRASS, Tile.GRASS, Tile.GRASS, Tile.GRASS}
        };

    private final Image tileset;

    public JGameCanvas() {
        tileset = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("resources/tileset.png"));
    }

    @Override
    protected void paintComponent(final Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        for(int i=0;i<10;i++) {
            for(int j=0;j<10;j++) {
                drawTile(g, JGameCanvas.map[j][i], i*JGameCanvas.tW,j*JGameCanvas.tH);
            }
        }
    }

    protected void drawTile(final Graphics g, final Tile t, final int x, final int y){
        // map Tile from the tileset
        final int mx = t.ordinal()%10;
        final int my = t.ordinal()/10;
        g.drawImage(tileset, x, y, x+JGameCanvas.tW, y+JGameCanvas.tH,
                mx*JGameCanvas.tW, my*JGameCanvas.tH,  mx*JGameCanvas.tW+JGameCanvas.tW, my*JGameCanvas.tH+JGameCanvas.tH, this);
    }
}