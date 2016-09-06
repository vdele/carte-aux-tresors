/**
 *
 */
package com.pconnect.entity.event.impl;

import com.pconnect.entity.event.Event;


/**
 * @author 20002845
 * @date Nov 16, 2015
 *
 */
public class Coffre extends Event
{

    /* (non-Javadoc)
     * @see com.pconnect.entity.event.Event#activeEvent()
     * TODO this method must be abstract in event, and a main method must be used to call consumeEvent, then activeEvent
     */
    @Override
    public void runEvent() {
        super.activeEvent();
        board.showMsgBox("Hello I'm a coffre");
    }

}

