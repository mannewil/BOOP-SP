/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generator;

import data.KomponentaPocitaceBase;
import data.KomponentaPocitaceCPU;
import data.KomponentaPocitaceGPU;
import data.KomponentaPocitaceRAM;
import data.KomponentaPocitaceRAM.TypPameti;
import java.util.Random;
import kolekce.LinkSeznam;

/**
 *
 * @author wille
 */
public class Generator {

    static private Random rand = new Random();
    static private int ID = 1;

    static private String[] mozneNazvyCPU = {"Intel Core 2 Duo", "Intel Core 2 Quad", "Intel Core i3",
        "Intel Core i5", "Intel Core i7", "Intel Core i9",
        "AMD Phenom II", "AMD Threadripper", "AMD Bulldozer",
        "AMD FX Series AM4"}; //10
    static private int[] moznyPocetJaderCPU = {2, 4, 6, 8, 12}; //4
    static private int[] moznaRychlostCPU = {1800, 2100, 2300, 2700, 2900, 3100, 3400}; //6

    static private String[] mozneNazvyGPU = {"MSI", "GIGABYTE", "Palit", "Xfx", "Sapphire", "ZOTAC", "EVGA",}; //7
    static private int[] moznaVideoKapacitaGPU = {1024, 2048, 3072, 4096, 6114, 8192, 12288}; //7
    static private int[] moznaRychlostGPU = {6000, 7000, 8000, 9000, 11000, 12000, 14000, 16000}; //8

    static private String[] mozneNazvyRAM = {"Kingston", "ADATA", "G.SKILL", "Corsair", "Cooler Master", "Samsung", "Patriot", "Crucial"}; //8
    static private int[] moznaKapacitaRAM = {1024, 2048, 4096, 6114, 8192, 12288, 16384, 32768, 65536}; //9
    static private int[] moznaRychlostRAM = {600, 760, 900, 1100, 1400, 1800, 2100, 2400, 2700, 2900, 3200, 3500, 3800, 4100}; //14

    private static KomponentaPocitaceCPU generateCPU() {
        int pocetJader = moznyPocetJaderCPU[rand.nextInt(moznyPocetJaderCPU.length)];
        KomponentaPocitaceCPU cpu = new KomponentaPocitaceCPU(ID, mozneNazvyCPU[rand.nextInt(mozneNazvyCPU.length)], moznaRychlostCPU[rand.nextInt(moznaRychlostCPU.length)], pocetJader, pocetJader * 2);
        return cpu;
    }

    private static KomponentaPocitaceGPU generateGPU() {
        int pocetFanu = 0;

        int vidKapacita = moznaVideoKapacitaGPU[rand.nextInt(moznaVideoKapacitaGPU.length)];

        if (vidKapacita <= 2048) {
            pocetFanu = 1;
        } else if (vidKapacita > 2048 && vidKapacita < 6114) {
            pocetFanu = 2;
        } else {
            pocetFanu = 3;
        }

        KomponentaPocitaceGPU gpu = new KomponentaPocitaceGPU(ID, mozneNazvyGPU[rand.nextInt(mozneNazvyGPU.length)], moznaRychlostGPU[rand.nextInt(moznaRychlostGPU.length)], vidKapacita, pocetFanu, rand.nextInt(39) + 1);
        return gpu;
    }

    private static KomponentaPocitaceRAM generateRAM() {
        TypPameti typ = null;

        int pomRychlost = moznaRychlostRAM[rand.nextInt(moznaRychlostRAM.length)];
        if (pomRychlost < 800) {
            typ = TypPameti.DDR2;
        } else if (pomRychlost > 800 && pomRychlost < 1866) {
            typ = TypPameti.DDR3;
        } else if (pomRychlost > 1866 && pomRychlost < 3200) {
            typ = TypPameti.DDR4;
        } else {
            typ = TypPameti.DDR5;
        }

        KomponentaPocitaceRAM ram = new KomponentaPocitaceRAM(ID, mozneNazvyRAM[rand.nextInt(mozneNazvyRAM.length)], pomRychlost, moznaKapacitaRAM[rand.nextInt(moznaKapacitaRAM.length)], typ, rand.nextBoolean());
        return ram;
    }

    public static LinkSeznam<KomponentaPocitaceBase> generate(int paramKvantita) {
        LinkSeznam<KomponentaPocitaceBase> seznam = new LinkSeznam();
        int rozh = 0;

        for (int i = 0; i < paramKvantita; i++) {
            rozh = rand.nextInt(3);
            switch (rozh) {
                case 0 ->
                    seznam.vlozPosledni(generateCPU());
                case 1 ->
                    seznam.vlozPosledni(generateGPU());
                case 2 ->
                    seznam.vlozPosledni(generateRAM());
            }
            ID++;
        }

        return seznam;
    }

    public static void setID(int ID) {
        Generator.ID = ID;
    }

    public static int getID() {
        return ID;
    }

    public static void refreshID() {
        ID = 1;
    }

}
