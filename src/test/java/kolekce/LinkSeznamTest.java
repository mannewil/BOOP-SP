package kolekce;

import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author karel@simerda.cz
 */
public class LinkSeznamTest {

//<editor-fold defaultstate="collapsed" desc="Testovací třída a objekty">    
    private static class TestClass {

        int a;

        public TestClass(int a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return "T" + a;
        }

    }

    private final TestClass T1 = new TestClass(1);
    private final TestClass T2 = new TestClass(2);
    private final TestClass T3 = new TestClass(3);
    private final TestClass T4 = new TestClass(4);
    private final TestClass T5 = new TestClass(5);
    private final TestClass T6 = new TestClass(6);
    private final TestClass T7 = new TestClass(7);
    private final TestClass T8 = new TestClass(8);
    private final TestClass T9 = new TestClass(9);
//</editor-fold>

    public LinkSeznamTest() {
    }

    //<editor-fold defaultstate="collapsed" desc="01 Vkládání prvního prvku">
    @Test
    public void test_01_01_VlozPrvni() {
        try {
            LinkSeznam<TestClass> instance = new LinkSeznam<>();
            instance.vlozPrvni(T1);
            TestClass[] result = {instance.dejPrvni(), instance.dejPosledni()};
            TestClass[] expected = {T1, T1};
            assertArrayEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }

    @Test(expected = NullPointerException.class)
    public void test_01_02_VlozPrvniException() throws NullPointerException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(null);
        fail();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="02 Vkládání posledního prvku">
    @Test
    public void test_02_01_VlozPosledni() {
        try {
            LinkSeznam<TestClass> instance = new LinkSeznam<>();
            instance.vlozPosledni(T1);
            TestClass[] result = {instance.dejPrvni(), instance.dejPosledni()};
            TestClass[] expected = {T1, T1};
            assertArrayEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }

    @Test(expected = NullPointerException.class)
    public void test_02_02_VlozPosledniException() throws NullPointerException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPosledni(null);
        fail();
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="03 Kontrola nastavení prvního prvku">
    @Test
    public void test_03_01_NastavPrvni() {
        try {
            LinkSeznam<TestClass> instance = new LinkSeznam<>();
            instance.vlozPrvni(T1);
            instance.nastavPrvni();
            TestClass result = instance.dejAktualni();
            TestClass expected = T1;
            assertEquals(expected, result);
        } catch (KolekceException ex) {
            fail();
        }
    }

    @Test
    public void test_03_02_NastavPosledni() {
        try {
            LinkSeznam<TestClass> instance = new LinkSeznam<>();
            instance.vlozPrvni(T1);
            instance.vlozPrvni(T2);
            instance.nastavPosledni();
            TestClass result = instance.dejAktualni();
            TestClass expected = T1;
            assertEquals(expected, result);
        } catch (KolekceException ex) {
            fail();
        }
    }

    @Test(expected = KolekceException.class)
    public void test_03_01_NastavPrvniException() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.nastavPrvni();
        fail();
    }

    @Test(expected = KolekceException.class)
    public void test_03_02_NastavPosledniException() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.nastavPosledni();
        fail();
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="04 Kontrola nastavení posledního prvku">
    @Test
    public void test_04_01_NastavPosledni() {
        try {
            LinkSeznam<TestClass> instance = new LinkSeznam<>();
            instance.vlozPrvni(T1);
            instance.nastavPosledni();
            TestClass result = instance.dejAktualni();
            TestClass expected = T1;
            assertEquals(expected, result);
        } catch (KolekceException ex) {
            fail();
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="05 Testy metod dej">
    @Test
    public void test_05_01_DejPrvni() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T3);
        instance.vlozPosledni(T2);
        instance.vlozPrvni(T1);
        instance.nastavPrvni();
        instance.dalsi();
        assertEquals(T1, instance.dejPrvni());

    }

    @Test(expected = KolekceException.class)
    public void test05_02_DejPrvniException() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.dejPrvni();
        fail();
    }

