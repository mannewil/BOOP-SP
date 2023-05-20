/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.util.Objects;

/**
 *
 * @author wille
 */
public abstract class KomponentaPocitaceBase implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int idKomponenty = 0;
    private String nazevKomponenty = "";
    TypyKomponent typ = TypyKomponent.NeNastavena;

    private int rychlostMHZ = 0;
    private int kapacitaPametiMB = 0;

    public KomponentaPocitaceBase(int ID, String nazevKomponenty, TypyKomponent typ, int rychlostMHZ, int kapacitaPametiMB) {
        setIdKomponenty(ID);
        setTyp(typ);
        setNazevKomponenty(nazevKomponenty);
        setRychlostMHZ(rychlostMHZ);
        setKapacitaPametiMB(kapacitaPametiMB);
    }

    public String getNazevKomponenty() {
        return nazevKomponenty;
    }

    public void setNazevKomponenty(String nazevKomponenty) {
        if (nazevKomponenty.equals(null)) {
            throw new NullPointerException("Chybne zadani");
        }
        this.nazevKomponenty = nazevKomponenty;
    }

    public TypyKomponent getTyp() {
        return typ;
    }

    public void setTyp(TypyKomponent typ) {
        if (typ == null) {
            throw new NullPointerException("Chyba");
        }
        if (typ == TypyKomponent.NeNastavena) {
            throw new IllegalArgumentException("Nenastaveny typ komponenty!");
        }
        this.typ = typ;
    }

    public int getRychlostMHZ() {
        return rychlostMHZ;
    }

    public void setRychlostMHZ(int rychlostMHZ) {
        if (rychlostMHZ < 0) {
            throw new IllegalArgumentException("Chybne zadani!");
        }
        this.rychlostMHZ = rychlostMHZ;
    }

    public int getKapacitaPametiMB() {
        return kapacitaPametiMB;
    }

    public void setKapacitaPametiMB(int kapacitaPametiMB) {
        if (kapacitaPametiMB < 0) {
            throw new IllegalArgumentException("Chybne zadani!");
        }
        this.kapacitaPametiMB = kapacitaPametiMB;
    }

    public int getIdKomponenty() {
        return idKomponenty;
    }

    public void setIdKomponenty(int idKomponenty) {
        if (idKomponenty < 0) {
            throw new IllegalArgumentException("Chybne zadani!");
        }
        this.idKomponenty = idKomponenty;
    }

    @Override
    public String toString() {
        return this.idKomponenty + ","
                + this.nazevKomponenty + ","
                + this.typ.toString() + ","
                + String.valueOf(this.rychlostMHZ) + ","
                + String.valueOf(this.kapacitaPametiMB);
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final KomponentaPocitaceBase other = (KomponentaPocitaceBase) obj;
        if (this.rychlostMHZ != other.rychlostMHZ) {
            return false;
        }
        if (this.kapacitaPametiMB != other.kapacitaPametiMB) {
            return false;
        }
        if (!Objects.equals(this.nazevKomponenty, other.nazevKomponenty)) {
            return false;
        }
        return Objects.equals(this.typ, other.typ);
    }

}
