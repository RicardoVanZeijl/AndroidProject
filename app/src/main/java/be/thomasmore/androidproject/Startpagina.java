package be.thomasmore.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;


import java.util.List;

/**
 * Created by larsg.
 *
 */

public class Startpagina extends AppCompatActivity {

    private DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpagina);

        Toolbar toolbarAdmin = findViewById(R.id.toolbar);
        setSupportActionBar(toolbarAdmin);

        db = new DatabaseHelper(this);
        readGroepen();

        final Button button = findViewById(R.id.buttonStart);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText name = (EditText) findViewById(R.id.studentNaam);

                if (name.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vul je naam in!", Toast.LENGTH_SHORT).show();
                } else {
                    EditText naamStudent = (EditText) findViewById(R.id.studentNaam);
                    String naam = naamStudent.getText().toString();

                    Spinner groepen = (Spinner) findViewById(R.id.spinnerGroepen);
                    Groep groep = new Groep();
                    groep = (Groep) groepen.getSelectedItem();

                    Student student = new Student();
                    student.setNaam(naam);
                    student.setGroepID(groep.getGroepID());

                    long studentID = db.insertStudent(student);

                    Bundle bundle = new Bundle();
                    bundle.putLong("studentID", studentID);
                    Intent i = new Intent(Startpagina.this, Voormeting.class);
                    i.putExtras(bundle);

                    startActivity(i);
                }
            }

        });
    }


    private void readGroepen() {
        final List<Groep> groepen = db.getGroepen();

        ArrayAdapter<Groep> adapter = new ArrayAdapter<Groep>(this, android.R.layout.simple_spinner_item, groepen);

        final Spinner groepenSpinner = (Spinner) findViewById(R.id.spinnerGroepen);
        groepenSpinner.setAdapter(adapter);
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
                Intent i = new Intent(this, AdminPunten.class);
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