    @Test
    public void test_05_03_DejAktualni() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.vlozPosledni(T2);
        instance.nastavPrvni();
        assertEquals(T1, instance.dejAktualni());

    }

    @Test
    public void test_05_03_02_DejAktualni() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.vlozPrvni(T2);
        instance.vlozPrvni(T3);
        instance.nastavPrvni();
        instance.dalsi();
        assertEquals(T2, instance.dejAktualni());

    }

    @Test(expected = KolekceException.class)
    public void test05_04_DejAktualniException() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.dejAktualni();
        fail();
    }

    @Test
    public void test_05_05_DejPosledni() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T2);
        instance.vlozPosledni(T1);
        assertEquals(T1, instance.dejPosledni());

    }

    @Test
    public void test_05_05_02_DejPosledni() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPosledni(T1);
        assertEquals(T1, instance.dejPosledni());

    }

    @Test
    public void test_05_05_03_DejPosledni() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPosledni(T1);
        instance.vlozPosledni(T3);
        instance.vlozPosledni(T2);
        assertEquals(T2, instance.dejPosledni());

    }

    @Test(expected = KolekceException.class)
    public void test05_06_DejPosledniException() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.dejPosledni();
        fail();
    }

    @Test
    public void test_05_07_DejZaAktualnim() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.vlozPosledni(T2);
        instance.vlozPrvni(T3);
        instance.nastavPrvni();
        instance.dalsi();
        instance.vlozZaAktualni(T4);
        assertEquals(T4, instance.dejZaAktualnim());
    }

//    @Test
//    public void test_05_08_DejZaAktualnim() throws KolekceException {
//        LinkSeznam<TestClass> instance = new LinkSeznam<>();
//        instance.vlozPrvni(T1);
//        instance.nastavPrvni();
//        assertNull(instance.dejZaAktualnim());
//    }

    @Test(expected = KolekceException.class)
    public void test_05_09_DejZaAktualnimException() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.dejZaAktualnim();
        fail();
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="06 Testy metody odeberPrvni">
    @Test
    public void test_06_01_OdeberPrvniJedenZJednoho() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        assertEquals(T1, instance.odeberPrvni());
        assertEquals(0, instance.size());
    }

    @Test(expected = KolekceException.class)
    public void test_06_02_OdeberPrvniJedenZJednoho() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.odeberPrvni();
        fail();
    }

    @Test(expected = KolekceException.class)
    public void test_06_03_OdeberPrvniJedenZJednoho() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.vlozPosledni(T2);
        instance.nastavPrvni();
        instance.odeberPrvni();
        assertNull(instance.dejAktualni());
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="07 Testy metody odeberPosledni">
    @Test
    public void test_07_01_OdeberPosledniJedenZJednoho() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        TestClass TSmazano = instance.odeberPosledni();
        assertEquals(T1, TSmazano);
        assertEquals(0, instance.size());
    }

    @Test
    public void test_07_02_OdeberPosledniJedenZJednoho() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T3);
        instance.vlozPrvni(T2);
        instance.vlozPrvni(T1);
        TestClass TSmazano = instance.odeberPosledni();
        assertEquals(T3, TSmazano);
        assertEquals(2, instance.size());
    }

    @Test(expected = KolekceException.class)
    public void test_07_03_OdeberPosledniJedenZJednohoException() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        TestClass TSmazano = instance.odeberPosledni();
        instance.dejPrvni();
        fail();
    }

    @Test(expected = KolekceException.class)
    public void test_07_04_OdeberPosledniJedenZJednohoException() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        TestClass TSmazano = instance.odeberPosledni();
        instance.dejPosledni();
        fail();
    }

    @Test(expected = KolekceException.class)
    public void test_07_05_OdeberPosledniJedenZJednohoException() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        TestClass TSmazano = instance.odeberPosledni();
        instance.dejAktualni();
        fail();
    }

    @Test(expected = KolekceException.class)
    public void test_07_06_OdeberPosledniJedenZJednohoException() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.vlozPosledni(T2);
        instance.nastavPosledni();
        instance.odeberPosledni();
        assertNull(instance.dejAktualni());
    }

    @Test(expected = KolekceException.class)
    public void test_07_07_OdeberPosledniJedenZJednohoException() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.odeberPosledni();
        fail();
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="08 Procházení seznamu metodou dalsi">
    @Test
    public void test_08_01_JeDalsi() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.vlozPrvni(T2);
        instance.vlozPrvni(T3);
        instance.nastavPrvni();
        assertTrue(instance.jeDalsi());

    }

    @Test
    public void test_08_02_JeDalsi() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.nastavPrvni();
        assertFalse(instance.jeDalsi());
    }

    @Test
    public void test_08_03_JeDalsi() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        assertFalse(instance.jeDalsi());
    }

    @Test
    public void test_08_01_Dalsi() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.vlozPosledni(T2);
        instance.nastavPrvni();
        instance.dalsi();
        assertEquals(T2, instance.dejAktualni());
    }

    @Test(expected = KolekceException.class)
    public void test_08_02_Dalsi() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.nastavPrvni();
        instance.dalsi();
        fail();

    }

