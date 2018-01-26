package be.thomasmore.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Lars Gijbels.
 *
 */

public class AdminWoordenlijsten extends AppCompatActivity {

    private DatabaseHelper db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_woordenlijsten);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHelper(this);
        readWoordenlijsten();
    }

    private void readWoordenlijsten() {
        final List<Woordenlijst> woordenlijsten = db.getWoordenlijsten();

        ArrayAdapter<Woordenlijst> adapter = new ArrayAdapter<Woordenlijst>(this, android.R.layout.simple_list_item_1, woordenlijsten);

        final ListView lijst = (ListView) findViewById(R.id.lijstWoordenlijsten);
        lijst.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.studenten:
                Intent intent0 = new Intent(this, AdminStudenten.class);
                startActivity(intent0);
                return true;
            case R.id.groepen:
                Intent intent1 = new Intent(this, AdminGroepen.class);
                startActivity(intent1);
                return true;
            case R.id.onderzoeken:
                Intent intent2 = new Intent(this, AdminOnderzoeken.class);
                startActivity(intent2);
                return true;
            case R.id.onderdelen:
                Intent intent3 = new Intent(this, AdminOnderdelen.class);
                startActivity(intent3);
                return true;
            case R.id.lijsten:
                Intent intent4 = new Intent(this, AdminLijsten.class);
                startActivity(intent4);
                return true;
            case R.id.woordenlijsten:
                Intent intent5 = new Intent(this, AdminWoordenlijsten.class);
                startActivity(intent5);
                return true;
            case R.id.woorden:
                Intent intent6 = new Intent(this, AdminWoorden.class);
                startActivity(intent6);
                return true;
            case R.id.symantischWeb:
                Intent intent7 = new Intent(this, AdminSymantischeWebben.class);
                startActivity(intent7);
                return true;
            case R.id.keuzes:
                Intent intent8 = new Intent(this, AdminKeuzes.class);
                startActivity(intent8);
                return true;
            case R.id.scores:
                Intent intent9 = new Intent(this, AdminScores.class);
                startActivity(intent9);
                return true;
            case R.id.fouten:
                Intent intent10 = new Intent(this, AdminFouten.class);
                startActivity(intent10);
                return true;
            case R.id.nieuwOnderzoek:
                Intent intent11 = new Intent(this, Startpagina.class);
                startActivity(intent11);
                return true;
            case R.id.afsluiten:
                finish();
                return true;
            default:
                return false;
        }
    }
}
