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

        //Tabellen met relaties
        String CREATE_TABLE_STUDENT = "CREATE TABLE student (" +
                "studentID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "naam TEXT," +
                "FOREIGN KEY (groepID) REFERENCES groep(groepID))";
        db.execSQL(CREATE_TABLE_STUDENT);

        String CREATE_TABLE_ONDERZOEK = "CREATE TABLE onderzoek (" +
                "onderzoekID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "FOREIGN KEY (studentID) REFERENCES student(studentID)," +
                "FOREIGN KEY (lijstID) REFERENCES lijst(lijstID)," +
                "FOREIGN KEY (onderdeelID) REFERENCES onderdeel(onderdeelID)," +
                "score TEXT, " +
                "tijd TEXT)";
        db.execSQL(CREATE_TABLE_ONDERZOEK);

        String CREATE_TABLE_WOORDENLIJST = "CREATE TABLE woordenlijst (" +
                "woordenlijstID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "FOREIGN KEY (lijstID) REFERENCES lijst(lijstID)," +
                "FOREIGN KEY (woordID) REFERENCES woord(woordID))";
        db.execSQL(CREATE_TABLE_WOORDENLIJST);

        String CREATE_TABLE_FOUTENLIJST = "CREATE TABLE foutenlijst (" +
                "foutenlijstID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "FOREIGN KEY (onderzoekID) REFERENCES onderzoek(onderzoekID)," +
                "FOREIGN KEY (woordID) REFERENCES woord(woordID))";
        db.execSQL(CREATE_TABLE_FOUTENLIJST);

        insertGroepen(db);
    }

    //Database opvullen met testgegevens
    private void insertGroepen(SQLiteDatabase db) {
        db.execSQL("INSERT INTO groep (groepID, naam) VALUES (1, 'A');");
        db.execSQL("INSERT INTO groep (groepID, naam) VALUES (2, 'B');");
        db.execSQL("INSERT INTO groep (groepID, naam) VALUES (3, 'C');");
        db.execSQL("INSERT INTO groep (groepID, naam) VALUES (4, 'D');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS foutenlijst");
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

    public long insertStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("naam", student.getNaam());
        values.put("groepID",student.getGroepID());

        long id = db.insert("student", null, values);

        db.close();
        return id;
    }

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

    //'lijst' tijdelijk aangepast naar list
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
