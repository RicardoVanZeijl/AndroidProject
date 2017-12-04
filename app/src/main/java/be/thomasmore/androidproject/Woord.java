package be.thomasmore.androidproject;

/**
 * Created by ricar on 4/12/2017.
 */

public class Woord {
    private long woordID;
    private String woord;

    public long getWoordID() {
        return woordID;
    }

    public void setWoordID(long woordID) {
        this.woordID = woordID;
    }

    public String getWoord() {
        return woord;
    }

    public void setWoord(String woord) {
        this.woord = woord;
    }

    public Woord() {
    }

    public Woord(long woordID, String woord) {
        this.woordID = woordID;
        this.woord = woord;
    }

    @Override
    public String toString() {
        return "Woord{" +
                "woordID=" + woordID +
                ", woord='" + woord + '\'' +
                '}';
    }
}

