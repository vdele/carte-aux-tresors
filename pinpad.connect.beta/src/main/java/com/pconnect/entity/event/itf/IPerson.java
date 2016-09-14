/**
 *
 */

package com.pconnect.entity.event.itf;

import com.pconnect.entity.event.bo.Person;

/**
 * @author 20002845
 * @date 6 oct. 2015
 *
 */
public interface IPerson extends IMobileEvent
{
    public abstract Integer getLifePercent();

    public abstract String getName();

    /**
     * Rapidity is calculated for one hit between 1 and 10
     * @return
     */
    public abstract Integer getRapidity();

    public abstract String getSurname();

    public abstract Integer getVictoriaNumber();

    /**
     * @param adversaire
     */
    public abstract void hits(Person adversaire);

    /**
     *
     */
    public abstract void incrementVictoriaNumber();

    public abstract boolean isAlive();

    public abstract boolean isDead();

    public boolean isMainChar();

    public abstract void setLifePercent(Integer lifePercent);
    public void setMainChar(final boolean mainChar);

    public abstract String toString();

}
