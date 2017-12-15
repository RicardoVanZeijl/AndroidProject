package be.thomasmore.androidproject;

/**
 * Created by Ricardo van Zeijl on 4/12/2017.
 *
 */

public class Onderdeel {
    private long onderdeelID;
    private String naam;

    public long getOnderdeelID() {
        return onderdeelID;
    }

    public void setOnderdeelID(long onderdeelID) {
        this.onderdeelID = onderdeelID;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Onderdeel() {
    }

    public Onderdeel(long onderdeelID, String naam) {
        this.onderdeelID = onderdeelID;
        this.naam = naam;
    }

    @Override
    public String toString() {
        return naam;
    }
}
