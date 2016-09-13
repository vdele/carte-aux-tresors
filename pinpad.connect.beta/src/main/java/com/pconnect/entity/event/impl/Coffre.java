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
     *
     */
    @Override
    public void runEvent() {
        board.showMsgBox("Hello I'm a coffre");
    }

}

