package com.example.justin.doggie.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.justin.doggie.model.Preference;
import com.example.justin.doggie.model.ServerInfo;
import com.example.justin.doggie.model.User;
import com.example.justin.doggie.R;

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

public class LoginActivity extends AppCompatActivity
{
    Button buttonLogin, buttonRegister;
    EditText etUsername, etPassword;
    User currentUser = null;
    LoginActivity loginActivity = this;

    final static int REQUEST_USER = 0, UPDATE_FROM_MAIN = 1;
    final static String KEY_USER = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //currentUser = new User("name", "surname", "email", "mobile", "username", "password", null);

        buttonLogin = (Button) findViewById(R.id.button_login);
        buttonRegister = (Button) findViewById(R.id.button_register);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);

        buttonLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new GetUserByCredentialHelper().execute();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), RegisterActivity.class);
                startActivityForResult(intent, REQUEST_USER);
            }
        });
    }

    private void login()
    {
        if( currentUser != null )
        {
            Intent intent = new Intent();
            intent.setClass(getBaseContext(), MainActivity.class);
            intent.putExtra(KEY_USER, currentUser);
            startActivityForResult(intent, UPDATE_FROM_MAIN);
        }
        else
        {
            Toast.makeText(LoginActivity.this, "Account not found.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == REQUEST_USER && resultCode == RESULT_OK )
        {
            currentUser = (User) data.getExtras().get(KEY_USER);
        }
        //TODO: temp solution
        else if( requestCode == UPDATE_FROM_MAIN && resultCode == RESULT_OK )
        {
            currentUser = (User) data.getExtras().get(KEY_USER);
        }
    }

    public class GetUserByCredentialHelper extends AsyncTask<String, Void, String>
    {
        String username, password;
        LoginActivity parentActivity;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            username = etUsername.getText().toString();
            password = etPassword.getText().toString();
            parentActivity = loginActivity;
        }

        @Override
        protected String doInBackground( String... params )
        {
            String url = ServerInfo.url + "GetUserByCredentialsServlet";

            OkHttpClient client = new OkHttpClient();

            // this request needs to send data from client to server
            RequestBody postRequestBody = new FormBody.Builder()
                    .add("username", username)
                    .add("password", password)
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(postRequestBody)
                    .build();

            Response response = null;

            try
            {
                response = client.newCall(request).execute();
                return response.body().string();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute( String s )
        {
            super.onPostExecute(s);

            currentUser = null;

            try
            {
                JSONObject userjson = new JSONObject(s);

                JSONArray preferenceArray = userjson.getJSONArray("userPreferences");

                ArrayList<Preference> preferences = new ArrayList<>(0);

                for( int i = 0; i < preferenceArray.length(); i++ )
                {
                    JSONObject preference = preferenceArray.getJSONObject(i);
                    int id = preference.getInt("id");
                    String preferenceItem = preference.getString("preference");
                    Toast.makeText(LoginActivity.this, "id = " + id + "\npreference = " + preferenceItem, Toast.LENGTH_SHORT).show();
                    preferences.add(new Preference(id, preferenceItem));
                }

                currentUser = new User(userjson.getString("userId"),
                        userjson.getString("firstName"),
                        userjson.getString("lastName"),
                        userjson.getString("email"),
                        userjson.getString("mobileNumber"),
                        userjson.getString("username"),
                        userjson.getString("password"),
                        userjson.getDouble("latitude"),
                        userjson.getDouble("longitude"));
                currentUser.setUserPreferences(preferences);

                parentActivity.login();
            }
            catch ( JSONException e )
            {
                e.printStackTrace();
                Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
