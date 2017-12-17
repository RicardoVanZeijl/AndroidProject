package be.thomasmore.androidproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Ricardo van Zeijl on 16/12/2017.
 *
 */

public class Preteaching extends AppCompatActivity {

    long studentID;
    long groepID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preteaching);

        Bundle bundle = getIntent().getExtras();
        studentID = bundle.getLong("studentID");
        groepID = bundle.getLong("groepID");

        TextView tekst = findViewById(R.id.preteachingtekst);
        tekst.setText("Tom en zijn vriendjes zijn aan het spelen. Dat ziet er leuk uit. Kijk maar eens goed wat ze allemaal aan het doen zijn. Zie jij Tom staan? Druk er maar op.");

        TextView tekstTom = findViewById(R.id.preteachingtekstTom);
        tekstTom.setText("Tom zegt: Hallo, ik ben Tom. Ik ga samen met mijn vriendjes op avontuur. Onderweg gaan we allemaal nieuwe woordjes leren. Leuk hé? Wij hebben wel een probleem. Victor ligt ziek in bed. Wil jij in zijn plaats met ons meegaan? Druk maar op de grote knop, dan kunnen we er aan beginnen.");


    }

}
