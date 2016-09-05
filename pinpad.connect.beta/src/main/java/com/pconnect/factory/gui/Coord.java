package com.pconnect.factory.gui;



public class Coord {

    private final int X;
    private final int Y;

    public Coord() {
        this(0, 0);
    }

    public Coord(final int x, final int y) {
        X = x;
        Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    @Override
    public String toString() {
        return X + "." + Y;
    }
}
