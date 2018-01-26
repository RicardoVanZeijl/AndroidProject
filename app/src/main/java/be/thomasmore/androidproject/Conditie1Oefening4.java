package be.thomasmore.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

/**
 * Created by Ricardo van Zeijl.
 *
 */

public class Conditie1Oefening4 extends AppCompatActivity {

    TextToSpeech tts;

    private DatabaseHelper db;

    long[] woordIDs;
    List<Keuze> keuzes;

    Woord woord;

    long studentID = 1;

    Integer index;
    int punten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditie1_oefening4);

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
        tom.setText("Hier zie je allemaal woordjes staan. Sommige woordjes passen bij ");

        if (woord.getOnbepaaldLidwoord() != 0) {
            tom.append("een ");
        }

        tom.append(woord.getWoord() + ". Eentje niet. Sleep de woordjes die passen bij " + woord.getWoord() + " naar de vakjes.");

        TextView randomWoord = findViewById(R.id.randomWoord);
        randomWoord.setText(woord.getWoord());

        keuzes = db.getKeuzesFromWoord(woord.getWoordID());

        for (int i = 0; i < keuzes.size(); i++) {
            TextView text = findViewById(getResources().getIdentifier(("randomKeuze" + i), "id", getPackageName()));
            text.setText(keuzes.get(i).getKeuze());
        }

        speakText(tom.getText().toString());
    }

    public void speakText(String toSpeak) {
        tts.speak(toSpeak, TextToSpeech.QUEUE_ADD, null);
    }

    public void onClickVolgendeOefening(View view) {
        Bundle bundle = new Bundle();
        bundle.putLong("studentID", studentID);
        bundle.putInt("index", index);
        bundle.putLongArray("woordIDs", woordIDs);
        Intent intent = new Intent(this, Conditie1Oefening5.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
