package be.thomasmore.androidproject;

/**
 * Created by Ricardo van Zeijl.
 *
 */

public class BepaaldLidwoord {
    private long bepaaldLidwoordID;
    private String bepaaldLidwoord;

    public long getBepaaldLidwoordID() {
        return bepaaldLidwoordID;
    }

    public void setBepaaldLidwoordID(long bepaaldLidwoordID) {
        this.bepaaldLidwoordID = bepaaldLidwoordID;
    }

    public String getBepaaldLidwoord() {
        return bepaaldLidwoord;
    }

    public void setBepaaldLidwoord(String bepaaldLidwoord) {
        this.bepaaldLidwoord = bepaaldLidwoord;
    }

    public BepaaldLidwoord() {
    }

    public BepaaldLidwoord(long bepaaldLidwoordID, String bepaaldLidwoord) {
        this.bepaaldLidwoordID = bepaaldLidwoordID;
        this.bepaaldLidwoord = bepaaldLidwoord;
    }

    @Override
    public String toString() {
        return "BepaaldLidwoord{" +
                "bepaaldLidwoordID=" + bepaaldLidwoordID +
                ", bepaaldLidwoord='" + bepaaldLidwoord + '\'' +
                '}';
    }
}
