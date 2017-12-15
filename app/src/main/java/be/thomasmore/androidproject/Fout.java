package be.thomasmore.androidproject;

/**
 * Created by Ricardo van Zeijl on 4/12/2017.
 *
 */

public class Fout {
    private long foutID;
    private long studentID;
    private long onderdeelID;
    private long woordID;

    public long getFoutID() {
        return foutID;
    }

    public void setFoutID(long foutID) {
        this.foutID = foutID;
    }

    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }

    public long getOnderdeelID() {
        return onderdeelID;
    }

    public void setOnderdeelID(long onderdeelID) {
        this.onderdeelID = onderdeelID;
    }

    public long getWoordID() {
        return woordID;
    }

    public void setWoordID(long woordID) {
        this.woordID = woordID;
    }

    public Fout() {
    }

    public Fout(long foutID, long studentID, long onderdeelID, long woordID) {
        this.foutID = foutID;
        this.studentID = studentID;
        this.onderdeelID = onderdeelID;
        this.woordID = woordID;
    }

    @Override
    public String toString() {
        return "Fout{" +
                "foutID=" + foutID +
                ", studentID=" + studentID +
                ", onderdeelID=" + onderdeelID +
                ", woordID=" + woordID +
                '}';
    }
}

