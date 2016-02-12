package no.westerdals.student.loktho14.PG4100.InnleveringEn;

import java.util.Scanner;

/**
 * Created by Thorstein on 18.01.2016.
 */
public class Kunde implements Runnable {
    private final Utleier UTLEIER = Utleier.getUtleier();
    private final int MAKS_SEKUNDER_UT = 2000;
    private final int MAKS_SEKUNDER_INN = 6000;
    private final int MIN_SEKUNDER = 1000;
    private static final Scanner SCANNER = new Scanner(System.in);
    private String kundeNavn;
    private boolean startRun;

    //Konstruktør
    public Kunde(String kundeNavn) {
        setKundeNavn(kundeNavn);
        this.startRun = true;
    }

    //Konstruktør dersom jeg vil teste Scanner.
    public Kunde() {
        this(SCANNER.next());
    }

    //Getter metode for å få navnet til kunde
    public String getKundeNavn() {
        return kundeNavn;
    }

    //Setter metode
    public void setKundeNavn(String kundeNavn) {
        this.kundeNavn = kundeNavn;
    }

    public void stoppRun() {
        startRun = false;
    }


    //toString metode for å skrive ut navnet til kunden
    @Override
    public String toString() {
        return kundeNavn;
    }

    //equals metode for å sjekke kundenavn opp mot hverandre
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Kunde kunde = (Kunde) o;

        return getKundeNavn().equals(kunde.getKundeNavn());
    }

    /*
    * Run metode som får kunden til å vente 1-10 sek før den leier en bil
    * den får også kunden til å vente 1-3 sek med å levere
    * */
    @Override
    public void run() {
        while (startRun) {
            try {
                Thread.sleep(leiTid());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            UTLEIER.leiLeiebil(this);

            try {
                Thread.sleep(leverTid());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            UTLEIER.leverTilbakeLeiebil(this);
        }
    }

    /*
    * Metode for å få en tråd til å sove
    * */
    private int leiTid() {
        return ((int) (Math.random() * MAKS_SEKUNDER_UT) + MIN_SEKUNDER);
    }

    private int leverTid() {
        return ((int) (Math.random() * MAKS_SEKUNDER_INN) + MIN_SEKUNDER);
    }
    /*
    * Metode for å få en tråd til å sove - slutt
    * */

}