//</editor-fold>    
    //<editor-fold defaultstate="collapsed" desc="09 Testy metod vlozZaAktualni">
    /**
     * Ukazatel na aktuální prvek se metodou vlož neposouvá.
     *
     * @throws KolekceException
     */
    @Test
    public void test_09_01_VlozZaAktualniPrvni() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.nastavPrvni();
        instance.vlozZaAktualni(T2);
        TestClass result = instance.dejPrvni();
        assertEquals(T1, result);
    }

    @Test(expected = NullPointerException.class)
    public void test_09_02_VlozZaAktualniPrvni() throws NullPointerException, KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.nastavPrvni();
        instance.vlozZaAktualni(null);
        fail();
    }

    @Test
    public void test_09_03_VlozZaAktualniPrvni() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.nastavPrvni();
        instance.vlozZaAktualni(T2);
        TestClass result = instance.dejPosledni();
        assertEquals(T2, result);

    }

    @Test(expected = KolekceException.class)
    public void test_09_04_VlozZaAktualniPrvni() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.vlozZaAktualni(T2);
        fail();
    }

    @Test
    public void test_09_05_VlozZaAktualniPrvni() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.nastavPrvni();
        instance.vlozZaAktualni(T2);
        assertEquals(2, instance.size());

    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="10 Testy metod dejAktualni"> 
    @Test
    public void test_10_01_DejAktualniPosledni() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T2);
        instance.vlozPrvni(T1);
        instance.nastavPosledni();
        assertEquals(T2, instance.dejAktualni());
    }

    @Test(expected = KolekceException.class)
    public void test_10_02_DejAktualniException() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.dejAktualni();
        fail();
    }

    @Test(expected = KolekceException.class)
    public void test_10_03_DejAktualniException() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.dejAktualni();
        fail();
    }

    @Test
    public void test_10_04_DejAktualniPosledni() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T2);
        instance.vlozPrvni(T1);
        instance.nastavPrvni();
        assertEquals(T1, instance.dejAktualni());
        instance.dalsi();
        assertEquals(T2, instance.dejAktualni());
        instance.vlozPosledni(T3);
        instance.nastavPosledni();
        assertEquals(T3, instance.dejAktualni());
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="11 Testy metod dejZaAkualnim">
    @Test
    public void test_11_01_DejZaAktualnim() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T2);
        instance.vlozPrvni(T1);
        instance.nastavPrvni();
        assertEquals(T2, instance.dejZaAktualnim());
    }

    @Test(expected = KolekceException.class)
    public void test_11_02_DejAktualniException() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.dejZaAktualnim();
        fail();
    }

    @Test(expected = KolekceException.class)
    public void test_11_03_DejAktualniException() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.dejZaAktualnim();
        fail();
    }

    @Test
    public void test_11_04_DejAktualniPosledni() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.vlozPrvni(T2);
        instance.nastavPrvni();
        assertEquals(T1, instance.dejZaAktualnim());
        instance.vlozPrvni(T3);
        instance.nastavPrvni();
        assertEquals(T2, instance.dejZaAktualnim());
        instance.dalsi();
        assertEquals(T1, instance.dejZaAktualnim());
        instance.vlozPosledni(T4);
        instance.nastavPosledni();
        instance.vlozPosledni(T5);
        assertEquals(T5, instance.dejZaAktualnim());
    }

