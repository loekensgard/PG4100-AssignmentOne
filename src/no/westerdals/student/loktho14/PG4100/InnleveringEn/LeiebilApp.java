package no.westerdals.student.loktho14.PG4100.InnleveringEn;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by Thorstein on 18.01.2016.
 */
public class LeiebilApp {
    private static final ArrayList<Kunde> KUNDER = new ArrayList<>();
    private static final ExecutorService TRAAD_STARTER = Executors.newFixedThreadPool(10);
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Skriv inn 5 navn:");

        //Må lage fem kunder før trådene startes.

        for (int i = 1; KUNDER.size() < 5; i++) {
            try {
                KUNDER.add(new Kunde(SCANNER.next()));
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Starter trådene som har blitt laget
        for (Kunde kunde : KUNDER) {
            TRAAD_STARTER.execute(kunde);
        }

        //Etter fem kunder så kan jeg lage kunder helt til det når 10 kunder, og den vil starte trådene hver for seg.
        while (true) {
            if (KUNDER.size() == 10) {
                break;
            }
            try {
                KUNDER.add(new Kunde(SCANNER.next()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            TRAAD_STARTER.execute(KUNDER.get(KUNDER.size() - 1));
        }

        SCANNER.close();
        TRAAD_STARTER.shutdown();
    }
}
