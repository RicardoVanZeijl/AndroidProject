package be.thomasmore.androidproject;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

/**
 * Created by Ricardo van Zeijl.
 *
 */

public class Conditie1Oefening6 extends AppCompatActivity {

    TextToSpeech tts;

    private DatabaseHelper db;

    long[] woordIDs;
    List<String> contextzinnen;

    Woord woord;

    long studentID = 1;

    Integer index;
    int punten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditie1_oefening6);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(new Locale("NL"));
                }
            }
        });

        Bundle bundle = getIntent().getExtras();
        studentID = bundle.getLong("studentID");
        woordIDs = bundle.getLongArray("woordIDs");
        index = bundle.getInt("index");

        db = new DatabaseHelper(this);

        woord = db.getWoord(woordIDs[index]);

        TextView tom = findViewById(R.id.tekstTom);
        tom.setText("Dankjewel om mij te helpen. Jij hebt dat super gedaan. Door jou weet ik nu wat meer over ");

        if (woord.getOnbepaaldLidwoord() != 0) {
            tom.append("een ");
        }

        tom.append(woord.getWoord() + ". Wat een leuk woord is " + woord.getWoord() + " toch? Wat vind jij van het woord  '" + woord.getWoord() +"'?");
    }

}
