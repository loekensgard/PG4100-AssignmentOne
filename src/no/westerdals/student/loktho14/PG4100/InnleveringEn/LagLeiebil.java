package no.westerdals.student.loktho14.PG4100.InnleveringEn;

import java.util.ArrayList;

/**
 * Created by Thorstein on 26.01.2016.
 */
public class LagLeiebil {
    private final int MAKS_BILER = 5;
    private final boolean NY_BIL = false;
    private final int REG_NUMMER_MIN = 100000;
    private final int REG_NUMMER_MAKS = 800000;
    private final String REG_NUMMER_BOKSTAVER = "AB";
    private final ArrayList<Leiebil> LEIEBILER = new ArrayList<>();
    private int regNummerLengde;

    //Konstruktør
    public LagLeiebil(int regNummerLengde) {
        this.regNummerLengde = regNummerLengde;
    }

    /*
    * Metode for å lage en leiebil med et unikt registreringsnummer
    * */
    public Leiebil lagLeiebilRegNummer() {
        String regNummer = lagRegNummer();
        return new Leiebil(regNummer, NY_BIL);
    }

    /*
    * Metode for å lage et unikt registreringsnummer ved hjelp av math.random();
    * */
    private String lagRegNummer() {
        String regNummer = REG_NUMMER_BOKSTAVER;

        int nr = (int) (Math.random() * REG_NUMMER_MAKS) + REG_NUMMER_MIN;
        regNummer += String.valueOf(nr);

        return regNummer;
    }

    /*
    * Metode for å lag flere leiebiler (I dette tilfelle 5)
    * */
    public ArrayList<Leiebil> lagLeieBiler() {
        for (int i = 0; i < MAKS_BILER; i++) {
            LEIEBILER.add(lagLeiebilRegNummer());
        }
        return LEIEBILER;
    }
}
