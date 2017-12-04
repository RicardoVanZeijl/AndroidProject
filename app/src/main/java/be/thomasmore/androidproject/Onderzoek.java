package be.thomasmore.androidproject;

/**
 * Created by ricar on 4/12/2017.
 */

public class Onderzoek {
    private long onderzoekID;
    private long studentID;
    private long lijstID;
    private long onderdeelID;
    private String score;
    private String tijd;

    public long getOnderzoekID() {
        return onderzoekID;
    }

    public void setOnderzoekID(long onderzoekID) {
        this.onderzoekID = onderzoekID;
    }

    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long studentID) {
        this.studentID = studentID;
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTijd() {
        return tijd;
    }

    public void setTijd(String tijd) {
        this.tijd = tijd;
    }

    public Onderzoek() {
    }

    public Onderzoek(long onderzoekID, long studentID, long lijstID, long onderdeelID, String score, String tijd) {
        this.onderzoekID = onderzoekID;
        this.studentID = studentID;
        this.lijstID = lijstID;
        this.onderdeelID = onderdeelID;
        this.score = score;
        this.tijd = tijd;
    }

    @Override
    public String toString() {
        return "Onderzoek{" +
                "onderzoekID=" + onderzoekID +
                ", studentID=" + studentID +
                ", lijstID=" + lijstID +
                ", onderdeelID=" + onderdeelID +
                ", score='" + score + '\'' +
                ", tijd='" + tijd + '\'' +
                '}';
    }
}
