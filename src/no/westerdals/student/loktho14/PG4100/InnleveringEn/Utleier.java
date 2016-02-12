package no.westerdals.student.loktho14.PG4100.InnleveringEn;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Thorstein on 26.01.2016.
 */
public class Utleier {
    private final ArrayList<Leiebil> LEIEBILER = new ArrayList<>();
    private final ReentrantLock LOCK = new ReentrantLock();
    private final LagLeiebil LAG_LEIEBIL;
    private static final String MAKS_STJERNER = "*****************";
    private static final String MIN_STJERNER = "***********";
    private static final String KOMMA = ", ";
    private static final String LINJE_SKIFT = "\n";
    private Condition ingenLedig = LOCK.newCondition();
    private static Utleier utleier = null;

    //konstruktør som lager fem leiebiler ved hjelp av lagLeiebil klasse
    public Utleier() {
        LAG_LEIEBIL = new LagLeiebil(5);
        LEIEBILER.addAll(LAG_LEIEBIL.lagLeieBiler());
    }

    public Utleier(String regnummer) {
        LAG_LEIEBIL = null;
        Leiebil leiebil = new Leiebil(regnummer);
        LEIEBILER.add(leiebil);

    }

    //Returnerer en instace av Utleier, slik at jeg bruker samme utleier overalt og ikke lager nye.
    public static Utleier getUtleier() {
        if (utleier == null) {
            utleier = new Utleier();
        }

        return utleier;
    }

    //returnerer listen med leiebiler slik at det er enklere å teste
    public ArrayList<Leiebil> getLeiebiler() {
        return LEIEBILER;
    }

    /*
    * Metode for å leie en leiebil. Jeg vil gå igjennom lista med leiebiler og sjekke om en av de er ledig
    * og leie den ut.
    * Hvis det ikke er en ledig bil vil await bli kalt frem til en bil leveres.
    * Jeg printer også hva som skjer og status på alle bilene.
    * */
    public void leiLeiebil(Kunde kunde) {
        LOCK.lock();
        try {
            while (ingenLedigeBiler()) {
                fikkIkkeleid(kunde);
                leiebilStatus();
                ingenLedig.await();
            }

            leiLedigLeiebil(kunde);
            leiebilStatus();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }


    private Leiebil leiLedigLeiebil(Kunde kunde) {
        for (Leiebil leiebil : LEIEBILER) {
            if (!leiebil.getleid()) {
                leiebil.lei(kunde);
                fikkLeid(kunde, leiebil);
                return leiebil;
            }
        }
        return null;
    }

    //Metode som sjekker om alle leiebilene mine er leid ut eller ikke
    private boolean ingenLedigeBiler() {
        for (Leiebil leiebil : LEIEBILER) {
            if (!leiebil.getleid()) {
                return false;
            }
        }
        return true;
    }

    /*
    * Metode som går igjennom alle leiebilene og sjekker om en kunde tilhører denne leiebilen.
    * Dersom den finner en kunde som leier leiebilen vil den levere den tilbake.
    * Må bruke samme lock her for å unngå en feilmelding.
    * */
    public void leverTilbakeLeiebil(Kunde kunde) {
        LOCK.lock();
        try {
            for (Leiebil leiebil : LEIEBILER) {
                if (leiebil.getLeieKunde() == kunde) {
                    leiebil.leverLeiebil();
                    ingenLedig.signal();
                    leverteTilbake(kunde, leiebil);
                }
            }
            leiebilStatus();
        } finally {
            LOCK.unlock();
        }
    }

    //Metode for å printe ut statusen til alle leiebilene
    public void leiebilStatus() {
        System.out.println(MIN_STJERNER + " Status for utleiebilene " + MAKS_STJERNER);
        for (Leiebil leiebil : LEIEBILER) {
            System.out.print(leiebil.toString() + KOMMA);
        }
        System.out.println();
        System.out.println(MIN_STJERNER + " Status slutt " + MAKS_STJERNER + LINJE_SKIFT);
    }

    private void fikkLeid(Kunde kunde, Leiebil leiebil) {
        System.out.println(kunde + " har leid bilen med regNr " + leiebil.getRegNummer());
    }

    private void fikkIkkeleid(Kunde kunde) {
        System.out.println(kunde.getKundeNavn() + " fikk ikke leid bil");
    }

    private void leverteTilbake(Kunde kunde, Leiebil leiebil) {
        System.out.println(kunde + " leverte tilbake bilen med regNr " + leiebil.getRegNummer());
    }

}
