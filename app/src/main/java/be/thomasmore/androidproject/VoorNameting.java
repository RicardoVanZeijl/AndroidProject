package be.thomasmore.androidproject;

/**
 * Created by ricar on 4/12/2017.
 */

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class VoorNameting extends AppCompatActivity {

    //TTS object
    private TextToSpeech myTTS;
    //status check code
    private int MY_DATA_CHECK_CODE = 0;

    //custom variables
    int score;

    //create the Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voor_nameting);

        //get a reference to the button element listed in the XML layout
        ImageButton speakButton = (ImageButton) findViewById(R.id.imageButtonTTS);
        //listen for clicks
        //speakButton.setOnClickListener((View.OnClickListener) this);

        //check for TTS data
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        //startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);

        //check on savedInstance
        if (savedInstanceState != null) {
            score = savedInstanceState.getInt("score");
        }

        //initial TTS
        TextView textViewWord = (TextView)findViewById(R.id.word);
        String word = textViewWord.getText().toString();
        //speakWords(word);


    }

    //respond to button clicks
    public void imageButtonTTSOnClick(View v) {

        //get the text
        TextView textViewWord = (TextView)findViewById(R.id.word);
        String word = textViewWord.getText().toString();
        speakWords(word);
    }

    //speak the user text
    private void speakWords(String speech) {

        //speak straight away
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            myTTS.speak(speech,TextToSpeech.QUEUE_FLUSH,null,null);
        } else {
            myTTS.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    //act on result of TTS data check
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                //the user has the necessary data - create the TTS
                myTTS = new TextToSpeech(this, (TextToSpeech.OnInitListener) this);
            }
            else {
                //no data - install it now
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }

    //setup TTS
    public void onInit(int initStatus) {

        //check for successful instantiation
        if (initStatus == TextToSpeech.SUCCESS) {
            if(myTTS.isLanguageAvailable(Locale.US)==TextToSpeech.LANG_AVAILABLE)
                myTTS.setLanguage(Locale.US);
        }
        else if (initStatus == TextToSpeech.ERROR) {
            Toast.makeText(this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
        }
    }

    public void onSaveInstanceState(Bundle outState){
        outState.putInt("score",score);
        super.onSaveInstanceState(outState);
    }
}
