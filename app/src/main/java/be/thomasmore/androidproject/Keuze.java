package be.thomasmore.androidproject;

/**
 * Created by Ricardo van Zeijl.
 *
 */

public class Keuze {
    private long keuzeID;
    private String keuze;

    public long getKeuzeID() {
        return keuzeID;
    }

    public void setKeuzeID(long keuzeID) {
        this.keuzeID = keuzeID;
    }

    public String getKeuze() {
        return keuze;
    }

    public void setKeuze(String keuze) {
        this.keuze = keuze;
    }

    public Keuze() {
    }

    public Keuze(long keuzeID, String keuze) {
        this.keuzeID = keuzeID;
        this.keuze = keuze;
    }

    @Override
    public String toString() {
        return "Keuze{" +
                "keuzeID=" + keuzeID +
                ", keuze='" + keuze + '\'' +
                '}';
    }
}
