package be.thomasmore.androidproject;

/**
 * Created by Ricardo van Zeijl on 4/12/2017.
 *
 */

public class Onderzoek {
    private long onderzoekID;
    private long groepID;
    private long lijstID;
    private long onderdeelID;

    public long getOnderzoekID() {
        return onderzoekID;
    }

    public void setOnderzoekID(long onderzoekID) {
        this.onderzoekID = onderzoekID;
    }

    public long getGroepID() {
        return groepID;
    }

    public void setGroepID(long groepID) {
        this.groepID = groepID;
    }

    public long getLijstID() {
        return lijstID;
    }

    public void setLijstID(long lijstID) {
        this.lijstID = lijstID;
    }

    public long getOnderdeelID() {
        return onderdeelID;
    }

    public void setOnderdeelID(long onderdeelID) {
        this.onderdeelID = onderdeelID;
    }

    public Onderzoek() {
    }

    public Onderzoek(long onderzoekID, long groepID, long lijstID, long onderdeelID) {
        this.onderzoekID = onderzoekID;
        this.groepID = groepID;
        this.lijstID = lijstID;
        this.onderdeelID = onderdeelID;
    }

    @Override
    public String toString() {
        return "Onderzoek{" +
                "onderzoekID=" + onderzoekID +
                ", groepID=" + groepID +
                ", lijstID=" + lijstID +
                ", onderdeelID=" + onderdeelID +
                '}';
    }

}
