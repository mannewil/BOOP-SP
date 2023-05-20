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
public class KomponentaPocitaceGPU extends KomponentaPocitaceBase {

    private int pocetFanu = 0;
    private int hlasitost = 0;
    

    public KomponentaPocitaceGPU(int idKomponenty, String nazevKomponenty, int rychlostMHZ, int kapacitaPametiMB, int pocetFanu, int hlasitost) {
        super(idKomponenty, nazevKomponenty, TypyKomponent.GPU, rychlostMHZ, kapacitaPametiMB);
        setPocetFanu(pocetFanu);
        setHlasitost(hlasitost);
    }

    public int getPocetFanu() {
        return pocetFanu;
    }

    public void setPocetFanu(int pocetFanu) {
        if (pocetFanu < 1) {
            throw new IllegalArgumentException("");
        }
        this.pocetFanu = pocetFanu;
    }

    public int getHlasitost() {
        return hlasitost;
    }

    public void setHlasitost(int hlasitost) {
        if (hlasitost < 1) {
            throw new IllegalArgumentException("");
        }
        this.hlasitost = hlasitost;
    }

    @Override
    public String toString() {
        return super.toString() + ","
                + String.valueOf(this.pocetFanu) + ","
                + String.valueOf(this.hlasitost);
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
        final KomponentaPocitaceGPU other = (KomponentaPocitaceGPU) obj;
        if (this.pocetFanu != other.pocetFanu) {
            return false;
        }
        return this.hlasitost == other.hlasitost;
    }

}
