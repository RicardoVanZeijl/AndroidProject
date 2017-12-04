package be.thomasmore.androidproject;

/**
 * Created by ricar on 4/12/2017.
 */

public class Foutenlijst {
    private long foutenlijstID;
    private long onderzoekID;
    private long woordID;

    public long getFoutenlijstID() {
        return foutenlijstID;
    }

    public void setFoutenlijstID(long foutenlijstID) {
        this.foutenlijstID = foutenlijstID;
    }

    public long getOnderzoekID() {
        return onderzoekID;
    }

    public void setOnderzoekID(long onderzoekID) {
        this.onderzoekID = onderzoekID;
    }

    public long getWoordID() {
        return woordID;
    }

    public void setWoordID(long woordID) {
        this.woordID = woordID;
    }

    public Foutenlijst() {
    }

    public Foutenlijst(long foutenlijstID, long onderzoekID, long woordID) {
        this.foutenlijstID = foutenlijstID;
        this.onderzoekID = onderzoekID;
        this.woordID = woordID;
    }

    @Override
    public String toString() {
        return "Foutenlijst{" +
                "foutenlijstID=" + foutenlijstID +
                ", onderzoekID=" + onderzoekID +
                ", woordID=" + woordID +
                '}';
    }
}

