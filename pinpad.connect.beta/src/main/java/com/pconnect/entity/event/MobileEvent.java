
package com.pconnect.entity.event;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.pconnect.entity.event.itf.IEvent;
import com.pconnect.entity.event.itf.IEventHelper;
import com.pconnect.entity.event.itf.IMobileEvent;
import com.pconnect.factory.gui.Coord;
import com.pconnect.factory.running.InstanceManager;
import com.pconnect.factory.running.itf.IInstanceManager;

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

    protected IEventHelper eventHelper;

    public MobileEvent() {
        super();
        eventHelper = (IEventHelper) InstanceManager.getInstance(IInstanceManager.EVENT_HELPER);
    }

    /**
     * TODO this method can be extracted
     *
     * @param horizontalMovement
     * @param increaseMov
     * @param movement
     */
    public void defineNewCoord(final boolean horizontalMovement, final int increaseMov, final int movement) {
        if(horizontalMovement){
            setX(eventHelper.calculateNewValue(this,horizontalMovement, increaseMov, movement));
        } else {
            setY(eventHelper.calculateNewValue(this,horizontalMovement, increaseMov, movement));
        }
    }

    public void down() {
        executeVerticalMovement(true, false);
    }


    /**
     *
     *
     * @param increase
     * @param horizontalMovement
     */
    public void executeVerticalMovement(final boolean increase,final boolean horizontalMovement) {
        int movement = stepMovement;
        final int increaseMov = increase ? 1 : -1;
        while (eventHelper.isInTheWall(this,horizontalMovement,eventHelper.calculateNewValue(this,horizontalMovement, increaseMov, movement)) && movement > 0) {
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
        return eventHelper.getEvent(this,facedCoord.getX(), facedCoord.getY());
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

    public void left() {
        executeVerticalMovement(false,true);
    }

    /* (non-Javadoc)
     * @see entity.person.IPerson#modifyIndRepresentationVariation()
     */
    public void modifyIndRepresentationVariation() {
        if (representationVariationIndex == REPR_VARIATION.length - 1) {
            representationVariationIndex = 0;
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
