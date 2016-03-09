package com.example.justin.doggie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity
{
    Button btnFake;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // a button to simulate temporary login
            btnFake = (Button) findViewById(R.id.tempLogin);
            btnFake.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent();
                    i.setClass(getBaseContext(),AdoptionActivity.class);
                    startActivity(i);
                }
            });

    }
}
