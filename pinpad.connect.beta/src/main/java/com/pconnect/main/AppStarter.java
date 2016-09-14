package com.pconnect.main;

import com.pconnect.entity.event.bo.Person;
import com.pconnect.factory.running.Ring;


/**
 * Hello world!
 *
 */
public class AppStarter

{        // App.p.getContentPane().setLayout(null);


    enum a {aaa,bbb,ccc};

    public static void main( final String[] args ) throws Exception
    {

        // ************* first thing to do ******************//
        final AppLoader appLoader = new AppLoader();
        appLoader.start();
        //*************************************************//
        //************* then display :) *******************//
        final AppViewer appViewer = new AppViewer();
        appViewer.display();
        //*************************************************//

        if(true) {
            return;
        }
        //        Name name;
        //        DumbParse dumb;
        //        try {
        //            name = new Name();
        //            name.getDatasInFile();
        //
        //            dumb = new DumbParse();
        //            dumb.getDatasInFile();
        //        } catch (final Exception e) {
        //            e.printStackTrace();
        //        }
        final Person karl = new Person("Age","Karl",100);
        final Person jean = new Person("Bon","Jean",100);
        for(int i = 0 ; i < 1000; i++){
            karl.setLifePercent(100);
            jean.setLifePercent(100);
            Ring.fight(karl, jean);
        }
        System.out.println(karl + " : " + karl.getVictoriaNumber());
        System.out.println(jean + " : " + jean.getVictoriaNumber());



    }


}
