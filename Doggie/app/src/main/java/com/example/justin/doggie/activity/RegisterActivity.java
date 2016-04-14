package com.example.justin.doggie.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.justin.doggie.model.Preference;
import com.example.justin.doggie.model.ServerInfo;
import com.example.justin.doggie.model.User;
import com.example.justin.doggie.R;
import com.example.justin.doggie.adapter.PreferenceAdapter;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
        new GetPreferenceListHelper().execute();

        buttonSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new RegisterUserHelper().execute();
                /*
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
                    /*User user = new User(firstName, lastName, email, mobileNo, username, password, userPreference);

                    // temporary solution
                    Intent intent = new Intent();
                    intent.putExtra(LoginActivity.KEY_USER, user);
                    setResult(RESULT_OK, intent);

                    finish();
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }*/
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

    public class GetPreferenceListHelper extends AsyncTask<Void, Void, String>
    {
        @Override
        protected String doInBackground( Void... params )
        {
            String url = ServerInfo.url + "GetAllPreferencesServlet";

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = null;

            try
            {
                response = client.newCall(request).execute();
                return response.body().string();
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }

            return "";
        }

        @Override
        protected void onPostExecute( String s )
        {
            super.onPostExecute(s);

            preferenceList = new ArrayList<>(0);

            try
            {
                JSONArray preferenceListFromServer = new JSONArray(s);

                for( int i = 0; i < preferenceListFromServer.length(); i++ )
                {
                    JSONObject preference = preferenceListFromServer.getJSONObject(i);
                    int id = preference.getInt("id");
                    String preferenceItem = preference.getString("preference");

                    preferenceList.add(new Preference(id, preferenceItem));
                }

                preferenceAdapter = new PreferenceAdapter(preferenceList, false);

                rvPreferences.setAdapter(preferenceAdapter);
                rvPreferences.setLayoutManager(new LinearLayoutManager(getBaseContext()));
            }
            catch ( JSONException e )
            {
                e.printStackTrace();
            }
        }
    }

    public class RegisterUserHelper extends AsyncTask<String, Void, String>
    {
        User user = new User();

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            user.setFirstName(etFirstName.getText().toString());
            user.setLastName(etLastName.getText().toString());
            user.setEmail(etEmail.getText().toString());
            user.setMobileNumber(etMobile.getText().toString());
            user.setUsername(etUsername.getText().toString());
            user.setPassword(etPassword.getText().toString());

            // get the chosen preferenceIds of the user and query it from the preferenceList / database
            user.setUserPreferences(getUserPreferences(preferenceAdapter.getUserPreferenceIds()));
        }

        @Override
        protected String doInBackground( String... params )
        {
            String url = ServerInfo.url + "AddUserServlet";

            OkHttpClient client = new OkHttpClient();

            //JSONArray preferences = new JSONArray(user.getUserPreferences());
            Gson gson = new Gson();
            String preferences = gson.toJson(user.getUserPreferences());

            //Toast.makeText(RegisterActivity.this, preferences, Toast.LENGTH_SHORT).show();

            // this request needs to send data from client to server
            RequestBody postRequestBody = new FormBody.Builder()
                    .add("firstName", user.getFirstName())
                    .add("lastName", user.getLastName())
                    .add("email", user.getEmail())
                    .add("mobileNumber", user.getMobileNumber())
                    .add("username", user.getUsername())
                    .add("password", user.getPassword())
                    .add("userPreferences", preferences.toString())
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(postRequestBody)
                    .build();

            try
            {
                client.newCall(request).execute();
                // In this case, the client is not waiting for a response back
                // but it's better to check if the request was handled correctly or not
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            return null;
        }
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
