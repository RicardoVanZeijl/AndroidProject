package be.thomasmore.androidproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Ricardo van Zeijl on 28/11/2017.
 *
 */

public class Pretraining extends AppCompatActivity {

    long studentID;
    long groepID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pretraining);

        Bundle bundle = getIntent().getExtras();
        studentID = bundle.getLong("studentID");
        groepID = bundle.getLong("groepID");
    }

}
