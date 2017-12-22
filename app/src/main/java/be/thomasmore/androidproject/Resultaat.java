package be.thomasmore.androidproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Ricardo van Zeijl.
 *
 */

public class Resultaat extends AppCompatActivity {

    private DatabaseHelper db;

    long studentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultaat);

        Bundle bundle = getIntent().getExtras();
        studentID = bundle.getLong("studentID");


    }

}
