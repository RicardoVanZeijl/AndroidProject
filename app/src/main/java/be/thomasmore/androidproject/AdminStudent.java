package be.thomasmore.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by larsg on 21/12/2017.
 */

public class AdminStudent extends AppCompatActivity {


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
