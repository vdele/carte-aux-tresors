package com.pconnect.entity.event;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.pconnect.entity.event.itf.IMapElement;
import com.pconnect.factory.gui.Board;
import com.pconnect.factory.running.InstanceManager;
import com.pconnect.factory.running.Logger;
import com.pconnect.factory.running.itf.IInstanceManager;


public class MapElement implements IMapElement {

    Logger log = new Logger(this.getClass());

    private int HEIGHT = 32;
    private int WIDTH = 32;
    private int X = 60;
    private int Y = 110;

    public BufferedImage[] TILE_CHAR = null;

    /**
     * Tile position <br/>
     * down 0-1-2 <br/>
     * left 3-4-5 <br/>
     * right 6-7-8<br/>
     * up 9-10-11
     *
     */
    int IND_REPR_VARIATION = 0;

    final int[] REPR_VARIATION = { 0, 1, 0, -1 };

    int IMG_REPRESENTATION = 1;

    protected Board board = null;

    public MapElement() {
        board = (Board) InstanceManager.getInstance(IInstanceManager.BOARD);
    }


    public Integer getDirection(){
        return IMG_REPRESENTATION;
    }

    public final int getHeight() {
        return HEIGHT;
    }

    /* (non-Javadoc)
     * @see entity.person.IPerson#getImgRepresentation()
     */
    public Integer getImgRepresentation(){
        return IMG_REPRESENTATION;
    }

    public final BufferedImage[] getTileChar() {
        return TILE_CHAR;
    }

    public BufferedImage getTileRepresentation(){
        log.logTrace("Tile_Char[@]", IMG_REPRESENTATION+IND_REPR_VARIATION);
        return TILE_CHAR[IMG_REPRESENTATION+REPR_VARIATION[IND_REPR_VARIATION]];
    }


    public final int getWidth() {
        return WIDTH;
    }


    /* (non-Javadoc)
     * @see entity.person.IEvent#maxX()
     */
    public final int maxX() {
        return X + WIDTH;
    }

    public final int maxY() {
        return Y + HEIGHT;
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


    /* (non-Javadoc)
     * @see entity.person.IPerson#modifyIndRepresentationVariation()
     */
    public void modifyIndRepresentationVariation() {
        if(IND_REPR_VARIATION == REPR_VARIATION.length-1) {
            IND_REPR_VARIATION=0;
        } else {
            IND_REPR_VARIATION++;
        }
    }

    public void setDirection(final int keyCode) {
        log.logTrace("Key : @", keyCode);
        if(keyCode == new Integer(KeyEvent.VK_DOWN)){
            IMG_REPRESENTATION = Person.DIRECTION_SOUTH;
        }
        if(keyCode == new Integer(KeyEvent.VK_LEFT)){
            IMG_REPRESENTATION = Person.DIRECTION_WEST;
        }
        if(keyCode == new Integer(KeyEvent.VK_RIGHT)){
            IMG_REPRESENTATION = Person.DIRECTION_EAST;
        }
        if(keyCode==new Integer(KeyEvent.VK_UP)){
            IMG_REPRESENTATION = Person.DIRECTION_NORTH;
        }
    }

    public final void setHeight(final int height) {
        HEIGHT = height;
    }

    public final void setTileChar(final BufferedImage[] tILE_CHAR) {
        TILE_CHAR = tILE_CHAR;
    }

    public final void setWidth(final int width) {
        WIDTH = width;
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
