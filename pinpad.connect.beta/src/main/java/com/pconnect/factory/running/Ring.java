/**
 *
 */
package com.pconnect.factory.running;

import com.pconnect.entity.event.bo.Person;
import com.pconnect.entity.event.itf.IPerson;


/**
 * @author 20002845
 * @date 11 juin 2015
 * 
 */
public class Ring
{
    public static void fight(final Person p1, final Person p2){
        System.out.println("Le combat entre " + p1.toString() + " et " + p2.toString() + " commencent !");

        while(p1.isAlive() && p2.isAlive()){
            newHit(p1,p2);
            etatFightter(p1,p2);
        }
        if(p1.isAlive()){
            displayVictoria(p1);
        } else 
            if(p2.isAlive()){
                displayVictoria(p2);
            } else {
                displayExAequo();
            }
    }

    /**
     * @param p1
     * @param p2
     */
    private static void etatFightter(final IPerson p1, final IPerson p2) {
        System.out.println(p1 + " : " + p1.getLifePercent() + " ||||| " + p2 + " : " + p2.getLifePercent());

    }

    private static final int P1_FASTER = 0;
    private static final int P2_FASTER = 1;
    private static final int SAME_RAPIDITY = 2;

    /**
     * @param p1
     * @param p2
     */
    private static void newHit(final Person p1, final Person p2) {
        final int whoIsFaster = whoIsFaster(p1,p2);
        switch (whoIsFaster) {
        case P1_FASTER:
            p1.hits(p2);
            break;
        case P2_FASTER:
            p2.hits(p1);
            break;
        case SAME_RAPIDITY:

            break;
        }


    }

    /**
     * @param p1
     * @param p2
     * @return
     */
    private static int whoIsFaster(final IPerson p1, final IPerson p2) {
        final int p1Rapidity = p1.getRapidity();
        final int p2Rapidity = p2.getRapidity();
        if(p1Rapidity>p2Rapidity) {
            System.out.println(p1 + " est plus rapide");
            return Ring.P1_FASTER;
        }
        if(p2Rapidity>p1Rapidity) {
            System.out.println(p2 + " est plus rapide");
            return Ring.P2_FASTER;
        }
        System.out.println(p1 + " et " + p2 + " se neutralisent ! ");
        return Ring.SAME_RAPIDITY;
    }

    /**
     * 
     */
    private static void displayExAequo() {
        System.out.println("Ex aequo !!!");

    }

    /**
     * @param p1
     */
    private static void displayVictoria(final IPerson p) {
        System.out.println(p.toString() + " wins !!!");
        p.incrementVictoriaNumber();

    }
}

