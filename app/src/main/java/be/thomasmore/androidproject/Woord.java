package be.thomasmore.androidproject;

/**
 * Created by Ricardo van Zeijl.
 *
 */

public class Woord {
    private long woordID;
    private String woord;
    private short onbepaaldLidwoord;
    private long bepaaldLidwoordID;
    private String definitie;
    private String juisteContextzin;
    private String fouteContextzin;
    private String lettergrepen;

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

    public short getOnbepaaldLidwoord() {
        return onbepaaldLidwoord;
    }

    public void setOnbepaaldLidwoord(short onbepaaldLidwoord) {
        this.onbepaaldLidwoord = onbepaaldLidwoord;
    }

    public long getBepaaldLidwoordID() {
        return bepaaldLidwoordID;
    }

    public void setBepaaldLidwoordID(long bepaaldLidwoordID) {
        this.bepaaldLidwoordID = bepaaldLidwoordID;
    }

    public String getDefinitie() {
        return definitie;
    }

    public void setDefinitie(String definitie) {
        this.definitie = definitie;
    }

    public String getJuisteContextzin() {
        return juisteContextzin;
    }

    public void setJuisteContextzin(String juisteContextzin) {
        this.juisteContextzin = juisteContextzin;
    }

    public String getFouteContextzin() {
        return fouteContextzin;
    }

    public void setFouteContextzin(String fouteContextzin) {
        this.fouteContextzin = fouteContextzin;
    }

    public String getLettergrepen() {
        return lettergrepen;
    }

    public void setLettergrepen(String lettergrepen) {
        this.lettergrepen = lettergrepen;
    }

    public Woord() {
    }

    public Woord(long woordID, String woord, short onbepaaldLidwoord, long bepaaldLidwoordID, String definitie, String juisteContextzin, String fouteContextzin, String lettergrepen) {
        this.woordID = woordID;
        this.woord = woord;
        this.onbepaaldLidwoord = onbepaaldLidwoord;
        this.bepaaldLidwoordID = bepaaldLidwoordID;
        this.definitie = definitie;
        this.juisteContextzin = juisteContextzin;
        this.fouteContextzin = fouteContextzin;
        this.lettergrepen = lettergrepen;
    }

    @Override
    public String toString() {
        return " woord: " + woord + "\n" +
                " definitie: " + definitie + '\n' +
                " juisteContextzin: " + juisteContextzin + '\n' +
                " fouteContextzin: " + fouteContextzin + '\n' +
                " lettergrepen: " + lettergrepen;
    }
}

