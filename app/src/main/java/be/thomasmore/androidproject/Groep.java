package be.thomasmore.androidproject;

/**
 * Created by Ricardo van Zeijl on 4/12/2017.
 */

public class Groep {
    private long groepID;
    private String naam;


    public long getGroepID() {
        return groepID;
    }

    public void setGroepID(long groepID) {
        this.groepID = groepID;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Groep() {
    }

    public Groep(long groepID, String naam) {
        this.groepID = groepID;
        this.naam = naam;
    }

    @Override
    public String toString() {
        return naam;
    }
}
