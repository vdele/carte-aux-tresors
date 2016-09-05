package com.pconnect.entity.event;

import java.awt.image.BufferedImage;

import com.pconnect.entity.event.itf.IMapElement;
import com.pconnect.factory.gui.Board;
import com.pconnect.factory.running.InstanceManager;
import com.pconnect.factory.running.Logger;
import com.pconnect.factory.running.itf.IInstanceManager;


public class MapElement implements IMapElement {

    Logger log = new Logger(this.getClass());

    private int height = 32;
    private int width = 32;
    private int X = 60;
    private int Y = 110;
    private int IMG_REPRESENTATION = 1;

    public BufferedImage[] TILE_CHAR = null;


    protected Board board = null;

    public MapElement() {
        board = (Board) InstanceManager.getInstance(IInstanceManager.BOARD);
    }

    public final int getHeight() {
        return height;
    }


    public Integer getImgRepresentation(){
        return IMG_REPRESENTATION;
    }

    public final BufferedImage[] getTileChar() {
        return TILE_CHAR;
    }

    public BufferedImage getTileRepresentation(){
        log.logTrace("Tile_Char[@]", IMG_REPRESENTATION);
        return TILE_CHAR[IMG_REPRESENTATION];
    }

    public final int getWidth() {
        return width;
    }


    /* (non-Javadoc)
     * @see entity.person.IEvent#maxX()
     */
    public final int maxX() {
        return X + width;
    }


    public final int maxY() {
        return Y + height;
    }

    /* (non-Javadoc)
     * @see entity.person.IEvent#minX()
     */
    public final int minX() {
        return X;
    }

    /* (non-Javadoc)
     * @see entity.person.IEvent#minY()
     */
    public final int minY() {
        return Y;
    }


    public final void setHeight(final int height) {
        this.height = height;
    }

    public void setImgRepresentation(final Integer imgRepresentation){
        IMG_REPRESENTATION = imgRepresentation;
    }

    public final void setTileChar(final BufferedImage[] tILE_CHAR) {
        TILE_CHAR = tILE_CHAR;
    }

    public final void setWidth(final int width) {
        this.width = width;
    }

    /*
     * (non-Javadoc)
     * @see entity.person.itf.IEvent#setX(int)
     */
    public final void setX(final int x) {
        X = x;
    }

    /*
     * (non-Javadoc)
     * @see entity.person.itf.IEvent#setY(int)
     */
    public final void setY(final int y) {
        Y = y;
    }

}
