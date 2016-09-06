
package com.pconnect.entity.event;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.List;

import com.pconnect.entity.event.itf.IEvent;
import com.pconnect.entity.event.itf.IMobileEvent;
import com.pconnect.entity.event.itf.IPerson;
import com.pconnect.factory.gui.Coord;

public abstract class MobileEvent extends Event implements IMobileEvent {

    private final int[] REPR_VARIATION = { 0, 1, 0, -1 };

    /**
     * Tile position <br/>
     * down 0-1-2 <br/>
     * left 3-4-5 <br/>
     * right 6-7-8<br/>
     * up 9-10-11
     *
     */
    private int representationVariationIndex = 0;

    public int stepMovement = 6;

    public MobileEvent() {
        super();
    }

    private int calculateNewValue(final boolean horizontalMovement, final int increaseMov, final int movement) {
        if(horizontalMovement){
            return minX() + increaseMov * movement;
        }
        else{
            return minY() + increaseMov * movement;
        }
    }

    /**
     * TODO this class can be extracted
     *
     * @param horizontalMovement
     * @param increaseMov
     * @param movement
     */
    private void defineNewCoord(final boolean horizontalMovement, final int increaseMov, final int movement) {
        if(horizontalMovement){
            setX(calculateNewValue(horizontalMovement, increaseMov, movement));
        } else {
            setY(calculateNewValue(horizontalMovement, increaseMov, movement));
        }
    }

    public void down() {
        executeVerticalMovement(true, false);
    }

    /**
     * TODO Can be extracted in another class
     *
     * @param increase
     * @param horizontalMovement
     */
    public void executeVerticalMovement(final boolean increase,final boolean horizontalMovement) {
        int movement = stepMovement;
        final int increaseMov = increase ? 1 : -1;
        while (isInTheWall(horizontalMovement,calculateNewValue(horizontalMovement, increaseMov, movement)) && movement > 0) {
            movement--;
        }
        defineNewCoord(horizontalMovement, increaseMov, movement);

    }

    public Integer getDirection(){
        return getImgRepresentation();
    }

    /**
     * @param facedCoord
     * @return
     */
    private IEvent getEvent(final Coord facedCoord) {
        return getEvent(facedCoord.getX(), facedCoord.getY());
    }

    /**
     * Extract all event excepted main character
     *
     * @param xPix
     * @param yPix
     * @return
     */
    private IEvent getEvent(final int xPix, final int yPix) {
        final List<IEvent> listEvt = board.getEvents();
        if(listEvt!=null) {
            for (final IEvent evt:listEvt) {
                if(evt!=null
                        && (!(evt instanceof IPerson)
                                || evt instanceof IPerson && !((IPerson)evt).isMainChar())){
                    if (evt.minX() < xPix + getWidth()
                    && evt.maxY()>yPix
                    && evt.minY() < yPix + getHeight()
                    && evt.maxX()>xPix) {
                        return evt;
                    }
                }

            }
        }
        return null;
    }

    private Coord getFacedCoord() {
        int xPix,yPix;
        //isInTheWall(X - movement ,Y)
        switch(getDirection()){
            case DIRECTION_NORTH:
                xPix = minX();
                yPix = minY() - stepMovement;
                break;
            case DIRECTION_SOUTH:
                xPix = minX();
                yPix = minY() + stepMovement;
                break;
            case DIRECTION_WEST:
                xPix = minX() - stepMovement;
                yPix = minY();
                break;
            case DIRECTION_EAST:
                xPix = minX() + stepMovement;
                yPix = minY();
                break;
            default:
                xPix = -1;
                yPix = -1;
                break;

        }
        final Coord c = new Coord(xPix,yPix);

        return c;
    }

    /* (non-Javadoc)
     * @see com.pconnect.entity.event.itf.IPerson#getFacedEvent()
     */
    public IEvent getFacedEvent() {
        return getEvent(getFacedCoord());
    }

    @Override
    public BufferedImage getTileRepresentation(){
        log.logTrace("Tile_Char[@]", getImgRepresentation()+representationVariationIndex);
        return TILE_CHAR[getImgRepresentation()+REPR_VARIATION[representationVariationIndex]];
    }

    /**
     * TODO can be extracted in another helper class
     *
     * @param horizontalMovement
     * @param newValue
     * @return
     */
    private boolean isInTheWall(final boolean horizontalMovement, final int newValue) {
        int xPix;
        int yPix;
        if(horizontalMovement){
            xPix = newValue;
            yPix = minY();
        }
        else{
            xPix = minX();
            yPix = newValue;
        }

        if (yPix + getHeight() >= board.getMapLengthInPixels() || xPix + getWidth() >= board.getMapWidthInPixels() || xPix < 0 || yPix < 0) {
            return true;
        } else if (getEvent(xPix, yPix) != null) {
            return true;
        } else {
            return false;
        }

    }

    public void left() {
        executeVerticalMovement(false,true);
    }

    /* (non-Javadoc)
     * @see entity.person.IPerson#modifyIndRepresentationVariation()
     */
    public void modifyIndRepresentationVariation() {
        if(representationVariationIndex == REPR_VARIATION.length-1) {
            representationVariationIndex=0;
        } else {
            representationVariationIndex++;
        }
    }

    public void right() {
        executeVerticalMovement(true,true);
    }


    public void setDirection(final int keyCode) {
        log.logTrace("Key : @", keyCode);
        if (keyCode == new Integer(KeyEvent.VK_DOWN)) {
            setImgRepresentation(IMobileEvent.DIRECTION_SOUTH);
        }
        if (keyCode == new Integer(KeyEvent.VK_LEFT)) {
            setImgRepresentation(IMobileEvent.DIRECTION_WEST);
        }
        if (keyCode == new Integer(KeyEvent.VK_RIGHT)) {
            setImgRepresentation(IMobileEvent.DIRECTION_EAST);
        }
        if (keyCode == new Integer(KeyEvent.VK_UP)) {
            setImgRepresentation(IMobileEvent.DIRECTION_NORTH);
        }
    }

    public void up() {
        executeVerticalMovement(false, false);
    }

}
