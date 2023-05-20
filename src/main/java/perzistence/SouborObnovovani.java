/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perzistence;

import data.KomponentaPocitaceBase;
import data.KomponentaPocitaceCPU;
import data.KomponentaPocitaceGPU;
import data.KomponentaPocitaceRAM;
import data.KomponentaPocitaceRAM.TypPameti;
import data.TypyKomponent;
import kolekce.LinkSeznam;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author wille
 */
public class SouborObnovovani {

    public static LinkSeznam<KomponentaPocitaceBase> obnovaniBinarni(String jmenoSouboru) {
        LinkSeznam prectenySeznam = new LinkSeznam();

        try {
            FileInputStream otevrenySoubor = new FileInputStream(jmenoSouboru);
            ObjectInputStream objektPrecteni = new ObjectInputStream(otevrenySoubor);

            prectenySeznam = (LinkSeznam<KomponentaPocitaceBase>) objektPrecteni.readObject();
            objektPrecteni.close();
            otevrenySoubor.close();
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException");
        } catch (IOException ex) {
            System.out.println("IOException");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException");
        }
        return prectenySeznam;
    }

    public static LinkSeznam<KomponentaPocitaceBase> obnovaniTextovy(String jmenoSouboru) {
        LinkSeznam<KomponentaPocitaceBase> prectenySeznam = new LinkSeznam();
        File soubor = new File(jmenoSouboru);
        String line = "";
        try {
            Scanner sc = new Scanner(soubor);
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                Scanner sc2 = new Scanner(line);
                sc2.useDelimiter("[,]");
                int idKomponenty = sc2.nextInt();
                String valNazevK = sc2.next();
                TypyKomponent valTypK = TypyKomponent.valueOf(sc2.next());
                int valRychlost = sc2.nextInt();
                int valKapacita = sc2.nextInt();
                switch (valTypK) {
                    case CPU:
                        int valPJad = sc2.nextInt();
                        int valPVlak = sc2.nextInt();
                        KomponentaPocitaceCPU kompC = new KomponentaPocitaceCPU(idKomponenty, valNazevK, valRychlost, valPJad, valPVlak);
                        prectenySeznam.vlozPosledni(kompC);
                        break;
                    case GPU:
                        int valPFanu = sc2.nextInt();
                        int valHlas = sc2.nextInt();
                        KomponentaPocitaceGPU kompG = new KomponentaPocitaceGPU(idKomponenty, valNazevK, valRychlost, valKapacita, valPFanu, valHlas);
                        prectenySeznam.vlozPosledni(kompG);
                        break;
                    case RAM:
                        TypPameti valTypPameti = TypPameti.valueOf(sc2.next());
                        boolean valRamBool = Boolean.valueOf(sc2.next());
                        KomponentaPocitaceRAM kompR = new KomponentaPocitaceRAM(idKomponenty, valNazevK, valRychlost, valKapacita, valTypPameti, valRamBool);
                        prectenySeznam.vlozPosledni(kompR);
                        break;
                }
                sc2.close();
            }
            sc.close();
            return prectenySeznam;

        } catch (FileNotFoundException ex) {

            System.out.println("FileNotFoundException");
            return prectenySeznam;
        }

    }
}
