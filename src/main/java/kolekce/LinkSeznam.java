/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kolekce;

import java.util.Iterator;
import java.util.NoSuchElementException;
import pomocna.Prvek;

/**
 *
 * @author wille
 */
public class LinkSeznam<E> implements SpojovySeznam<E>, java.io.Serializable {

    private static final long serialVersionUID = 5L;
    private int size;
    private Prvek<E> prvni;
    private Prvek<E> aktualni;
    private Prvek<E> posledni;

    public LinkSeznam() {
        zrus();
    }

    @Override
    public void nastavPrvni() throws KolekceException {
        if (!jePrazdny()) {
            aktualni = prvni;
        } else {
            throw new KolekceException("Chyba, seznam je prazdny.");
        }
    }

    @Override
    public void nastavPosledni() throws KolekceException {
        if (!jePrazdny()) {
            aktualni = posledni;
        } else {
            throw new KolekceException("Chyba, seznam je prazdny.");
        }
    }

    @Override
    public void dalsi() throws KolekceException {
        if (!jeDalsi()) {
            throw new KolekceException("Chyba, seznam je prazdny, nebo nema currPointer nema dalsiho prvku.");
        }
        aktualni = aktualni.next;
    }

    public Prvek<E> dejPredchozi(Prvek<E> prvek) throws KolekceException {
        if (jePrazdny() || prvek == null) {
            throw new KolekceException("Chyba, seznam je prazdny, nebo nema currPointer nema dalsiho prvku.");
        }

        Prvek<E> pomocny = prvni;
        while (pomocny.next != prvek) {
            pomocny = pomocny.next;
        }
        return pomocny;
    }

    public void predchozi() throws KolekceException {

        if (jePrazdny() || aktualni == null) {
            throw new KolekceException("Chyba, seznam je prazdny, nebo nema currPointer nema dalsiho prvku.");
        }
        if (aktualni == prvni) {
            throw new KolekceException("Chyba, aktualni prvek je prvni, kvuli tomu, neni mozne dostat do pozici ktera je predchozi k prvni.");
        }
        aktualni = dejPredchozi(aktualni);
    }

    @Override
    public boolean jeDalsi() {
        if (aktualni == null || jePrazdny()) {
            return false;
        }
        return (aktualni.next != null);
    }

    @Override
    public void vlozPrvni(E data) {
        if (data == null) {
            throw new NullPointerException("Data chybi!");
        }
        prvni = new Prvek<E>(data, prvni);
        if (posledni == null) {
            posledni = prvni;
        }
        //Prvek pomPrvek = new Prvek<>(data, prvek);
        //prvek = pomPrvek;
        size++;

    }

    @Override
    public void vlozPosledni(E data) {
        if (data == null) {
            throw new NullPointerException("Data chybi!");
        }

        if (jePrazdny()) {
            vlozPrvni(data);
            return;
        }
        posledni.next = new Prvek<E>(data, null);
        posledni = posledni.next;
        size++;
    }

    @Override
    public void vlozZaAktualni(E data) throws KolekceException {
        if (data == null) {
            throw new NullPointerException("Data chybi!");
        }
        if (aktualni == null) {
            throw new KolekceException("Aktualni prvek chybi!");
        }

        aktualni.next = new Prvek<>(data, aktualni.next);

        if (aktualni == posledni) {
            posledni = aktualni.next;
        }

        size++;
    }

    @Override
    public boolean jePrazdny() {
        if (prvni == null || size == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public E dejPrvni() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException("Seznam je prazdny, je to chybne.");
        }
        return prvni.data;
    }

