package no.westerdals.student.loktho14.PG4100.InnleveringEn;

/**
 * Created by Thorstein on 18.01.2016.
 */
public class Leiebil {
    private final String BINDESTREK = " - ";
    private String regNummer;
    private Kunde leieKunde = null;
    private boolean leid;

    //Konstruktør
    public Leiebil(String regNummer) {
        setRegNummer(regNummer);
        setLeid(leid);
    }

    //Getter metoder
    public String getRegNummer() {
        return regNummer;
    }

    public Kunde getLeieKunde() {
        return leieKunde;
    }

    public boolean getleid() {
        return leid;
    }
    //Getter metoder slutt

    //Setter metoder
    public void setRegNummer(String regNummer) {
        this.regNummer = regNummer;
    }

    public void setLeieKunde(Kunde leieKunde) {
        this.leieKunde = leieKunde;
    }

    public void setLeid(Boolean leid) {
        this.leid = leid;
    }
    //Setter metoder slutt

    //Metode som sier om en bil er ledig eller ikke
    public String leidUt() {
        if (!leid) {
            return "Ledig";
        } else {
            return "Leid av " + getLeieKunde().getKundeNavn();
        }

    }

    //Metode for å levere en leiebil
    public void leverLeiebil() {
        setLeieKunde(null);
        setLeid(false);
    }

    //Metode for å leie en leiebil
    public void leiLeiebil(Kunde kunde) {
        setLeieKunde(kunde);
        setLeid(true);
    }

    //toSring-metode som returnerer registreringsnummer og om den er leid ut eller ikke
    @Override
    public String toString() {
        return getRegNummer() + BINDESTREK + leidUt();
    }

    //equals-metode som sjekker om bilene er like på registreringsnummeret.
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Leiebil leiebil = (Leiebil) o;

        return getRegNummer().equals(leiebil.regNummer);
    }

}
