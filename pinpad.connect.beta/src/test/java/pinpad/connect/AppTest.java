package pinpad.connect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.pconnect.entity.event.bo.Person;
import com.pconnect.entity.event.itf.IPerson;
import com.pconnect.factory.parsing.NameData;
import com.pconnect.factory.running.Logger;
import com.pconnect.factory.util.FactoryUtils;
import com.pconnect.factory.util.Invoker;
import com.pconnect.factory.util.ParsingFactory;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
extends TestCase
{

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    private final String methodName = "length";

    private final String valueObject = "Some object";

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( final String testName )
    {
        super( testName );
    }

    /**
     * @param tab
     */
    private void afficheResult(final int[] tab) {
        System.out.println("-----------------");
        for(int i=0 ; i < tab.length;i++){
            if(tab[i]!=0) {
                System.out.println( i + " : " + tab[i]);
            }
        }
        System.out.println("-----------------");
    }

    /**
     * @param tab
     */
    private void init(final int[] tab) {
        for(int i=0 ; i < tab.length;i++){
            tab[i] = 0;
        }
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void testDice(){
        final int[] tab={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        for(int i = 0 ; i < 300000;i++){
            final int de1 = FactoryUtils.dice(6, 2);
            tab[de1]++;
        }
        afficheResult(tab);
    }

    public void testGetMethod() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
    IllegalAccessException, InvocationTargetException, ClassNotFoundException, InstantiationException {
        final Class<?> c = Class.forName("java.lang.String");
        final Method m = c.getMethod(methodName, new Class[] {});
        final Object ret = m.invoke(valueObject, new Object[] {});
        assertEquals(11, ret);

        Invoker.invokeMethod("com.pconnect.factory.util.Invoker", "test");
    }

    public void testLogger(){
        final Logger log = new Logger(getClass());
        log.logError("remplacer valeur @ @ @ @", 1,2,3,4);


    }

    public void testParsing(){
        try {
            final NameData name = new NameData();
            assertEquals("name.data", name.getFileName());
        } catch (final Exception e) {

            e.printStackTrace();
        }
    }

    public void testParsingName() throws Exception{
        final String class1 = "NameData";
        assertEquals("name.data",ParsingFactory.convertClassToDataFileName(class1));

        final String class2 = "NameOfClassData";
        assertEquals("nameOfClass.data",ParsingFactory.convertClassToDataFileName(class2));
        final String class3 = "Name_of_classData";
        assertEquals("name_of_class.data",ParsingFactory.convertClassToDataFileName(class3));
    }

    public void testProbability(){
        final int[] tab={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        for(int i = 0 ; i < 1000000; i ++){
            final int chiffre = FactoryUtils.target(10, 5);
            tab[chiffre]++;
        }
        afficheResult(tab);
        init(tab);

        for(int i = 0 ; i < 1000000; i ++){
            final int chiffre = FactoryUtils.random(5, 15);
            tab[chiffre]++;
        }

        afficheResult(tab);
    }


    public void testRandom(){
        for(int i =0;i<1000;i++){
            final int random = FactoryUtils.random(50,70);
            assertEquals(true, random>=50);
            assertEquals(true, random<=70);
        }
    }
    public void testRandomList(){
        for(int i =0;i<1000;i++){
            final int random = FactoryUtils.random(0,1,2,3,4,5,6,7,8,9,10);
            assertEquals(true, random>=0);
            assertEquals(true, random<=10);
        }
    }


    public void testRapidity(){
        final IPerson p1 = new Person("Bros", "Mario", 100);
        for (int i = 0 ; i < 1000 ; i ++) {

            final int rapidity = p1.getRapidity();
            System.out.println(rapidity);

            assertEquals(true, rapidity>=1);
            assertEquals(true, rapidity<=10);
        }
    }




}
