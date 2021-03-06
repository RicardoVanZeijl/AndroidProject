package be.thomasmore.androidproject;

/**
 * Created by Ricardo van Zeijl.
 *
 */

public class Lijst {
    private long lijstID;
    private String naam;

    public long getLijstID() {
        return lijstID;
    }

    public void setLijstID(long lijstID) {
        this.lijstID = lijstID;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Lijst() {
    }

    public Lijst(long lijstID, String naam) {
        this.lijstID = lijstID;
        this.naam = naam;
    }

    @Override
    public String toString() {
        return naam;
    }
}
