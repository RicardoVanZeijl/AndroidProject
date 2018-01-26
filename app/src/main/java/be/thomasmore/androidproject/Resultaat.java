package be.thomasmore.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by Ricardo van Zeijl.
 *
 */

public class Resultaat extends AppCompatActivity {

    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultaat);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(new Locale("NL"));
                }
            }
        });

        TextView tom = findViewById(R.id.tekstTom);
        tom.setText("Wat was dat leuk. Dankjewel om mij zo goed te helpen. Wij hopen dat jij het ook zo leuk vond en de volgende keer weer samen met ons op avontuur gaat. Tot dan!");

        speakText(tom.getText().toString());
    }

    public void speakText(String toSpeak) {
        tts.speak(toSpeak, TextToSpeech.QUEUE_ADD, null);
    }

    public void onClickStartNieuwOnderzoek(View view) {
        Intent intent = new Intent(this, Startpagina.class);
        startActivity(intent);
    }
}