//    @Test
//    public void test_11_05_DejZaAktualnim() throws KolekceException {
//        LinkSeznam<TestClass> instance = new LinkSeznam<>();
//        instance.vlozPrvni(T2);
//        instance.nastavPrvni();
//        assertNull(instance.dejZaAktualnim());
//    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="12 Testy metod odeberAktualni">
    @Test
    public void test_12_01_OdeberAktualni() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T2);
        instance.vlozPrvni(T1);
        instance.vlozPrvni(T3);
        instance.nastavPrvni();
        assertEquals(3, instance.size());
        TestClass result = instance.odeberAktualni();
        assertEquals(T3, result);
        assertEquals(2, instance.size());
    }

    @Test
    public void test_12_02_OdeberAktualni() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T2);
        instance.vlozPrvni(T1);
        instance.vlozPrvni(T3);
        instance.nastavPosledni();
        assertEquals(3, instance.size());
        TestClass result = instance.odeberAktualni();
        assertEquals(T2, result);
        assertEquals(2, instance.size());
    }

    @Test
    public void test_12_03_OdeberAktualni() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T2);
        instance.vlozPrvni(T1);
        instance.vlozPrvni(T3);
        instance.nastavPrvni();
        instance.dalsi();
        assertEquals(3, instance.size());
        TestClass result = instance.odeberAktualni();
        assertEquals(T1, result);
        assertEquals(2, instance.size());
    }

    @Test
    public void test_12_04_OdeberAktualni() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.vlozPosledni(T2);
        instance.vlozPosledni(T3);
        instance.vlozPosledni(T4);
        instance.nastavPrvni();
        instance.dalsi();
        instance.dalsi();
        assertEquals(4, instance.size());
        TestClass result = instance.odeberAktualni();
        assertEquals(T3, result);
        assertEquals(3, instance.size());
    }

    @Test(expected = KolekceException.class)
    public void test_12_05_OdeberAktualni() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.odeberAktualni();
        fail();
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="13 Testy metody odeberZaAktualnim">
    @Test
    public void test_13_01_OdeberZaAktualnim() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T2);
        instance.vlozPrvni(T1);
        instance.vlozPrvni(T3);
        instance.nastavPrvni();
        TestClass result = instance.odeberZaAktualnim();
        assertEquals(T1, result);
    }

    @Test(expected = KolekceException.class)
    public void test_13_02_OdeberZaAktualnim() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.odeberZaAktualnim();
        fail();
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="14 Testy iterátoru">
    @Test
    public void test_14_01_Iterator() {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T2);
        Iterator<TestClass> iterator = instance.iterator();
        assertEquals(T2, iterator.next());
    }

    @Test
    public void test_14_02_Iterator() {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        Iterator<TestClass> iterator = instance.iterator();
        assertFalse(iterator.hasNext());

        instance.vlozPrvni(T1);
        iterator = instance.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(T1, iterator.next());
        assertFalse(iterator.hasNext());

        instance.vlozPosledni(T2);
        instance.vlozPosledni(T3);

        assertTrue(iterator.hasNext());
        assertEquals(T2, iterator.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void test_14_03_Iterator() {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T2);
        Iterator<TestClass> iterator = instance.iterator();
        assertEquals(T2, iterator.next());
        iterator.next();
        fail();
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="15 Testy na velikost seznamu">
    @Test
    public void test_15_01_JePrazdny() {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        assertTrue(instance.jePrazdny());
    }

    @Test
    public void test_15_02_JePrazdny() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        assertFalse(instance.jePrazdny());
        instance.odeberPrvni();
        assertTrue(instance.jePrazdny());
    }

    @Test
    public void test_15_03_Size() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        assertEquals(0, instance.size());
        instance.vlozPosledni(T1);
        instance.vlozPosledni(T2);
        instance.vlozPosledni(T3);
        assertEquals(3, instance.size());
        instance.odeberPosledni();
        assertEquals(2, instance.size());
        try {
            instance.odeberPrvni();
            assertEquals(1, instance.size());
            instance.vlozPosledni(T3);
            instance.vlozPosledni(T4);
            assertEquals(3, instance.size());
            instance.nastavPrvni();
            instance.odeberZaAktualnim();
            assertEquals(2, instance.size());

            TestClass[] list = dejPole(instance);
            assertEquals(list.length, 2);
        } catch (KolekceException e) {
            fail();
        }
    }
	 @Test
    public void test_15_04_Size() throws KolekceException{
         LinkSeznam<TestClass> instance = new LinkSeznam<>();
         for (int i = 0; i < 1000; i++) {
            instance.vlozPrvni(T1);
            instance.vlozPosledni(T2);
        }     
         for (int i = 0; i < 500; i++) {
            instance.odeberPrvni();
            instance.odeberPosledni();   
        }
         
          assertEquals(1000, instance.size());
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="16 Výkonnostní testy">
    @Test(timeout = 200L)
    public void test_Size10() {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        for (int i = 0; i < 1000000; i++) {
            instance.vlozPrvni(T1);
        }
        int p = instance.size();
    }

    @Test
    public void test_LinkSeznam_stream() {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T4);
        instance.vlozPrvni(T3);
        instance.vlozPrvni(T2);
        instance.vlozPrvni(T1);

        assertEquals(4L, instance.stream().count());
        TestClass[] array = dejPole(instance);
        assertArrayEquals(instance.stream().toArray(), array);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Privátní pomocné metody">
    private TestClass[] dejPole(LinkSeznam<TestClass> instance) {
        int pocet = instance.size();
        TestClass[] pole = new TestClass[pocet];
        Iterator<TestClass> iterator = instance.iterator();
        for (int i = 0; i < pocet; i++) {
            pole[i] = iterator.next();
        }
        return pole;
    }
    //</editor-fold>
}
