package com.example.justin.doggie.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.justin.doggie.model.Preference;
import com.example.justin.doggie.model.User;
import com.example.justin.doggie.R;
import com.example.justin.doggie.adapter.PreferenceAdapter;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity
{
    Button buttonSave, buttonCancel;
    EditText etFirstName, etLastName, etEmail, etMobile, etUsername, etPassword, etConfirmPassword;
    RecyclerView rvPreferences;
    PreferenceAdapter preferenceAdapter;
    ArrayList<Preference> preferenceList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        buttonSave = (Button) findViewById(R.id.button_save);
        buttonCancel = (Button) findViewById(R.id.button_cancel);
        etFirstName = (EditText) findViewById(R.id.et_first_name);
        etLastName = (EditText) findViewById(R.id.et_last_name);
        etEmail = (EditText) findViewById(R.id.et_email);
        etMobile = (EditText) findViewById(R.id.et_mobile);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        etConfirmPassword = (EditText) findViewById(R.id.et_confirm_password);
        rvPreferences = (RecyclerView) findViewById(R.id.recycler_view_preference);

        // get all preferences from the database
        preferenceList = new ArrayList<>(0);
        preferenceList.add(new Preference(1, "White"));
        preferenceList.add(new Preference(2, "Brown"));
        preferenceList.add(new Preference(3, "Black"));
        preferenceList.add(new Preference(4, "White & Brown"));
        preferenceList.add(new Preference(5, "White & Black"));
        preferenceList.add(new Preference(6, "Curly Hair"));
        preferenceList.add(new Preference(7, "Wavy Hair"));
        preferenceList.add(new Preference(8, "Straight Hair"));
        preferenceList.add(new Preference(9, "Fluffy Hair"));
        preferenceList.add(new Preference(10, "Hybrid"));

        preferenceAdapter = new PreferenceAdapter(preferenceList, false);

        rvPreferences.setAdapter(preferenceAdapter);
        rvPreferences.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        buttonSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String email = etEmail.getText().toString();
                String mobileNo = etMobile.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();
                // get the chosen preferenceIds of the user and query it from the preferenceList / database
                ArrayList<Preference> userPreference = getUserPreferences(preferenceAdapter.getUserPreferenceIds());

                if (password.equals(confirmPassword))
                {
                    User user = new User(firstName, lastName, email, mobileNo, username, password, userPreference);

                    // temporary solution
                    Intent intent = new Intent();
                    intent.putExtra(LoginActivity.KEY_USER, user);
                    setResult(RESULT_OK, intent);

                    finish();
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }

    // temporary function
    private ArrayList<Preference> getUserPreferences( ArrayList<Integer> userPreferenceIds )
    {
        ArrayList<Preference> userPreferences = new ArrayList<>(0);

        for( Integer i : userPreferenceIds )
        {
            for( Preference p : preferenceList )
            {
                if( p.getId() == i )
                {
                    userPreferences.add(new Preference(p.getId(), p.getPreference()));
                }
            }
        }

        return userPreferences;
    }
}
