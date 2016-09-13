/**
 *
 */
package com.pconnect.factory.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.pconnect.factory.running.Engine;
import com.pconnect.factory.running.InstanceManager;
import com.pconnect.factory.running.itf.IInstanceManager;


/**
 * @author 20002845
 * @date 6 juil. 2015
 * 
 */
public class PadListener implements KeyListener
{
    private final int NO_BACKGROUND=KeyEvent.VK_F1;
    private final int DISPLAY_CHAR=KeyEvent.VK_F2;
    private final int PAUSE=KeyEvent.VK_ESCAPE;
    private final int F12=KeyEvent.VK_F12;
    private final int TEST_MSG_BOX=KeyEvent.VK_F11;
    private final int VALIDATION_KEY1=KeyEvent.VK_ENTER;
    private final int VALIDATION_KEY2=KeyEvent.VK_SPACE;

    final int[] USED_TOUCH = {NO_BACKGROUND,DISPLAY_CHAR,PAUSE,F12,TEST_MSG_BOX};
    final int[] VALIDATION_KEYS = {VALIDATION_KEY1,VALIDATION_KEY2};

    private Board board = null;

    public PadListener(){
        super();
        board = (Board)InstanceManager.getInstance(IInstanceManager.BOARD);
    }

    /**
     * @param keyCode
     */
    private void fKeyManagement(final int keyCode) {
        if(isUsedFKey(keyCode)){
            switch (keyCode) {
            case NO_BACKGROUND:                
                board.activeBackground();
                break;            
            case DISPLAY_CHAR:                
                board.displayCharacter();
                break;
            case PAUSE:   
                board.pause();

                break;

            case TEST_MSG_BOX:
                if(!board.messageIsDisplayed()) {
                    board.showMsgBox("Just a test");
                } else {
                    board.disableMsgBox();
                }
                break;

            case F12:                
                System.exit(0);
                break;

            default:
                break;
            }
        }
    }

    private boolean isArrowKey(final int keyCode) {
        switch (keyCode) {
        case KeyEvent.VK_UP:
            return true;

        case KeyEvent.VK_DOWN:
            return true;

        case KeyEvent.VK_LEFT:
            return true;

        case KeyEvent.VK_RIGHT:
            return true;

        default:
            return false;
        }
    }

    /**
     * @param keyCode
     * @return
     */
    private boolean isUsedFKey(final int keyCode) {
        for(final int touch : USED_TOUCH){
            if(touch== keyCode) {
                return true;
            }
        }
        return false;
        // return Arrays.asList(USED_TOUCH).contains(keyCode);   

    }

    /**
     * If key typed is validationkey (enter, space)
     * @param keyCode
     * @return
     */
    private boolean isValidationKey(final int keyCode) {
        for(final int touch : VALIDATION_KEYS){
            if(touch== keyCode) {
                return true;
            }
        }
        return false;
    }

    public synchronized void keyPressed(final KeyEvent e) {

        if(!board.gameIsStopped()) {
            if(isArrowKey(e.getKeyCode())){
                if(!Engine.movement.contains(new Integer(e.getKeyCode()))) {
                    board.getMainChar().setDirection(e.getKeyCode());
                    Engine.movement.clear();
                }
                Engine.movement.add(e.getKeyCode());
            }
        }

        final int keyCode = e.getKeyCode();
        if(board.gameInPause()){
            switch (keyCode) {
            case KeyEvent.VK_UP:
                board.MENU_PAUSE.selectPrevious();
                break;
            case KeyEvent.VK_DOWN:
                board.MENU_PAUSE.selectNext();
                break;

            default:
                if(isValidationKey(keyCode)){
                    board.MENU_PAUSE.activeSelectedMenu();
                }
                break;
            }
        }
        else if(board.messageIsDisplayed()){
            if(isValidationKey(keyCode)){
                board.disableMsgBox();
            }
        }
        else {
            if(isValidationKey(keyCode)){
                board.eventManagement();
            }

        }

        fKeyManagement(keyCode);



    }

    public synchronized void keyReleased(final KeyEvent e) {
        Engine.movement.remove(new Integer(e.getKeyCode())); 
    }

    public synchronized void keyTyped(final KeyEvent e) {
        // Nothing to do here
    }

}

