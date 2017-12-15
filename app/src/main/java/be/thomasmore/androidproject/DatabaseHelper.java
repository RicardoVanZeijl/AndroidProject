package be.thomasmore.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricardo van Zeijl on 4/12/2017.
 *
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "androidproject";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tabellen zonder foreign keys
        String CREATE_TABLE_GROEP = "CREATE TABLE groep (" +
                "groepID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "naam TEXT)";
        db.execSQL(CREATE_TABLE_GROEP);

        String CREATE_TABLE_LIJST = "CREATE TABLE lijst (" +
                "lijstID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "naam TEXT)";
        db.execSQL(CREATE_TABLE_LIJST);

        String CREATE_TABLE_WOORD = "CREATE TABLE woord (" +
                "woordID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "woord TEXT)";
        db.execSQL(CREATE_TABLE_WOORD);

        String CREATE_TABLE_ONDERDEEL = "CREATE TABLE onderdeel (" +
                "onderdeelID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "naam TEXT)";
        db.execSQL(CREATE_TABLE_ONDERDEEL);

        //Tabellen met foreign keys
        String CREATE_TABLE_STUDENT = "CREATE TABLE student (" +
                "studentID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "naam TEXT," +
                "groepID INTEGER, " +
                "FOREIGN KEY (groepID) REFERENCES groep(groepID))";
        db.execSQL(CREATE_TABLE_STUDENT);

        String CREATE_TABLE_ONDERZOEK = "CREATE TABLE onderzoek (" +
                "onderzoekID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "groepID INTEGER, " +
                "lijstID INTEGER, " +
                "onderdeelID INTEGER, " +
                "FOREIGN KEY (groepID) REFERENCES groep(groepID)," +
                "FOREIGN KEY (lijstID) REFERENCES lijst(lijstID)," +
                "FOREIGN KEY (onderdeelID) REFERENCES onderdeel(onderdeelID))";
        db.execSQL(CREATE_TABLE_ONDERZOEK);

        String CREATE_TABLE_WOORDENLIJST = "CREATE TABLE woordenlijst (" +
                "woordenlijstID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "lijstID INTEGER, " +
                "woordID INTEGER, " +
                "FOREIGN KEY (lijstID) REFERENCES lijst(lijstID)," +
                "FOREIGN KEY (woordID) REFERENCES woord(woordID))";
        db.execSQL(CREATE_TABLE_WOORDENLIJST);

        String CREATE_TABLE_SCORE = "CREATE TABLE score (" +
                "scoreID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "studentID INTEGER, " +
                "onderdeelID INTEGER, " +
                "score TEXT, " +
                "tijd TEXT, " +
                "FOREIGN KEY (studentID) REFERENCES student(studentID)," +
                "FOREIGN KEY (onderdeelID) REFERENCES onderdeel(onderdeelID))";
        db.execSQL(CREATE_TABLE_SCORE);

        String CREATE_TABLE_FOUT = "CREATE TABLE fout (" +
                "foutID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "studentID INTEGER, " +
                "onderdeelID INTEGER, " +
                "woordID INTEGER, " +
                "FOREIGN KEY (studentID) REFERENCES student(studentID)," +
                "FOREIGN KEY (onderdeelID) REFERENCES onderdeel(onderdeelID)," +
                "FOREIGN KEY (woordID) REFERENCES woord(woordID))";
        db.execSQL(CREATE_TABLE_FOUT);

        insertGroepen(db);
        insertLijsten(db);
        insertWoorden(db);
        insertOnderdelen(db);
        insertStudenten(db);
        insertOnderzoeken(db);
        insertWoordenlijsten(db);
    }

    //Database opvullen met testgegevens

    private void insertGroepen(SQLiteDatabase db) {
        db.execSQL("INSERT INTO groep (groepID, naam) VALUES (1, 'A');");
        db.execSQL("INSERT INTO groep (groepID, naam) VALUES (2, 'B');");
    }

    private void insertLijsten(SQLiteDatabase db) {
        db.execSQL("INSERT INTO lijst (lijstID, naam) VALUES (1, 'Testlijst');");
    }

    private void insertWoorden(SQLiteDatabase db) {
        db.execSQL("INSERT INTO woord (woordID, woord) VALUES (1, 'A');");
        db.execSQL("INSERT INTO woord (woordID, woord) VALUES (2, 'B');");
        db.execSQL("INSERT INTO woord (woordID, woord) VALUES (3, 'C');");
        db.execSQL("INSERT INTO woord (woordID, woord) VALUES (4, 'D');");
        db.execSQL("INSERT INTO woord (woordID, woord) VALUES (5, 'E');");
        db.execSQL("INSERT INTO woord (woordID, woord) VALUES (6, 'F');");
        db.execSQL("INSERT INTO woord (woordID, woord) VALUES (7, 'G');");
        db.execSQL("INSERT INTO woord (woordID, woord) VALUES (8, 'H');");
        db.execSQL("INSERT INTO woord (woordID, woord) VALUES (9, 'I');");
        db.execSQL("INSERT INTO woord (woordID, woord) VALUES (10, 'J');");
        db.execSQL("INSERT INTO woord (woordID, woord) VALUES (11, 'K');");
        db.execSQL("INSERT INTO woord (woordID, woord) VALUES (12, 'L');");
        db.execSQL("INSERT INTO woord (woordID, woord) VALUES (13, 'M');");
        db.execSQL("INSERT INTO woord (woordID, woord) VALUES (14, 'N');");
        db.execSQL("INSERT INTO woord (woordID, woord) VALUES (15, 'O');");
        db.execSQL("INSERT INTO woord (woordID, woord) VALUES (16, 'P');");
        db.execSQL("INSERT INTO woord (woordID, woord) VALUES (17, 'Q');");
        db.execSQL("INSERT INTO woord (woordID, woord) VALUES (18, 'R');");
        db.execSQL("INSERT INTO woord (woordID, woord) VALUES (19, 'S');");
        db.execSQL("INSERT INTO woord (woordID, woord) VALUES (20, 'T');");
    }

    private void insertOnderdelen(SQLiteDatabase db) {
        db.execSQL("INSERT INTO onderdeel (onderdeelID, naam) VALUES (1, 'Voormeting');");
        db.execSQL("INSERT INTO onderdeel (onderdeelID, naam) VALUES (2, 'Training Conditie 1');");
        db.execSQL("INSERT INTO onderdeel (onderdeelID, naam) VALUES (3, 'Training Conditie 2');");
        db.execSQL("INSERT INTO onderdeel (onderdeelID, naam) VALUES (4, 'Nameting');");
    }

    private void insertStudenten(SQLiteDatabase db) {
        db.execSQL("INSERT INTO student (studentID, naam, groepID) VALUES (1, 'Lars Gijbels', 1);");
        db.execSQL("INSERT INTO student (studentID, naam, groepID) VALUES (2, 'Ricardo van Zeijl', 2);");
    }

    private void insertOnderzoeken(SQLiteDatabase db) {
        db.execSQL("INSERT INTO onderzoek (onderzoekID, groepID, lijstID, onderdeelID) VALUES (1, 1, 1, 1);");
        db.execSQL("INSERT INTO onderzoek (onderzoekID, groepID, lijstID, onderdeelID) VALUES (2, 2, 1, 1);");
        db.execSQL("INSERT INTO onderzoek (onderzoekID, groepID, lijstID, onderdeelID) VALUES (3, 1, 1, 2);");
        db.execSQL("INSERT INTO onderzoek (onderzoekID, groepID, lijstID, onderdeelID) VALUES (4, 2, 1, 3);");
        db.execSQL("INSERT INTO onderzoek (onderzoekID, groepID, lijstID, onderdeelID) VALUES (5, 1, 1, 4);");
        db.execSQL("INSERT INTO onderzoek (onderzoekID, groepID, lijstID, onderdeelID) VALUES (6, 2, 1, 4);");
    }

    private void insertWoordenlijsten(SQLiteDatabase db) {
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (1, 1, 1);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (2, 1, 2);");
        db.execSQL("INSERT INTO woordenlijst(woordenlijstID, lijstID, woordID) VALUES (3, 1, 3);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (4, 1, 4);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (5, 1, 5);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (6, 1, 6);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (7, 1, 7);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (8, 1, 8);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (9, 1, 9);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (10, 1, 10);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (11, 1, 11);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (12, 1, 12);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (13, 1, 13);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (14, 1, 14);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (15, 1, 15);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (16, 1, 16);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (17, 1, 17);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (18, 1, 18);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (19, 1, 19);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (20, 1, 20);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS fout");
        db.execSQL("DROP TABLE IF EXISTS score");
        db.execSQL("DROP TABLE IF EXISTS woordenlijst");
        db.execSQL("DROP TABLE IF EXISTS onderzoek");
        db.execSQL("DROP TABLE IF EXISTS student");
        db.execSQL("DROP TABLE IF EXISTS onderdeel");
        db.execSQL("DROP TABLE IF EXISTS woord");
        db.execSQL("DROP TABLE IF EXISTS lijst");
        db.execSQL("DROP TABLE IF EXISTS groep");

        // Tabellen opnieuw aanmaken
        onCreate(db);
    }

    //-------------------------------------------------------------------------------------------------
    //  CRUD Operations
    //-------------------------------------------------------------------------------------------------

    //Create
    public long insertStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("naam", student.getNaam());
        values.put("groepID",student.getGroepID());

        long id = db.insert("student", null, values);

        db.close();
        return id;
    }

    public long insertFout(Fout fout) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("studentID", fout.getStudentID());
        values.put("onderdeelID", fout.getOnderdeelID());
        values.put("woordID", fout.getWoordID());

        long id = db.insert("fout", null, values);

        db.close();
        return id;
    }

    //Read
    public List<Groep> getGroepen() {
        List<Groep> lijst = new ArrayList<>();

        String selectQuery = "SELECT  * FROM groep ORDER BY naam";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Groep groep = new Groep(cursor.getLong(0), cursor.getString(1));
                lijst.add(groep);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }

    //'lijst' tijdelijk aangepast naar list omdat tabel ook lijst noemt
    public List<Lijst> getLijsten() {
        List<Lijst> list = new ArrayList<>();

        String selectQuery = "SELECT  * FROM lijst ORDER BY naam";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Lijst lijst = new Lijst(cursor.getLong(0), cursor.getString(1));
                list.add(lijst);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }

    public Onderzoek getOnderzoek(long groepID, long onderdeelID) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                "onderzoek",
                new String[] { "onderzoekID", "groepID", "lijstID", "onderdeelID" },
                "groepID = ? AND onderdeelID = ?",
                new String[] { String.valueOf(groepID),  String.valueOf(onderdeelID) },
                null,
                null,
                null,
                null);

        Onderzoek onderzoek = new Onderzoek();

        if (cursor.moveToFirst()) {
            onderzoek = new Onderzoek(cursor.getLong(0),
                    cursor.getLong(1), cursor.getLong(2), cursor.getLong(3));
        }
        cursor.close();
        db.close();
        return onderzoek;
    }

    public List<Woord> getWoordenFromLijst(long lijstID) {
        List<Woord> lijst = new ArrayList<>();

        String selectQuery = "SELECT  * FROM woord w INNER JOIN woordenlijst wl ON (w.woordID = wl.woordID) where wl.lijstID = " + lijstID + " ORDER BY w.woord";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Woord woord = new Woord(cursor.getLong(0), cursor.getString(1));
                lijst.add(woord);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }
}
