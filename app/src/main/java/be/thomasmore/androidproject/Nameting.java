package be.thomasmore.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Created by Ricardo van Zeijl.
 *
 */

public class Nameting extends AppCompatActivity {

    TextToSpeech tts;

    private DatabaseHelper db;

    List<Woord> woorden;

    long studentID;
    long onderdeelID = 4;

    int index = 0;
    int punten = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nameting);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(new Locale("NL"));
                }
            }
        });

        final ImageButton ib = findViewById(R.id.textToSpeech);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView woord = findViewById(R.id.randomWoord);
                String toSpeak = woord.getText().toString();
                tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        db = new DatabaseHelper(this);

        Bundle bundle = getIntent().getExtras();
        studentID = bundle.getLong("studentID");

        Student student = db.getStudent(studentID);

        Onderzoek onderzoek;
        onderzoek = db.getOnderzoek(student.getGroepID(), onderdeelID);

        long lijstID = onderzoek.getLijstID();

        woorden = db.getWoordenFromLijst(lijstID);
        Collections.shuffle(woorden);

        volgendWoord();
    }

    private void volgendWoord(){
        TextView woord = findViewById(R.id.randomWoord);

        BepaaldLidwoord bepaaldLidwoord = db.getBepaaldLidwoord( woorden.get(index).getBepaaldLidwoordID());

        woord.setText(bepaaldLidwoord.getBepaaldLidwoord() +" " + woorden.get(index).getWoord());

        ArrayList<String> afbeeldingen = new ArrayList<>();

        afbeeldingen.add(String.valueOf(woorden.get(index).getWoord().toLowerCase()));

        Random rnd = new Random();

        for (int i=0; i<3; i++) {

            int randomGetal = rnd.nextInt(woorden.size());

            if (!afbeeldingen.contains(String.valueOf(woorden.get(randomGetal).getWoord().toLowerCase()))) {
                afbeeldingen.add(String.valueOf(woorden.get(randomGetal).getWoord().toLowerCase()));
            } else {
                i-=1;
            }
        }

        Collections.shuffle(afbeeldingen);

        for (int i=0; i < afbeeldingen.size(); i++) {
            ImageView image = findViewById(getResources().getIdentifier(("randomAfbeelding" + i), "id", getPackageName()));
            image.setImageResource(getResources().getIdentifier((afbeeldingen.get(i)+0), "drawable", getPackageName()));
            image.setTag(afbeeldingen.get(i).toLowerCase());
        }

        String toSpeak = woord.getText().toString();
        tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void onClickRandomAfbeelding(View view) {
        ImageView image = (ImageView) view;

        if (image.getTag().toString().equalsIgnoreCase(woorden.get(index).getWoord())){
            punten+=1;
        } else {
            Fout fout = new Fout();
            fout.setStudentID(studentID);
            fout.setOnderdeelID(onderdeelID);
            fout.setWoordID(woorden.get(index).getWoordID());

            db.insertFout(fout);
        }

        if (index<woorden.size()-1) {
            index++;
            volgendWoord();
        } else {
            Score score = new Score();
            score.setStudentID(studentID);
            score.setOnderdeelID(onderdeelID);
            score.setScore(punten + "/" + woorden.size());
            score.setTijd("0");

            db.insertScore(score);

            Bundle bundle = new Bundle();
            bundle.putLong("studentID", studentID);
            Intent intent = new Intent(this, Resultaat.class  );
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

}
