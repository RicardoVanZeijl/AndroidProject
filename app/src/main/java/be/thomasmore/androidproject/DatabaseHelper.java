package be.thomasmore.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricardo van Zeijl.
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

        String CREATE_TABLE_ONDERDEEL = "CREATE TABLE onderdeel (" +
                "onderdeelID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "naam TEXT)";
        db.execSQL(CREATE_TABLE_ONDERDEEL);

        String CREATE_TABLE_BEPAALDLIDWOORD = "CREATE TABLE bepaaldlidwoord (" +
                "bepaaldLidwoordID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "bepaaldLidwoord TEXT)";
        db.execSQL(CREATE_TABLE_BEPAALDLIDWOORD);

        String CREATE_TABLE_KEUZE = "CREATE TABLE keuze (" +
                "keuzeID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "keuze TEXT)";
        db.execSQL(CREATE_TABLE_KEUZE);

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

        String CREATE_TABLE_WOORD = "CREATE TABLE woord (" +
                "woordID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "woord TEXT, " +
                "onbepaaldLidwoord BOOLEAN, " +
                "bepaaldLidwoordID INTEGER, " +
                "definitie TEXT, " +
                "juisteContextzin TEXT, " +
                "fouteContextzin TEXT, " +
                "lettergrepen TEXT, " +
                "FOREIGN KEY (bepaaldLidwoordID) REFERENCES bepaaldlidwoord(bepaaldLidwoordID))";
        db.execSQL(CREATE_TABLE_WOORD);

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

        String CREATE_TABLE_SYMANTISCHWEB = "CREATE TABLE symantischweb (" +
                "symantischWebID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "woordID INTEGER, " +
                "keuzeID INTEGER, " +
                "juist INTEGER, " +
                "FOREIGN KEY (woordID) REFERENCES woord(woordID)," +
                "FOREIGN KEY (keuzeID) REFERENCES keuze(keuzeID))";
        db.execSQL(CREATE_TABLE_SYMANTISCHWEB);

        insertGroepen(db);
        insertLijsten(db);
        insertOnderdelen(db);
        insertBepaaldeLidwoorden(db);
        insertKeuzes(db);
        insertStudenten(db);
        insertOnderzoeken(db);
        insertWoorden(db);
        insertWoordenlijsten(db);
        insertSymantischeWebben(db);
    }

    //Database opvullen met testgegevens

    private void insertGroepen(SQLiteDatabase db) {
        db.execSQL("INSERT INTO groep (groepID, naam) VALUES (1, 'A');");
        db.execSQL("INSERT INTO groep (groepID, naam) VALUES (2, 'B');");
    }

    private void insertLijsten(SQLiteDatabase db) {
        db.execSQL("INSERT INTO lijst (lijstID, naam) VALUES (1, 'Testlijst');");
    }

    private void insertOnderdelen(SQLiteDatabase db) {
        db.execSQL("INSERT INTO onderdeel (onderdeelID, naam) VALUES (1, 'Voormeting');");
        db.execSQL("INSERT INTO onderdeel (onderdeelID, naam) VALUES (2, 'Training Conditie 1');");
        db.execSQL("INSERT INTO onderdeel (onderdeelID, naam) VALUES (3, 'Training Conditie 2');");
        db.execSQL("INSERT INTO onderdeel (onderdeelID, naam) VALUES (4, 'Nameting');");
    }

    private void insertBepaaldeLidwoorden(SQLiteDatabase db) {
        db.execSQL("INSERT INTO bepaaldlidwoord (bepaaldLidwoordID, bepaaldLidwoord) VALUES (1, 'de');");
        db.execSQL("INSERT INTO bepaaldlidwoord (bepaaldLidwoordID, bepaaldLidwoord) VALUES (2, 'het');");
        db.execSQL("INSERT INTO bepaaldlidwoord (bepaaldLidwoordID, bepaaldLidwoord) VALUES (3, '');");
    }

    private void insertKeuzes(SQLiteDatabase db) {
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (1, 'eraf vallen');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (2, 'sterk');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (3, 'hoog');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (4, 'de schaar');");

        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (5, 'groen');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (6, 'in de vijver');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (7, 'drijven');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (8, 'de bloem');");

        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (9, 'de waterkant');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (10, 'de vijver');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (11, 'schuilen');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (12, 'de zwembroek');");

        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (13, 'de pijn');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (14, 'voorover');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (15, 'struikelen');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (16, 'fijn');");

        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (17, 'het zuiden');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (18, 'de landskaart');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (19, 'de weg kwijt zijn');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (20, 'de boom');");

        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (21, 'kijken');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (22, 'zwemmen');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (23, 'in de zee');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (24, 'lezen');");

        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (25, 'de berg');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (26, 'beklimmen');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (27, 'naar boven');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (28, 'het strand');");

        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (29, 'sierlijk');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (30, 'hals');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (31, 'groen');");

        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (32, 'de tent');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (33, 'in de slaapzak');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (34, 'spelen');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (35, 'het huis');");

        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (36, 'het licht');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (37, 'schijnen');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (38, 'donker');");
        db.execSQL("INSERT INTO keuze (keuzeID, keuze) VALUES (39, 'het lawaai');");
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

    private void insertWoorden(SQLiteDatabase db) {
        db.execSQL("INSERT INTO woord (woordID, woord, onbepaaldLidwoord, bepaaldLidwoordID, definitie, juisteContextzin, fouteContextzin, lettergrepen) VALUES (1, 'klimtouw', 1, 2, 'Een klimtouw is een touw waarin je omhoog kunt klimmen.', 'In de gymzaal klim ik omhoog in het klimtouw. ', 'Ik wacht op de bus in het klimtouw.', 'klim-touw');");
        db.execSQL("INSERT INTO woord (woordID, woord, onbepaaldLidwoord, bepaaldLidwoordID, definitie, juisteContextzin, fouteContextzin, lettergrepen) VALUES (2, 'kroos', 0, 2, 'Kroos bestaat uit kleine, groene plantjes die op het water groeien. Je ziet het bijvoorbeeld in een sloot.', 'De eendjes in de sloot eten van het groene kroos.', 'Opa en het kroos fietsen  naar de bakker.', 'kroos');");
        db.execSQL("INSERT INTO woord (woordID, woord, onbepaaldLidwoord, bepaaldLidwoordID, definitie, juisteContextzin, fouteContextzin, lettergrepen) VALUES (3, 'riet', 0, 2, 'Riet lijkt op hoog gras. Het heeft lange stengels en groeit langs het water.', 'De eenden zitten bij het water tussen het riet.', 'Ik ga naar buiten met mijn jas en riet aan.', 'riet');");
        db.execSQL("INSERT INTO woord (woordID, woord, onbepaaldLidwoord, bepaaldLidwoordID, definitie, juisteContextzin, fouteContextzin, lettergrepen) VALUES (4, 'val', 1, 1, 'Als je een val maakt, val je op de grond.', 'Ik heb pijn door de val bij het skeeleren.', 'Het kindje gaf de flinke hond een val.', 'val');");
        db.execSQL("INSERT INTO woord (woordID, woord, onbepaaldLidwoord, bepaaldLidwoordID, definitie, juisteContextzin, fouteContextzin, lettergrepen) VALUES (5, 'kompas', 1, 2, 'Met een kompas weet je waar je naartoe moet. De naald van het kompas geeft het noorden aan.', 'Omdat papa niet weet waar we naartoe moeten lopen, kijkt hij op zijn kompas.', 'De dokter onderzocht mij met het kompas.', 'kompas');");
        db.execSQL("INSERT INTO woord (woordID, woord, onbepaaldLidwoord, bepaaldLidwoordID, definitie, juisteContextzin, fouteContextzin, lettergrepen) VALUES (6, 'duikbril', 1, 1, 'Een duikbril is een bril voor onder water. Daarmee kun je onder water je ogen open houden.', 'De jongen kan onder water de vissen goed bekijken, want hij heeft een duikbril op.', 'In de winkel betaal ik met mijn duikbril.', 'duik-bril');");
        db.execSQL("INSERT INTO woord (woordID, woord, onbepaaldLidwoord, bepaaldLidwoordID, definitie, juisteContextzin, fouteContextzin, lettergrepen) VALUES (7, 'steil', 0, 3, 'Een steile berg gaat heel schuin omhoog of omlaag.', 'Het is moeilijk om op dat steile pas van de berg naar boven te komen.', 'Mama leest voor het slapen een steil verhaaltje voor.', 'steil');");
        db.execSQL("INSERT INTO woord (woordID, woord, onbepaaldLidwoord, bepaaldLidwoordID, definitie, juisteContextzin, fouteContextzin, lettergrepen) VALUES (8, 'zwaan', 1, 1, 'Een zwaan is een grote vogel met een lange, kromme hals. Meestal zijn zwanen wit, maar soms zwart.', 'In de vijver in het park zwemt een witte zwaan.', 'Stop de zwaan in je boekentas.', 'zwaan');");
        db.execSQL("INSERT INTO woord (woordID, woord, onbepaaldLidwoord, bepaaldLidwoordID, definitie, juisteContextzin, fouteContextzin, lettergrepen) VALUES (9, 'kamp', 1, 2, 'Een kamp is een plaats om buiten te wonen en te slapen, bijvoorbeeld in tenten.', 'De kinderen zitten te eten tussen de tenten van het kamp.', 'Mama en papa kopen kamp in de winkel.', 'kamp');");
        db.execSQL("INSERT INTO woord (woordID, woord, onbepaaldLidwoord, bepaaldLidwoordID, definitie, juisteContextzin, fouteContextzin, lettergrepen) VALUES (10, 'zaklamp', 1, 1, 'Een zaklamp is een kleine lamp die je overal mee naartoe kunt nemen.', 'Om iets te kunnen zien in de donkere grot schijnt de jongen met de zaklamp naar binnen.', 'Papa belt met de zaklamp naar opa.', 'zak-lamp');");
    }

    private void insertWoordenlijsten(SQLiteDatabase db) {
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (1, 1, 1);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (2, 1, 2);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (3, 1, 3);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (4, 1, 4);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (5, 1, 5);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (6, 1, 6);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (7, 1, 7);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (8, 1, 8);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (9, 1, 9);");
        db.execSQL("INSERT INTO woordenlijst (woordenlijstID, lijstID, woordID) VALUES (10, 1, 10);");
    }

    private void insertScores(SQLiteDatabase db) {
        db.execSQL("INSERT INTO score (scoreID, studentID, onderdeelID, score, tijd) VALUES (1, 1, 1, '9/10', '59');");
        db.execSQL("INSERT INTO score (scoreID, studentID, onderdeelID, score, tijd) VALUES (1, 1, 1, '53/60', '360');");
        db.execSQL("INSERT INTO score (scoreID, studentID, onderdeelID, score, tijd) VALUES (1, 1, 1, '10/10', '45');");
    }

    private void insertSymantischeWebben(SQLiteDatabase db) {
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (1, 1, 1, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (2, 1, 2, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (3, 1, 3, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (4, 1, 4, 0);");

        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (5, 2, 5, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (6, 2, 6, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (7, 2, 7, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (8, 2, 8, 0);");

        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (9, 3, 9, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (10, 3, 10, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (11, 3, 11, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (12, 3, 12, 0);");

        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (13, 4, 13, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (14, 4, 14, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (15, 4, 15, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (16, 4, 16, 0);");

        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (17, 5, 17, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (18, 5, 18, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (19, 5, 19, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (20, 5, 20, 0);");

        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (21, 6, 21, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (22, 6, 22, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (23, 6, 23, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (24, 6, 24, 0);");

        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (25, 7, 25, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (26, 7, 26, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (27, 7, 27, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (28, 7, 28, 0);");

        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (29, 8, 22, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (30, 8, 29, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (31, 8, 30, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (32, 8, 31, 0);");

        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (33, 9, 32, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (34, 9, 33, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (35, 9, 34, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (36, 9, 35, 0);");

        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (37, 10, 36, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (38, 10, 37, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (39, 10, 38, 1);");
        db.execSQL("INSERT INTO symantischweb (symantischWebID, woordID, keuzeID, juist) VALUES (40, 10, 39, 0);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS symantischweb");
        db.execSQL("DROP TABLE IF EXISTS fout");
        db.execSQL("DROP TABLE IF EXISTS score");
        db.execSQL("DROP TABLE IF EXISTS woordenlijst");
        db.execSQL("DROP TABLE IF EXISTS woord");
        db.execSQL("DROP TABLE IF EXISTS onderzoek");
        db.execSQL("DROP TABLE IF EXISTS student");
        db.execSQL("DROP TABLE IF EXISTS keuze");
        db.execSQL("DROP TABLE IF EXISTS bepaaldlidwoord");
        db.execSQL("DROP TABLE IF EXISTS onderdeel");
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

    public long insertScore(Score score) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("studentID", score.getStudentID());
        values.put("onderdeelID", score.getOnderdeelID());
        values.put("score", score.getScore());
        values.put("tijd", score.getTijd());

        long id = db.insert("score", null, values);

        db.close();
        return id;
    }

    //Read
    public Student getStudent(long studentID) {
        String selectQuery = "SELECT * FROM student WHERE studentID = " + studentID + "";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        Student student = new Student();

        if (cursor.moveToFirst()) {
            student = new Student(cursor.getLong(0),
                    cursor.getString(1), cursor.getLong(2));
        }
        cursor.close();
        db.close();
        return student;
    }

    public List<Groep> getGroepen() {
        List<Groep> lijst = new ArrayList<>();

        String selectQuery = "SELECT * FROM groep ORDER BY naam";

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

    //'lijst' aangepast naar list omdat de tabel ook lijst heeft als naam
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
        String selectQuery = "SELECT * FROM onderzoek WHERE groepID = " + groepID + " AND onderdeelID = " + onderdeelID + " ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

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

        String selectQuery = "SELECT * FROM woord w INNER JOIN woordenlijst wl ON (w.woordID = wl.woordID) where wl.lijstID = " + lijstID + " ORDER BY w.woord";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Woord woord = new Woord(cursor.getLong(0), cursor.getString(1), cursor.getShort(2), cursor.getLong(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),  cursor.getString(7));
                lijst.add(woord);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }
}