    @Override
    public E dejPosledni() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException("Seznam je prazdny, nebo aktualni prvek neni zastaven.");
        }
        return posledni.data;
    }

    @Override
    public E dejAktualni() throws KolekceException {
        if (jePrazdny() || aktualni == null) {
            throw new KolekceException("Seznam je prazdny, nebo aktualni prvek neni zastaven.");
        }
        return aktualni.data;
    }

    @Override
    public E dejZaAktualnim() throws KolekceException {
        if (jePrazdny() || aktualni == null) {
            throw new KolekceException("Seznam je prazdny, nebo aktualni prvek neni zastaven.");
        }
        if (aktualni == posledni) {
            throw new KolekceException();
        }
        return aktualni.next.data;
    }

    @Override
    public E odeberPrvni() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException("Seznam je prazdny, je to chybne.");
        }

        E odebranaData = prvni.data;

        if (aktualni == prvni) {
            aktualni = null;
        }

        if (size == 1) {
            posledni = null;
        }

        prvni = prvni.next;
        size--;

        return odebranaData;
    }

    @Override
    public E odeberPosledni() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException("Seznam je prazdny, je to chybne.");
        }
        if (aktualni == posledni) {
            aktualni = null;
        }

        E odebranaData = posledni.data;
        if (size == 1) {
            prvni = null;
            posledni = null;
        } else {
            Prvek<E> pomocny = prvni;

            while (pomocny.next != posledni && pomocny.next != null) {
                pomocny = pomocny.next;
            }

            pomocny.next = null;
            posledni = pomocny;
        }
        size--;

        return odebranaData;
    }

    @Override
    public E odeberAktualni() throws KolekceException {
        if (jePrazdny() || aktualni == null) {
            throw new KolekceException("Seznam je prazdny, nebo aktualni prvek neni zastaven.");
        }
        if (aktualni == prvni) {
            return odeberPrvni();
        }
        if (aktualni == posledni) {
            return odeberPosledni();
        }

        E odebranaData = aktualni.data;

        Prvek<E> pomPointer = prvni;

        while (pomPointer.next != aktualni) {
            pomPointer = pomPointer.next;
        }
        pomPointer.next = aktualni.next;
        aktualni = null;
        size--;

        return odebranaData;
    }

    @Override
    public E odeberZaAktualnim() throws KolekceException {
        if (jePrazdny() || aktualni == null) {
            throw new KolekceException("Seznam je prazdny, nebo aktualni prvek neni zastaven.");
        }

        E odebranaData = null;

        if (aktualni.next != null) {
            odebranaData = aktualni.next.data;
            aktualni.next = aktualni.next.next;
            size--;
        } else {
            throw new KolekceException();
        }

        return odebranaData;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void zrus() {
        prvni = null;
        aktualni = null;
        posledni = null;
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            Prvek<E> prvniIterable = prvni;
            Prvek<E> currIterable = null;

            @Override
            public boolean hasNext() {

                if (currIterable == null) {
                    prvniIterable = prvni;
                    return prvniIterable != null;
                }
                return currIterable.next != null;
            }

            @Override
            public void remove() {
                if (currIterable == null) {
                    throw new IllegalStateException("Aktualni iterable prvek neukazuje na nic");
                }
                if (currIterable == prvni) {
                    try {
                        odeberPrvni();
                    } catch (KolekceException ex) {
                        System.out.println("Prvni prvek je prazdny");
                    }
                } else if (currIterable == aktualni) {
                    try {
                        odeberAktualni();
                    } catch (KolekceException ex) {
                        System.out.println("Aktualni prvek je prazdny nebo nenastaveny");
                    }
                } else if (currIterable == posledni) {
                    try {
                        odeberPosledni();
                    } catch (KolekceException ex) {
                        System.out.println("Posledni prvek je prazdny");
                    }
                } else {
                    try {
                        Prvek<E> pomocnyProOdeber = dejPredchozi(currIterable);
                        pomocnyProOdeber.next = currIterable.next;
                        size--;
                    } catch (KolekceException ex) {
                        System.out.println("Nastaveny iterable prvek je null.");
                    }
                }

            }

            @Override
            public E next() {
                if (hasNext()) {
                    if (currIterable != null) {
                        currIterable = currIterable.next;
                        E data = currIterable.data;
                        return data;
                    }
                    currIterable = prvniIterable;
                    return prvniIterable.data;
                }
                throw new NoSuchElementException();
            }
        };
    }

}
