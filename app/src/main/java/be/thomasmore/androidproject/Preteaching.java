package be.thomasmore.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by Ricardo van Zeijl.
 *
 */

public class Preteaching extends AppCompatActivity {

    private static final int MY_DATA_CHECK_CODE = 0;
    private TextToSpeech tts;

    long studentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preteaching);

        Bundle bundle = getIntent().getExtras();
//        studentID = bundle.getLong("studentID");

        TextView tekst = findViewById(R.id.preteachingtekst);
        tekst.setText("Tom en zijn vriendjes zijn aan het spelen. Dat ziet er leuk uit. Kijk maar eens goed wat ze allemaal aan het doen zijn. Zie jij Tom staan? Druk er maar op.");

        TextView tekstTom = findViewById(R.id.preteachingtekstTom);
        tekstTom.setText("Tom zegt: Hallo, ik ben Tom. Ik ga samen met mijn vriendjes op avontuur. Onderweg gaan we allemaal nieuwe woordjes leren. Leuk hé? Wij hebben wel een probleem. Victor ligt ziek in bed. Wil jij in zijn plaats met ons meegaan? Druk maar op de grote knop, dan kunnen we er aan beginnen.");

        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(new Locale("NL"));
                }
            }
        });

        speakTekst();
    }

    public void onClickStartAvontuur(View view) {
        Bundle bundle = new Bundle();
        bundle.putLong("studentID", studentID);
        Intent intent = new Intent(this,Conditie1Oefening1.class      );
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void speakTekst(){

        if(tts != null)
        {
            TextView tekst = findViewById(R.id.preteachingtekst);
            String toSpeak = tekst.getText().toString();
            Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
            tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                // the user has the necessary data - create the TTS
                TextToSpeech tts;
            } else {
                // no data - install it now
                Intent installTTSIntent = new Intent();
                installTTSIntent
                        .setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }
}
