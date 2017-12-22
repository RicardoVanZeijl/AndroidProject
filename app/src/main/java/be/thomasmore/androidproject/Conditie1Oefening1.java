package be.thomasmore.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ricardo van Zeijl.
 *
 */

public class Conditie1Oefening1 extends AppCompatActivity {

    TextToSpeech tts;

    private DatabaseHelper db;
    List<Woord> woorden;
    long[] woordIDs;

    Woord woord;

    long studentID;
    long onderdeelID = 2;

    Integer index;
    int punten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditie1_oefening1);

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

        if (index == null) {

            index = 0;

            db = new DatabaseHelper(this);

            Student student = db.getStudent(studentID);

            Onderzoek onderzoek;
            onderzoek = db.getOnderzoek(student.getGroepID(), onderdeelID);

            long lijstID = onderzoek.getLijstID();


            woorden = db.getWoordenFromLijst(lijstID);
            Collections.shuffle(woorden);

            woordIDs = new long[woorden.size()];

            for (int i = 0; i < woorden.size(); i++) {
                long wid = woorden.get(i).getWoordID();
                woordIDs[i]=(woorden.get(i).getWoordID());
            }
        }

        woord = db.getWoord(woordIDs[index]);

        ImageView afbeeldingFullscreen = findViewById(R.id.randomAfbeeldingFullscreen);
        afbeeldingFullscreen.setImageResource(getResources().getIdentifier((woord.getWoord() + 0), "drawable", getPackageName()));

        TextView tekst = findViewById(R.id.tekstTom);
        tekst.setText("Wat zien we hier? Druk op de foto en ik vertel je alles over ");

        BepaaldLidwoord bepaaldLidwoord = db.getBepaaldLidwoord(woord.getBepaaldLidwoordID());

        tekst.append(bepaaldLidwoord.getBepaaldLidwoord() +" ");

        tekst.append(woord.getWoord() + ".");

        speakText(tekst.getText().toString());
    }

    public void onClickVolgendeOefening(View view) {
        Bundle bundle = new Bundle();
        bundle.putLong("studentID", studentID);
        bundle.putInt("index", index);
        bundle.putLongArray("woordIDs", woordIDs);
        Intent intent = new Intent(this, Conditie1Oefening3.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onClickRandomAfbeeldingFullScreen(View view) {
        ImageView afbeeldingFullscreen = findViewById(R.id.randomAfbeeldingFullscreen);
        afbeeldingFullscreen.setVisibility(View.INVISIBLE);

        TableLayout tabel = findViewById(R.id.tabel);
        tabel.setVisibility(View.VISIBLE);

        ImageView afbeelding = findViewById(R.id.randomAfbeelding);
        afbeelding.setImageResource(getResources().getIdentifier((woord.getWoord() + 0), "drawable", getPackageName()));

        TextView tekst = findViewById(R.id.tekstTom);
        tekst.setText("Weet jij wat ");

        if (woord.getOnbepaaldLidwoord() != 0) {
            tekst.append("een ");
        }

        tekst.append(woord.getWoord() + " is? \n\n");

        tekst.append(woord.getDefinitie());

        speakText(tekst.getText().toString());
    }

    public void speakText(String toSpeak) {
        tts.speak(toSpeak, TextToSpeech.QUEUE_ADD, null);
    }

    @Override
    protected void onStart() {
        TextView tekst = findViewById(R.id.tekstTom);

        speakText(tekst.getText().toString());

        super.onStart();
    }
}
