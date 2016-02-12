package no.westerdals.student.loktho14.PG4100.InnleveringEn;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by Thorstein on 18.01.2016.
 */
public class LeiebilApp {
    private static final ArrayList<Kunde> KUNDER = new ArrayList<>();
    private static final ExecutorService TRAAD_STARTER = Executors.newFixedThreadPool(10);
    private static final Scanner SCANNER = new Scanner(System.in);
    private static boolean run = true;

    public static void main(String[] args) {
        LeiebilApp app = new LeiebilApp();
        app.startTi();
    }

    public LeiebilApp() {
        System.out.println("Velkommen til bilutleier app! \nVi må lage noen kunder, vil du at vi skal lage de for deg?(ja/nei)");
        String a = SCANNER.next();


        if (a.equalsIgnoreCase("ja")) {
            KUNDER.add(new Kunde("Herman"));
            KUNDER.add(new Kunde("Thorstein"));
            KUNDER.add(new Kunde("Bastian"));
            KUNDER.add(new Kunde("Thomas"));
            KUNDER.add(new Kunde("Nils"));
            KUNDER.add(new Kunde("Jonas"));
            KUNDER.add(new Kunde("Arild"));
            KUNDER.add(new Kunde("Anita"));
            KUNDER.add(new Kunde("Josefine"));
            KUNDER.add(new Kunde("Johan Sebastian Bach"));
            run = false;
            System.out.println("Vi har nå laget " + KUNDER.size() + " kunder appen vil starte straks.");

            startKunder();
        } else if (a.equalsIgnoreCase("nei")) {
            System.out.println("Skriv inn minst 5 navn:");
            startFem();
        } else {
            System.out.println("Du svarte verken nei eller ja");
        }

    }

    private void startFem() {
        for (int i = 1; KUNDER.size() < 5; i++) {
            try {
                KUNDER.add(new Kunde(SCANNER.next()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        startKunder();
    }

    public void startTi() {
        while (run) {
            if (KUNDER.size() == 10) {
                run = false;
                break;
            }
            try {
                KUNDER.add(new Kunde(SCANNER.next()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            TRAAD_STARTER.execute(KUNDER.get(KUNDER.size() - 1));
        }

        TRAAD_STARTER.shutdown();
    }

    private void startKunder() {
        for (Kunde kunde : KUNDER) {
            TRAAD_STARTER.execute(kunde);
        }
    }

}



