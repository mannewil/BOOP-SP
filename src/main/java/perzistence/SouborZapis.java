/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perzistence;

import data.KomponentaPocitaceBase;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileWriter;
import java.util.Iterator;
import kolekce.LinkSeznam;

/**
 *
 * @author wille
 */
public class SouborZapis {

    public static void zapisBinarni(LinkSeznam<KomponentaPocitaceBase> objekt, String jmenoSouboru) {
        if (objekt == null) {
            throw new NullPointerException("NullPointerException");
        }
        try {
            FileOutputStream otevrenySoubor = new FileOutputStream(jmenoSouboru);
            ObjectOutputStream zapisObjektu = new ObjectOutputStream(otevrenySoubor);
            zapisObjektu.writeObject(objekt);
            zapisObjektu.close();
            otevrenySoubor.close();
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException");
        } catch (IOException ex) {
            System.out.println("IOException");
        }
    }

    public static void zapisTextovy(LinkSeznam<KomponentaPocitaceBase> objekt, String jmenoSouboru) {

        if (objekt == null) {
            throw new NullPointerException("NullPointerException");
        }
        Iterator<KomponentaPocitaceBase> iterator = objekt.iterator();
        try {
            FileWriter zapisovacSouboru = new FileWriter(jmenoSouboru);
            while (iterator.hasNext()) {
                KomponentaPocitaceBase pomocnaKomponenta = iterator.next();
                zapisovacSouboru.write(pomocnaKomponenta.toString());
                if (iterator.hasNext()) {
                    zapisovacSouboru.write("\n");
                }
            }
            zapisovacSouboru.close();
        } catch (IOException ex) {
            System.out.println("IOException");
        }
    }

}
