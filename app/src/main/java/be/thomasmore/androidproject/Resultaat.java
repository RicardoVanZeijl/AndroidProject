package be.thomasmore.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Ricardo van Zeijl.
 *
 */

public class Resultaat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultaat);

        TextView tom = findViewById(R.id.tekstTom);
        tom.setText("Bedankt om mee te spelen. Tot de volgende keer!");
    }

    public void onClickStartNieuwOnderzoek(View view) {
        Intent intent = new Intent(this, Startpagina.class);
        startActivity(intent);
    }
}
