package com.example.angeloamadora.quiz2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class EditUnitActivity extends ActionBarActivity {

    EditText etUnitName, etEquivalence;
    Button buttonCancel, buttonSave, buttonDelete;
    DatabaseOpenHelper dbHelper;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_unit);

        etUnitName = (EditText) findViewById(R.id.et_unitname);
        etEquivalence = (EditText) findViewById(R.id.et_equivalence);
        buttonCancel = (Button) findViewById(R.id.button_cancel);
        buttonSave = (Button) findViewById(R.id.button_save);
        buttonDelete = (Button) findViewById(R.id.button_delete);
        dbHelper = new DatabaseOpenHelper(getBaseContext());

        etUnitName.setText(getIntent().getStringExtra("name"));
        String x = String.valueOf(getIntent().getIntExtra("equivalent", 0));
        etEquivalence.setText(x);


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = getIntent().getIntExtra("id", 0);
                Note n = new Note(id, etUnitName.getText().toString(), etEquivalence.getText().toString());
                dbHelper.updateNote(n);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = getIntent().getIntExtra("id", 0);
                Note n = new Note(id, etUnitName.getText().toString(), etEquivalence.getText().toString());
                dbHelper.deleteNote(id);
            }
        });


    }
}
