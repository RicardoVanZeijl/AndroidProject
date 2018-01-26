package be.thomasmore.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

/**
 * Created by Ricardo van Zeijl.
 *
 */

public class Conditie1Oefening5 extends AppCompatActivity {

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
        setContentView(R.layout.activity_conditie1_oefening5);

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

        if (woord.getOnbepaaldLidwoord() != 0) {
            tom.setText("Een ");
        }

        tom.append(woord.getWoord() + ", hoe ziet dat er weer uit? Ik heb hier 4 plaatjes. Weet jij op welke plaatjes hier ");

        if (woord.getOnbepaaldLidwoord() != 0) {
            tom.append("een ");
        }

        tom.append(woord.getWoord() + " staat? Sleep deze plaatjes maar naar vakjes.");

        ImageView randomAfbeelding = findViewById(R.id.randomAfbeelding);
        randomAfbeelding.setImageResource(getResources().getIdentifier((woord.getWoord() + 0), "drawable", getPackageName()));

        for (int i = 0; i < 4; i++) {
            ImageView image = findViewById(getResources().getIdentifier(("randomKeuzeAfbeelding" + i), "id", getPackageName()));
            image.setImageResource(getResources().getIdentifier((woord.getWoord()+(+i+1)), "drawable", getPackageName()));
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
        Intent intent = new Intent(this, Conditie1Oefening6.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
