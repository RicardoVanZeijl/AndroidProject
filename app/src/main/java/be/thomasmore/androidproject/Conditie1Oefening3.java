package be.thomasmore.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ricardo van Zeijl.
 *
 */

public class Conditie1Oefening3 extends AppCompatActivity {

    TextToSpeech tts;

    private DatabaseHelper db;

    long[] woordIDs;
    List<String> contextzinnen;

    Woord woord;

    long studentID;

    Integer index;
    int punten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditie1_oefening3);

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

        TextView tekst = findViewById(R.id.tekstTom);
        tekst.setText("Ik zou graag het woord " + woord.getWoord() + " in een zinnetje gebruiken. Zoek je mee naar een goede zin? Ik zeg een zin. Vind je dit een goede zin? Druk dan op de groene duim. Vind je dit geen goede zin? Druk dan op de rode duim");

        contextzinnen = new ArrayList<>();

        contextzinnen.add(woord.getJuisteContextzin());
        contextzinnen.add(woord.getFouteContextzin());

        Collections.shuffle(contextzinnen);

        TextView contextzin = findViewById(R.id.tekstContextzin);
        contextzin.setText(contextzinnen.get(0));
    }

    public void onClickJuistFout(View view) {
        TextView contextzin = findViewById(R.id.tekstContextzin);

        if (contextzin.getText().equals(woord.getJuisteContextzin())){
            punten+=1;

            speakText("Super goed gedaan");
        } else {
            speakText("Luister nog eens");
        }

        if (contextzin.getText().equals(contextzinnen.get(1))) {
            Bundle bundle = new Bundle();
            bundle.putLong("studentID", studentID);
            bundle.putLong("index", index);
            bundle.putLongArray("woordIDs", woordIDs);
            Intent intent = new Intent(this, Conditie1Oefening4.class);
            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            contextzin.setText(contextzinnen.get(1));
        }
    }

    public void speakText(String toSpeak) {
        tts.speak(toSpeak, TextToSpeech.QUEUE_ADD, null);
    }
}
