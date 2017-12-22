package be.thomasmore.androidproject;

/**
 * Created by Ricardo van Zeijl.
 *
 */

public class Score {
    private long scoreID;
    private long studentID;
    private long onderdeelID;
    private String score;
    private String tijd;


    public long getScoreID() {
        return scoreID;
    }

    public void setScoreID(long scoreID) {
        this.scoreID = scoreID;
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

    public Score() {
    }

    Student student = new Student();

    public Score(long scoreID, long studentID, long onderdeelID, String score, String tijd) {
        this.scoreID = scoreID;
        this.studentID = studentID;
        this.onderdeelID = onderdeelID;
        this.score = score;
        this.tijd = tijd;
    }

    @Override
    public String toString() {
        return "Score{" +
                "scoreID=" + scoreID +
                ", studentID=" + student +
                ", onderdeelID=" + onderdeelID +
                ", score='" + score + '\'' +
                ", tijd='" + tijd + '\'' +
                '}';
    }
}
