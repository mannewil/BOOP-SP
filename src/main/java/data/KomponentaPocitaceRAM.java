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
public class KomponentaPocitaceRAM extends KomponentaPocitaceBase {

    public enum TypPameti {
        DDR2, DDR3, DDR4, DDR5
    }

    private boolean dualChannel = false;
    private TypPameti typPameti;

    public KomponentaPocitaceRAM(int idKomponenty, String nazevKomponenty, int rychlostMHZ, int kapacitaPametiMB, TypPameti typPameti, boolean dualChannel) {
        super(idKomponenty, nazevKomponenty, TypyKomponent.RAM, rychlostMHZ, kapacitaPametiMB);
        this.typPameti = typPameti;
        setDualChannel(dualChannel);

    }

    public boolean isDualChannel() {
        return dualChannel;
    }

    public void setDualChannel(boolean dualChannel) {
        this.dualChannel = dualChannel;
    }

    public TypPameti getTypPameti() {
        return typPameti;
    }

    public void setTypPameti(TypPameti typPameti) {
        this.typPameti = typPameti;
    }

    @Override
    public String toString() {
        return super.toString() + ","
                + String.valueOf(this.typPameti) + ","
                + String.valueOf(this.dualChannel);
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
        final KomponentaPocitaceRAM other = (KomponentaPocitaceRAM) obj;
        if (this.dualChannel != other.dualChannel) {
            return false;
        }
        return this.typPameti == other.typPameti;
    }

}
