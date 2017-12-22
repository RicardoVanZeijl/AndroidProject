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

public class AdminWoorden extends AppCompatActivity {

    private DatabaseHelper db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_woorden);

        Toolbar toolbarAdmin = findViewById(R.id.toolbar);
        setSupportActionBar(toolbarAdmin);

        db = new DatabaseHelper(this);
        readWoorden();
    }

    // woorden inlezen in listview
    private void readWoorden() {
        final List<Woord> woorden = db.getWoorden();

        ArrayAdapter<Woord> adapter = new ArrayAdapter<Woord>(this, android.R.layout.simple_list_item_1, woorden);

        final ListView lijst = (ListView) findViewById(R.id.lijstwoorden);
        lijst.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_studenten_beheren:
                Intent intent = new Intent(this, AdminStudent.class);
                startActivity(intent);
                return true;
            case R.id.menu_scores:
                Intent i = new Intent(this, AdminScores.class);
                startActivity(i);
                return true;
            case R.id.menu_fouten:
                Intent in = new Intent(this, AdminFouten.class);
                startActivity(in);
                return true;
            case R.id.menu_groepen:
                Intent inte = new Intent(this, AdminGroepen.class);
                startActivity(inte);
                return true;
            case R.id.menu_keuzes:
                Intent inten = new Intent(this, AdminKeuzes.class);
                startActivity(inten);
                return true;
            case R.id.menu_lijsten:
                Intent intent1 = new Intent(this, AdminLijsten.class);
                startActivity(intent1);
                return true;
            case R.id.menu_onderdelen:
                Intent intent2 = new Intent(this, AdminOnderdelen.class);
                startActivity(intent2);
                return true;
            case R.id.menu_onderzoeken:
                Intent intent3 = new Intent(this, AdminOnderzoeken.class);
                startActivity(intent3);
                return true;
            case R.id.menu_woorden:
                Intent intent4 = new Intent(this, AdminWoorden.class);
                startActivity(intent4);
                return true;
            case R.id.menu_woordenlijsten:
                Intent intent5 = new Intent(this, AdminWoordlijsten.class);
                startActivity(intent5);
                return true;
            case R.id.afsluiten:
                finish();
                return true;
            default:
                return false;
        }
    }
}

