/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spravce;

import data.KomponentaPocitaceBase;
import generator.Generator;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import kolekce.KolekceException;
import kolekce.LinkSeznam;

/**
 *
 * @author wille
 */
public class Spravce implements Ovladani {
    
    LinkSeznam<KomponentaPocitaceBase> instance = new LinkSeznam();
    
    @Override
    public boolean novy(KomponentaPocitaceBase komponenta) {
        if (komponenta == null || dej() == null) {
            System.out.println("Komponenta je prazdna, ci zadna aktualni komponenta.");
            return false;
        }
        try {
            instance.vlozZaAktualni(komponenta);
            return true;
        } catch (KolekceException ex) {
            return false;
        }
    }
    
    public boolean vlozPosledni(KomponentaPocitaceBase komponenta) {
        if (komponenta == null) {
            System.out.println("Komponenta je prazdna");
            return false;
        }
        instance.vlozPosledni(komponenta);
        return true;
        
    }
    
    @Override
    public LinkSeznam<KomponentaPocitaceBase> najdi(Predicate<KomponentaPocitaceBase> predicate) {
        LinkSeznam<KomponentaPocitaceBase> nalKomponenty = new LinkSeznam();
        KomponentaPocitaceBase[] komponenty = (KomponentaPocitaceBase[]) instance.stream().filter(predicate).toArray();
        for (int i = 0; i < komponenty.length; i++) {
            nalKomponenty.vlozPosledni(komponenty[i]);
        }
        return nalKomponenty;
    }
    
    @Override
    public KomponentaPocitaceBase odeber(Predicate<KomponentaPocitaceBase> predicate) {
        Iterator<KomponentaPocitaceBase> it = iterator();
        
        while (it.hasNext()) {
            KomponentaPocitaceBase komponenta = it.next();
            if (predicate.test(komponenta)) {
                it.remove();
                return komponenta;
            }
        }
        return null;
    }
    
    @Override
    public KomponentaPocitaceBase dej() {
        try {
            return instance.dejAktualni();
        } catch (KolekceException ex) {
            System.out.println("");
            return null;
        }
    }
    
    @Override
    public boolean edituj(Consumer<KomponentaPocitaceBase> consumed) {
        try {
            consumed.accept(instance.dejAktualni());
            return true;
        } catch (KolekceException ex) {
            System.out.println("Aktualni prvek nenastaven!");
            return false;
        }
        
    }
    
    @Override
    public KomponentaPocitaceBase vyjmi() {
        try {
            return instance.odeberAktualni();
        } catch (KolekceException ex) {
            System.out.println("Zadny nastaveny prvek.");
            return null;
        }
    }
    
    @Override
    public boolean prvni() {
        try {
            instance.nastavPrvni();
            return true;
        } catch (KolekceException ex) {
            System.out.println("Seznam je prazdny");
            return false;
        }
    }
    
    @Override
    public boolean dalsi() {
        try {
            instance.dalsi();
            return true;
        } catch (KolekceException ex) {
            System.out.println("Zadna dalsi polozka");
            return false;
        }
    }
    
    @Override
    public boolean predchozi() {
        try {
            instance.predchozi();
            return true;
        } catch (KolekceException ex) {
            System.out.println("Zadna polozka pred nasi");
            return false;
        }
    }
    
    @Override
    public boolean posledni() {
        try {
            instance.nastavPosledni();
            return true;
        } catch (KolekceException ex) {
            System.out.println("Seznam je prazdny");
            return false;
        }
    }
    
    @Override
    public int pocet() {
        return instance.size();
    }
    
    @Override
    public boolean obnov(String fname) {
        instance = perzistence.SouborObnovovani.obnovaniBinarni(fname);
        return true;
    }
    
    @Override
    public boolean zalohuj(String fname) {
        perzistence.SouborZapis.zapisBinarni(instance, fname);
        return true;
    }
    
    @Override
    public boolean nactiText(String fname) {
        instance = perzistence.SouborObnovovani.obnovaniTextovy(fname);
        return true;
    }
    
    @Override
    public boolean ulozText(String fname) {
        perzistence.SouborZapis.zapisTextovy(instance, fname);
        return true;
    }
    
    @Override
    public boolean generuj(int pocet) {
        if (pocet < 1) {
            System.out.println("Pocet musi byt vetsi nez nula.");
            return false;
        }
        LinkSeznam<KomponentaPocitaceBase> linkSeznamPomocny;
        linkSeznamPomocny = generator.Generator.generate(pocet);
        Iterator<KomponentaPocitaceBase> it = linkSeznamPomocny.iterator();
        while (it.hasNext()) {
            instance.vlozPosledni(it.next());
        }
        
        return true;
    }
    
    @Override
    public boolean zrus() {
        instance.zrus();
        return true;
    }
    
    @Override
    public Iterator<KomponentaPocitaceBase> iterator() {
        return instance.iterator();
    }
    
    public void setID(int id) {
        Generator.setID(id);
    }
    
    public void refreshID() {
        Generator.refreshID();
    }
    
}
