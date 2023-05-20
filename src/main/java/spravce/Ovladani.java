/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spravce;

import data.KomponentaPocitaceBase;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import kolekce.LinkSeznam;

/**
 *
 * Toto rozhraní slouží k implementaci funkcí příkazového řádku pro program,
 * který uživateli nabízí výběr akcí, které má provést. Jedná se o kód typu
 * příkazového řádku, který zajišťuje, aby uživatel mohl psát příkazy (a jejich
 * zkratky) a pracovat s nimi. Níže je uveden popis funkcí uvnitř uvedeného
 * kódu.
 */
public interface Ovladani extends Iterable<KomponentaPocitaceBase> {

    /**
     * novy() vrátí nově vytvořenou komponentu.
     *
     * @return KomponentaPocitaceBase
     * @param komponenta
     */
    boolean novy(KomponentaPocitaceBase komponenta);

    /**
     * najdi() vrací predikát (hodnotu filtru) pro účely vyhledávání. v
     * implementaci generuji predikát tak, že převezmu uživatelský vstup a
     * transformuji ho na predikát, aby byl použitelný.
     *
     * @param predicate
     * @return KomponentaPocitaceBase
     *
     */
    LinkSeznam<KomponentaPocitaceBase> najdi(Predicate<KomponentaPocitaceBase> predicate);

    /**
     * Funkce odeber() vrací predikát jako výše, ale pro účely odstranění
     * nalezené komponenty.
     *
     * @return KomponentaPocitaceBase
     * @param predicate
     */
    KomponentaPocitaceBase odeber(Predicate<KomponentaPocitaceBase> predicate);

    /**
     * Funkce dej() vrací aktuální komponentu v seznamu.
     *
     * @return KomponentaPocitaceBase
     */
    KomponentaPocitaceBase dej();

    /**
     *
     * edituj() prostřednictvím konzumenta přebírá věci z objektů a z výsledku
     * rozhoduje, které z nich a jak upravit.
     *
     * @return boolean
     * @param consumer
     */
    boolean edituj(Consumer<KomponentaPocitaceBase> consumer);

    /**
     * vyjmi() vrací komponentu, která byla ze seznamu vyjmuta (aktuální).
     *
     * @return KomponentaPocitaceBase
     */
    KomponentaPocitaceBase vyjmi();

    /**
     * Funkce prvni() vrací true, pokud je v seznamu prvni komponenta.
     *
     * @return boolean
     */
    boolean prvni();

    /**
     * dalsi() vrací true, pokud je v seznamu dalsi komponenta.
     *
     * @return boolean
     */
    boolean dalsi();

    /**
     * predchozi() vrací true, pokud je v seznamu předchozí komponenta.
     *
     * @return boolean
     */
    boolean predchozi();

    /**
     *
     * posledni() vrací true, pokud je v seznamu poslední komponenta.
     *
     * @return boolean
     */
    boolean posledni();

    /**
     * pocet() vrací velikost seznamu.
     *
     * @return int
     */
    int pocet();

    /**
     * obnov() vrací true, pokud je operace (precteni datoveho souboru) úspěšná.
     *
     * @return boolean
     * @param filename
     */
    boolean obnov(String filename);

    /**
     * zalohuj() vrací true, pokud je operace (ulozeni datoveho souboru) úspěšná.
     *
     * @return boolean
     * @param filename
     */
    boolean zalohuj(String filename);

    /**
     * nactiText() vrací true, pokud je operace (precteni textoveho souboru) úspěšná.
     *
     * @return boolean
     * @param filename
     */
    boolean nactiText(String filename);

    /**
     * ulozText() vrací true, pokud je operace (ulozeni textoveho souboru) úspěšná.
     *
     * @return boolean
     * @param filename
     */
    boolean ulozText(String filename);

    /**
     * generuj() vrací true, pokud je pozadovany pocet objektu uspesne vygenerovan.
     * @return boolean
     * @param kvantita
     */
    boolean generuj(int kvantita);

    /**
     * Funkce zrus() vrací true, pokud byl seznam vymazán.
     * @return boolean
     */
    boolean zrus();

    default Stream<KomponentaPocitaceBase> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

}
