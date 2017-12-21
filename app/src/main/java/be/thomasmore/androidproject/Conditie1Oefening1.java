package be.thomasmore.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ricardo van Zeijl.
 *
 */

public class Conditie1Oefening1 extends AppCompatActivity {

    TextToSpeech tts;

    private DatabaseHelper db;

    List<Woord> woorden;

    long studentID;
    long onderdeelID = 1;

    int index = 0;
    int punten = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditie1_oefening1);

        db = new DatabaseHelper(this);

        Bundle bundle = getIntent().getExtras();
        studentID = bundle.getLong("studentID");

        Student student = db.getStudent(studentID);

        Onderzoek onderzoek;
        onderzoek = db.getOnderzoek(student.getGroepID(), onderdeelID);

        long lijstID = onderzoek.getLijstID();

        woorden = db.getWoordenFromLijst(lijstID);
        Collections.shuffle(woorden);
    }

    public void onClickVolgendeOefening(View view) {
        Bundle bundle = new Bundle();
        bundle.putLong("studentID", studentID);
        Intent intent = new Intent(this, Conditie1Oefening3.class  );
        intent.putExtras(bundle);
    }
}
