package be.thomasmore.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Conditie1Oefening1 extends AppCompatActivity {

    long studentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditie1_oefening1);


    }

    public void onClickVolgendeOefening(View view) {
        Bundle bundle = new Bundle();
        bundle.putLong("studentID", studentID);
        Intent intent = new Intent(this, Preteaching.class  );
        intent.putExtras(bundle);
    }
}
