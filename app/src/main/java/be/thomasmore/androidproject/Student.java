package be.thomasmore.androidproject;

/**
 * Created by ricar on 4/12/2017.
 */

public class Student {
    private long studentID;
    private String naam;
    private long groepID;

    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public long getGroepID() {
        return groepID;
    }

    public void setGroepID(long groepID) {
        this.groepID = groepID;
    }

    public Student() {
    }

    public Student(long studentID, String naam, long groepID) {
        this.studentID = studentID;
        this.naam = naam;
        this.groepID = groepID;
    }

    @Override
    public String toString() {
        return naam;
    }
}
