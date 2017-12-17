package be.thomasmore.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Ricardo van Zeijl on 28/11/2017.
 *
 */

public class Voormeting extends AppCompatActivity {

    private DatabaseHelper db;

    List<Woord> woorden;

    //Testgegevens, wordt meegegeven met bundle
    long studentID = 1;
    long groepID = 1;

    long onderdeelID = 1;

    int index;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voormeting);

        db = new DatabaseHelper(this);

//        Bundle bundle = getIntent().getExtras();
//        studentID = bundle.getLong("studentID");
//        groepID = bundle.getLong("groepID");

        Onderzoek onderzoek;
        onderzoek = db.getOnderzoek(groepID, onderdeelID);

        long lijstID = onderzoek.getLijstID();

        woorden = db.getWoordenFromLijst(lijstID);
        Collections.shuffle(woorden);

        index = 0;

        score = 0;

        volgendWoord();
    }

    private void volgendWoord(){
        TextView woord = findViewById(R.id.randomWoord);
        woord.setText(woorden.get(index).getWoord());

        ArrayList<String> afbeeldingen = new ArrayList<>();

        afbeeldingen.add(String.valueOf(woorden.get(index).getWoord().toLowerCase()));

        Random rnd = new Random();

        for (int i=0; i<3; i++) {

            int randomGetal = rnd.nextInt(20);

//            if (!Arrays.asList(afbeeldingen).contains(String.valueOf(woorden.get(randomGetal).getWoordID()))) {
//                afbeeldingen.add(String.valueOf(woorden.get(randomGetal).getWoordID()));
//            }

            if (!afbeeldingen.contains(String.valueOf(woorden.get(randomGetal).getWoord().toLowerCase()))) {
                afbeeldingen.add(String.valueOf(woorden.get(randomGetal).getWoord().toLowerCase()));
            }
        }

        Collections.shuffle(afbeeldingen);

        for (int i=0; i < afbeeldingen.size(); i++) {
            ImageView image = findViewById(getResources().getIdentifier(("randomAfbeelding" + i), "id", getPackageName()));
            image.setImageResource(getResources().getIdentifier(afbeeldingen.get(i), "drawable", getPackageName()));
            //Verkeerde tag wordt meegegeven
            //image.setTag(woorden.get(index).getWoordID());
            image.setTag(afbeeldingen.get(i).toLowerCase());
        }

        if (index<20) {
            index++;
        } else {
            Bundle bundle = new Bundle();
            bundle.putLong("studentID", studentID);
            bundle.putLong("groepID", groepID);
            Intent intent = new Intent(this, Preteaching.class  );
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    public void onClickRandomAfbeelding(View view) {
        ImageView image = (ImageView) view;

//        if (Integer.parseInt(image.getTag().toString()) == woorden.get(index).getWoordID()){
//            score+=1;
//        } else {
//            Fout fout = new Fout();
//            fout.setStudentID(studentID);
//            fout.setOnderdeelID(onderdeelID);
//            fout.setWoordID(woorden.get(index).getWoordID());
//
//            db.insertFout(fout);
//        }

        if (image.getTag().toString().equalsIgnoreCase(woorden.get(index).getWoord())){
            score+=1;
        } else {
            Fout fout = new Fout();
            fout.setStudentID(studentID);
            fout.setOnderdeelID(onderdeelID);
            fout.setWoordID(woorden.get(index).getWoordID());

            db.insertFout(fout);
        }

        volgendWoord();
    }
}
