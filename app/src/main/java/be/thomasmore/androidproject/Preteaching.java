package be.thomasmore.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by Ricardo van Zeijl.
 *
 */

public class Preteaching extends AppCompatActivity {

    private TextToSpeech tts;

    long studentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preteaching);

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

        ImageView afbeeldingTom = findViewById(R.id.afbeeldingTom);
        afbeeldingTom.setImageResource(getResources().getIdentifier("afbeeldingonbekendevrouw", "drawable", getPackageName()));

        TextView tekstTom = findViewById(R.id.tekstTom);
        tekstTom.setText("Tom en zijn vriendjes zijn aan het spelen. Dat ziet er leuk uit. Kijk maar eens goed wat ze allemaal aan het doen zijn. Zie jij Tom staan? Druk er maar op.");

        speakText(tekstTom.getText().toString());
    }

    public void onClickPreteachingplaat(View view) {
        ImageView afbeeldingTom = findViewById(R.id.afbeeldingTom);
        afbeeldingTom.setImageResource(getResources().getIdentifier("afbeeldingtom", "drawable", getPackageName()));

        TextView tekstTom = findViewById(R.id.tekstTom);
        tekstTom.setText("Hallo, ik ben Tom. Ik ga samen met mijn vriendjes op avontuur. Onderweg gaan we allemaal nieuwe woordjes leren. Leuk hé? Wij hebben wel een probleem. Victor ligt ziek in bed. Wil jij in zijn plaats met ons meegaan? Druk maar op de grote knop, dan kunnen we er aan beginnen.");

        speakText(tekstTom.getText().toString());

        ImageView preteachingplaat = findViewById(R.id.preteachingplaat);
        preteachingplaat.setVisibility(View.INVISIBLE);

        Button startAvontuur = findViewById(R.id.knopStartAvontuur);
        startAvontuur.setVisibility(View.VISIBLE);
    }

    public void onClickStartAvontuur(View view) {
        Bundle bundle = new Bundle();
        bundle.putLong("studentID", studentID);
        Intent intent = new Intent(this,Conditie1Oefening1.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void speakText(String toSpeak) {
        tts.speak(toSpeak, TextToSpeech.QUEUE_ADD, null);
    }
}
