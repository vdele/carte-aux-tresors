package com.pconnect.entity.event.itf;


public interface IEventHelper {

    public int calculateNewValue(final IMobileEvent evt, final boolean horizontalMovement, final int increaseMov, final int movement);

    public IEvent getEvent(final IMobileEvent currentEvt, final int xPix, final int yPix);

    public boolean isInTheWall(final IMobileEvent evt,final boolean horizontalMovement, final int newValue);
}
