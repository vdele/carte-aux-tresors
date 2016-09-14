/**
 *
 */
package com.pconnect.entity.event.bo;

import com.pconnect.entity.event.MobileEvent;
import com.pconnect.entity.event.itf.IPerson;
import com.pconnect.factory.util.FactoryUtils;


/**
 *
 *
 * @author 20002845
 * @date 11 juin 2015
 *
 */
public class Person extends MobileEvent implements IPerson
{

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




    public boolean isMainChar() {
        return mainChar;
    }

    /**
     * @param hittingValue
     */
    private void loseLife(final int hittingValue) {
        lifePercent = lifePercent - hittingValue;

    }


    @Override
    public void runEvent(){
        log.logInfo("Nothing to do here");
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
}

