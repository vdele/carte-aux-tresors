/**
 *
 */
package com.pconnect.entity.event;

import java.util.List;

import com.pconnect.entity.event.itf.IEvent;
import com.pconnect.entity.event.itf.IPerson;
import com.pconnect.factory.gui.Coord;
import com.pconnect.factory.util.FactoryUtils;


/**
 *
 * TODO can divide this class, separate moving operation in MobileEvent class
 *
 * @author 20002845
 * @date 11 juin 2015
 *
 */
public class Person extends Event implements IPerson
{
    public static final int DIRECTION_SOUTH = 1;
    public static final int DIRECTION_WEST = 4;
    public static final int DIRECTION_EAST = 7;

    public static final int DIRECTION_NORTH = 10;

    public int PAS = 6;

    private final String name;
    private final String surname;
    private Integer lifePercent;

    private boolean  mainChar= false;

    private Integer victoriaNumber;

    /**
     * @param name
     * @param surname
     * @param lifePercent
     */
    public Person(final String name, final String surname, final Integer lifePercent) {
        super();
        this.name = name;
        this.surname = surname;
        this.lifePercent = lifePercent;
        victoriaNumber = 0;

    }


    /* (non-Javadoc)
     * @see entity.person.IPerson#down()
     */
    public void down() {
        int movement = PAS;
        while (isInTheWall(minX(), minY() + movement) && movement > 0) {
            movement--;
        }
        setY( minY() + movement);
    }

    /**
     * @return
     */
    private boolean esquive() {
        // esquive = 1 chance sur 3
        final int esquive = Integer.valueOf(new Double(Math.random()*3).intValue());
        if(esquive == 0){
            log.logInfo(toString()+ " esquive !");
            return true;
        }

        return false;
    }

    /**
     *
     * @return
     */
    private int generateHitting() {
        return FactoryUtils.target(10, 5);
    }


    /**
     * @param facedCoord
     * @return
     */
    private IEvent getEvent(final Coord facedCoord) {
        return getEvent(facedCoord.getX(), facedCoord.getY());
    }

    /**
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

    public Coord getFacedCoord(){
        int xPix,yPix;
        //isInTheWall(X - movement ,Y)
        switch(getDirection()){
            case DIRECTION_NORTH:
                xPix = minX();
                yPix = minY() - PAS;
                break;
            case DIRECTION_SOUTH:
                xPix = minX();
                yPix = minY() + PAS;
                break;
            case DIRECTION_WEST:
                xPix = minX() - PAS;
                yPix = minY();
                break;
            case DIRECTION_EAST:
                xPix = minX() + PAS;
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


    /* (non-Javadoc)
     * @see entity.person.IPerson#getLifePercent()
     */
    public Integer getLifePercent() {
        return lifePercent;
    }

    /* (non-Javadoc)
     * @see entity.person.IPerson#getName()
     */
    public String getName() {
        return name;
    }
    /* (non-Javadoc)
     * @see entity.person.IPerson#getRapidity()
     */
    public Integer getRapidity(){
        return FactoryUtils.random(1, 10);
    }

    /* (non-Javadoc)
     * @see entity.person.IPerson#getSurname()
     */
    public String getSurname() {
        return surname;
    }

    /* (non-Javadoc)
     * @see entity.person.IPerson#getVictoriaNumber()
     */
    public Integer getVictoriaNumber(){
        return victoriaNumber;
    }

    /* (non-Javadoc)
     * @see entity.person.IPerson#hits(entity.person.Person)
     */
    public void hits(final Person adversaire) {
        log.logInfo(toString() + " tente de frapper " + adversaire);
        if(adversaire.esquive()){
            adversaire.hits(this);
        }
        else{
            log.logInfo(toString() + " frappe " + adversaire +" !!! ");
            final int hittingValue=generateHitting();
            adversaire.loseLife(hittingValue);
        }
    }


    /* (non-Javadoc)
     * @see entity.person.IPerson#incrementVictoriaNumber()
     */
    public void incrementVictoriaNumber() {
        victoriaNumber++;

    }

    /*
     * Tile position
     * down     0-1-2
     * left     3-4-5
     * right    6-7-8
     * up       9-10-11
     *
     */

    /* (non-Javadoc)
     * @see entity.person.IPerson#isAlive()
     */
    public boolean isAlive(){
        return !isDead();
    }

    /* (non-Javadoc)
     * @see entity.person.IPerson#isDead()
     */
    public boolean isDead(){
        if(lifePercent>0) {
            return false;
        } else {
            return true;
        }
    }


    /* (non-Javadoc)
     * @see entity.person.IPerson#isInTheWall(int, int)
     */
    public boolean isInTheWall(final int xPix, final int yPix){
        if (yPix + getHeight() >= board.getMapLengthInPixels()) {
            return true;
        } else if (xPix + getWidth() >= board.getMapWidthInPixels()) {
            return true;
        }
        else if (xPix < 0) {
            return true;
        } else if (yPix < 0) {
            return true;
        } else {
            if(getEvent(xPix, yPix)!=null) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean isMainChar() {
        return mainChar;
    }

    /* (non-Javadoc)
     * @see entity.person.IPerson#left()
     */
    public void left() {
        int movement = PAS;
        while (isInTheWall(minX() - movement, minY()) && movement > 0) {
            movement--;
        }
        setX(minX() - movement);
    }

    /**
     * @param hittingValue
     */
    private void loseLife(final int hittingValue) {
        lifePercent = lifePercent - hittingValue;

    }


    /* (non-Javadoc)
     * @see entity.person.IPerson#right()
     */
    public void right(){
        int movement = PAS;
        while (isInTheWall(minX() + movement, minY()) && movement > 0) {
            movement--;
        }
        setX(minX() + movement);
    }


    /* (non-Javadoc)
     * @see entity.person.IPerson#setLifePercent(java.lang.Integer)
     */
    public void setLifePercent(final Integer lifePercent) {
        this.lifePercent = lifePercent;
    }

    public void setMainChar(final boolean mainChar) {
        this.mainChar = mainChar;
    }

    /* (non-Javadoc)
     * @see entity.person.IPerson#toString()
     */
    @Override
    public String toString(){
        return getSurname() + " " + getName();
    }

    /* (non-Javadoc)
     * @see entity.person.IPerson#up()
     */
    public void up() {
        int movement = PAS;
        while(isInTheWall(minX() ,minY()-movement ) && movement>0){
            movement--;
        }
        setY( minY() - movement);
    }
}

