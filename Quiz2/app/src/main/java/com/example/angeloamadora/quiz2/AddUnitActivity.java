package com.example.angeloamadora.quiz2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddUnitActivity extends AppCompatActivity {

    EditText etUnitName, etEquivalence;
    Button buttonCancel, buttonAdd;
    DatabaseOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_unit);

        etUnitName = (EditText) findViewById(R.id.et_unitname);
        etEquivalence = (EditText) findViewById(R.id.et_equivalence);
        buttonCancel = (Button) findViewById(R.id.button_cancel);
        buttonAdd = (Button) findViewById(R.id.button_add);

        dbHelper = new DatabaseOpenHelper(getBaseContext());

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etUnitName.getText().toString();
                String conversion=etEquivalence.getText().toString();

                Note n = new Note(name,conversion);
                dbHelper.AddNote(n);
            }
        });

    }


}
