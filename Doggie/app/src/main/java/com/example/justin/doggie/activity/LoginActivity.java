package com.example.justin.doggie.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.justin.doggie.Model.User;
import com.example.justin.doggie.R;

public class LoginActivity extends AppCompatActivity
{
    Button buttonLogin, buttonRegister;
    EditText etUsername, etPassword;
    User currentUser;

    final static int REQUEST_USER = 0;
    final static String KEY_USER = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        currentUser = new User("name", "surname", "email", "mobile", "username", "password", null);

        buttonLogin = (Button) findViewById(R.id.button_login);
        buttonRegister = (Button) findViewById(R.id.button_register);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);

        buttonLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if( currentUser.getUsername().equals(username) && currentUser.getPassword().equals(password) )
                {
                    Intent intent = new Intent();
                    intent.setClass(getBaseContext(), AdoptionActivity.class);
                    startActivity(intent);
                }
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), RegisterActivity.class);
                startActivityForResult(intent, REQUEST_USER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == REQUEST_USER && resultCode == RESULT_OK )
        {
            currentUser = (User) data.getExtras().get(KEY_USER);
        }
    }
}
