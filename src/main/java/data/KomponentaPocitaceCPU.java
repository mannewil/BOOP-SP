/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.util.Locale;

/**
 *
 * @author wille
 */
public class KomponentaPocitaceCPU extends KomponentaPocitaceBase {

    private int pocetJader = 0;
    private int pocetVlaken = 0;

    public KomponentaPocitaceCPU(int idKomponenty, String nazevKomponenty, int rychlostMHZ, int pocetJadru, int pocetVlaken) {
        super(idKomponenty, nazevKomponenty, TypyKomponent.CPU, rychlostMHZ, 0);
        setPocetJader(pocetJadru);
        setPocetVlaken(pocetVlaken);
    }

    public int getPocetJader() {
        return pocetJader;
    }

    public void setPocetJader(int pocetJader) {
        if (pocetJader < 1) {
            throw new IllegalArgumentException("Chybne zadani!");
        }
        this.pocetJader = pocetJader;
    }

    public int getPocetVlaken() {
        return pocetVlaken;
    }

    public void setPocetVlaken(int pocetVlaken) {
        if (pocetVlaken < 1) {
            throw new IllegalArgumentException("Chybne zadani!");
        }
        this.pocetVlaken = pocetVlaken;
    }

    @Override
    public String toString() {
        return super.toString() + ","
                + String.valueOf(this.pocetJader) + ","
                + String.valueOf(this.pocetVlaken);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final KomponentaPocitaceCPU other = (KomponentaPocitaceCPU) obj;
        if (this.pocetJader != other.pocetJader) {
            return false;
        }
        return this.pocetVlaken == other.pocetVlaken;
    }

}
