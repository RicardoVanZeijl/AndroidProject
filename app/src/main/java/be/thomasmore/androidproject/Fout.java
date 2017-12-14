package be.thomasmore.androidproject;

/**
 * Created by Ricardo van Zeijl on 4/12/2017.
 *
 */

public class Fout {
    private long foutID;
    private long onderzoekID;
    private long woordID;

    public long getFoutID() {
        return foutID;
    }

    public void setFoutID(long foutID) {
        this.foutID = foutID;
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

    public Fout() {
    }

    public Fout(long foutID, long onderzoekID, long woordID) {
        this.foutID = foutID;
        this.onderzoekID = onderzoekID;
        this.woordID = woordID;
    }

    @Override
    public String toString() {
        return "Fout{" +
                "foutID=" + foutID +
                ", onderzoekID=" + onderzoekID +
                ", woordID=" + woordID +
                '}';
    }
}

