package be.thomasmore.androidproject;

/**
 * Created by Ricardo van Zeijl on 17/12/2017.
 *
 */

public class SymantischWeb {
    private long symantischWebID;
    private long woordID;
    private long keuzeID;
    private short juist;

    public long getSymantischWebID() {
        return symantischWebID;
    }

    public void setSymantischWebID(long symantischWebID) {
        this.symantischWebID = symantischWebID;
    }

    public long getWoordID() {
        return woordID;
    }

    public void setWoordID(long woordID) {
        this.woordID = woordID;
    }

    public long getKeuzeID() {
        return keuzeID;
    }

    public void setKeuzeID(long keuzeID) {
        this.keuzeID = keuzeID;
    }

    public short getJuist() {
        return juist;
    }

    public void setJuist(short juist) {
        this.juist = juist;
    }

    public SymantischWeb() {
    }

    public SymantischWeb(long symantischWebID, long woordID, long keuzeID, short juist) {
        this.symantischWebID = symantischWebID;
        this.woordID = woordID;
        this.keuzeID = keuzeID;
        this.juist = juist;
    }

    @Override
    public String toString() {
        return "SymantischWeb{" +
                "symantischWebID=" + symantischWebID +
                ", woordID=" + woordID +
                ", keuzeID=" + keuzeID +
                ", juist=" + juist +
                '}';
    }
}
