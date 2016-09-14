/**
 *
 */
package com.pconnect.entity.event.bo;

import com.pconnect.entity.event.Event;


/**
 * @author 20002845
 * @date Nov 16, 2015
 *
 */
public class Caisse extends Event
{

    @Override
    public void runEvent() {
        board.showMsgBox("Hello I'm a caisse");
    }

}

