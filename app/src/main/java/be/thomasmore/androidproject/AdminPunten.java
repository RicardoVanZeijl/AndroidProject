package be.thomasmore.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


/**
 * Created by larsg on 19/12/2017.
 */

public class AdminPunten extends AppCompatActivity {

    private DatabaseHelper db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_punten);

        db = new DatabaseHelper(this);
        readScores();
    }

    // scores inlezen in listview
    private void readScores() {
        final List<Score> scores = db.getScores();


        ArrayAdapter<Score> adapter = new ArrayAdapter<Score>(this, android.R.layout.simple_list_item_1, scores);

        final ListView lijst = (ListView) findViewById(R.id.lijstscore);
        lijst.setAdapter(adapter);
    }

    ;


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
                Intent i = new Intent(this, AdminPunten.class);
                startActivity(i);
                return true;
            case R.id.afsluiten:
                finish();
                return true;
            default:
                return false;
        }
    }
}
