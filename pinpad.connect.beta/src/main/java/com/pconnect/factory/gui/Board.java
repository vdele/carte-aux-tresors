/**
 *
 */
package com.pconnect.factory.gui;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.pconnect.entity.event.itf.IEvent;
import com.pconnect.entity.event.itf.IPerson;
import com.pconnect.factory.running.Logger;
import com.pconnect.factory.running.itf.IMenu;


/**
 * @author 20002845
 * @date 30 juin 2015
 *
 */
public class Board
{

    Logger log = new Logger(getClass());
    private boolean displayBackground = false;
    private boolean displayCharacter = false;
    private boolean inPause = false;
    private boolean displayMessage = false;
    private String messageText = null;

    public int CASE_SIZE = 32;
    public int SCREEN_WIDTH = 400;

    public int SCREEN_HEIGHT = 300;


    public IMenu MENU_PAUSE= null;

    private  List<IEvent> ALL_EVENTS= null;

    public  int[][] MAP = null;

    public  BufferedImage[] IMG_CASE = null;



    public  boolean DISPLAY_EVENTS = false;


    private int indMainChar=-1;

    /**
     *
     */
    public void activeBackground() {
        displayBackground = !displayBackground;
    }

    public  void addEvent(final IEvent evt){
        if(ALL_EVENTS == null) {
            ALL_EVENTS = new ArrayList<IEvent>();
        }
        ALL_EVENTS.add(evt);
    }

    public boolean areCharactersDisplayed() {
        return displayCharacter;
    }

    public  void disableMsgBox(){
        displayMessage = false;
        messageText = null;
    }

    /**
     *
     */
    public void displayCharacter() {
        displayCharacter = !displayCharacter;
    }
    /**
     *
     */
    public  void eventManagement() {
        final IEvent facedEvent = getMainChar().getFacedEvent();
        if(facedEvent !=null){
            facedEvent.activeEvent();
        }
    }



    public  boolean gameInPause(){
        return inPause;
    }

    public  boolean gameIsStopped(){
        return gameInPause() || messageIsDisplayed();
    }
    public List<IEvent> getEvents(){
        return ALL_EVENTS;
    }
    /**
     * RORO
     * @return
     */
    public IPerson getMainChar(){

        if(indMainChar==-1){
            for (int i = 0; i < ALL_EVENTS.size();i++){
                final IEvent evt = ALL_EVENTS.get(i);
                if(evt instanceof IPerson) {
                    if(((IPerson)evt).isMainChar()) {
                        indMainChar = i;
                    }
                }
            }
        }

        if(indMainChar !=-1 ){
            return (IPerson)ALL_EVENTS.get(indMainChar);
        }
        return null;

    }


    public  int getMapLengthInPixels(){
        return MAP.length*CASE_SIZE;
    }

    public  int getMapWidthInPixels(){
        return MAP[0].length*CASE_SIZE;
    }

    public  String getMsgBoxText(){
        return messageText;
    }


    public boolean isBackgroundDisplayed(){
        return displayBackground;
    }



    public  boolean messageIsDisplayed(){
        return displayMessage;
    }

    /**
     *
     */
    public  void pause() {
        if(!messageIsDisplayed()){
            inPause = !inPause;
            log.logInfo(inPause ? "Starts pause" : "Ends pause");
            if (inPause) {
                MENU_PAUSE.selectNext();
            }
        }
    }

    /**
     * @param string
     */
    public  void showMsgBox(final String text) {
        if(!gameInPause()){
            log.logInfo("Displaying message : @", text);
            displayMessage = true;
            messageText = text;
        }
    }

}

