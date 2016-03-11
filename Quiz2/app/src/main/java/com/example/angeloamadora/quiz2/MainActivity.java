package com.example.angeloamadora.quiz2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    RecyclerView rvLeft, rvRight;
    TextView tvRight, tvequals;
    EditText etLeft;
    UnitCursorAdapter ucaRight, ucaLeft;

    private int leftUnitId = 1, rightUnitId = 1;
    private DatabaseOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvLeft = (RecyclerView) findViewById(R.id.rv_left);
        rvRight = (RecyclerView) findViewById(R.id.rv_right);
        etLeft = (EditText) findViewById(R.id.et_unit_convert_left);
        tvRight = (TextView) findViewById(R.id.tv_unit_convert_right);
        tvequals = (TextView) findViewById(R.id.tv_equals);

        ucaRight = new UnitCursorAdapter(getBaseContext(), null);
        ucaLeft = new UnitCursorAdapter(getBaseContext(), null);

        dbHelper = new DatabaseOpenHelper(getBaseContext());

        rvLeft.setAdapter(ucaLeft);
        rvRight.setAdapter(ucaRight);
        rvLeft.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        rvRight.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        ucaLeft.setmOnItemClickListener(new UnitCursorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                // TODO
                leftUnitId = Integer.parseInt(dbHelper.getNote(id).getNote());
                int y = Integer.parseInt(String.valueOf(etLeft.getText().toString()))*(leftUnitId/rightUnitId);
                tvRight.setText(Integer.toString(y));
            }

            @Override
            public void onItemLongClick(int id) {
                // TODO BONUS : View a conversion
                Intent i = new Intent();
                i.setClass(getBaseContext(),EditUnitActivity.class);
                i.putExtra("name", dbHelper.getNote(id).getTitle());
                i.putExtra("equivalent", leftUnitId);
                i.putExtra("id",dbHelper.getNote(id).getId());
                startActivity(i);
            }
        });

        ucaRight.setmOnItemClickListener(new UnitCursorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                rightUnitId = Integer.parseInt(dbHelper.getNote(id).getNote());
                int y = Integer.parseInt(String.valueOf(etLeft.getText().toString()))*(leftUnitId/rightUnitId);
                tvRight.setText(Integer.toString(y));

            }

            @Override
            public void onItemLongClick(int id) {
                // TODO BONUS : View a conversion
            }
        });

        etLeft.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO
            }
        });

        tvequals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getBaseContext(),AddUnitActivity.class );
                startActivity(i);
            }
        });
    }

    protected void onResume() {
        super.onResume();
        Cursor c = dbHelper.getNotes();
        ucaLeft.swapCursor(c);
        ucaRight.swapCursor(c);

    }

}
