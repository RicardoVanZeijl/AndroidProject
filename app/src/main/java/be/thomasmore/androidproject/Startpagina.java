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
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
