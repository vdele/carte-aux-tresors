
package com.pconnect.entity.event.itf;

public interface IMobileEvent extends IEvent {


    public static final int DIRECTION_SOUTH = 1;
    public static final int DIRECTION_WEST = 4;
    public static final int DIRECTION_EAST = 7;
    public static final int DIRECTION_NORTH = 10;

    public void down();

    public Integer getDirection();

    public IEvent getFacedEvent();

    public void left();

    public void modifyIndRepresentationVariation();

    public void right();

    /**
     * @param keyCode
     */
    public void setDirection(int keyCode);

    public void up();

}
