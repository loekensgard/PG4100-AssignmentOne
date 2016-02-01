package no.westerdals.student.loktho14.PG4100.InnleveringEn;

/**
 * Created by Thorstein on 18.01.2016.
 */
public class Kunde implements Runnable {
    private final Utleier UTLEIER = Utleier.getUtleier();
    private final int MAKS_SEKUNDER_UT = 2000;
    private final int MAKS_SEKUNDER_INN = 6000;
    private final int MIN_SEKUNDER = 1000;
    private String kundeNavn;
    private boolean startRun = true;

    //Konstruktør
    public Kunde(String kundeNavn) {
        setKundeNavn(kundeNavn);
    }

    //Getter metode for å få navnet til kunde
    public String getKundeNavn() {
        return kundeNavn;
    }

    //Setter metode
    public void setKundeNavn(String kundeNavn) {
        this.kundeNavn = kundeNavn;
    }

    public void setTestBruk(Boolean n) {
        this.startRun = n;
    }

    //Metode for stoppe run metoden
    public void stoppRun() {
        setTestBruk(false);
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
            leiThread();
            UTLEIER.leiLeiebil(this);
            leverThread();
            UTLEIER.leverTilbakeLeiebil(this);
        }
    }

    /*
    * Metode for å få en tråd til å sove
    * */
    public void leiThread() {
        try {
            Thread.sleep((int) (Math.random() * MAKS_SEKUNDER_UT) + MIN_SEKUNDER);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void leverThread() {
        try {
            Thread.sleep((int) (Math.random() * MAKS_SEKUNDER_INN) + MIN_SEKUNDER);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*
    * Metode for å få en tråd til å sove - slutt
    * */

}