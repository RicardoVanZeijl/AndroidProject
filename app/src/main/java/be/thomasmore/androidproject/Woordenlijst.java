package be.thomasmore.androidproject;

/**
 * Created by Ricardo van Zeijl on 4/12/2017.
 */

public class Woordenlijst {
    private long woordenlijstID;
    private long lijstID;
    private long woordID;

    public long getWoordenlijstID() {
        return woordenlijstID;
    }

    public void setWoordenlijstID(long woordenlijstID) {
        this.woordenlijstID = woordenlijstID;
    }

    public long getLijstID() {
        return lijstID;
    }

    public void setLijstID(long lijstID) {
        this.lijstID = lijstID;
    }

    public long getWoordID() {
        return woordID;
    }

    public void setWoordID(long woordID) {
        this.woordID = woordID;
    }

    public Woordenlijst() {
    }

    public Woordenlijst(long woordenlijstID, long lijstID, long woordID) {
        this.woordenlijstID = woordenlijstID;
        this.lijstID = lijstID;
        this.woordID = woordID;
    }

    @Override
    public String toString() {
        return "Woordenlijst{" +
                "woordenlijstID=" + woordenlijstID +
                ", lijstID=" + lijstID +
                ", woordID=" + woordID +
                '}';
    }
}
