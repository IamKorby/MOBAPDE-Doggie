package com.example.justin.doggie.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.justin.doggie.R;
import com.example.justin.doggie.activity.LoginActivity;
import com.example.justin.doggie.activity.MainActivity;
import com.example.justin.doggie.adapter.PreferenceAdapter;
import com.example.justin.doggie.model.Preference;
import com.example.justin.doggie.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Justin on 14/03/2016.
 */
public class SettingsFragment extends Fragment
{
    Button buttonSave, buttonCancel;
    EditText etFirstName, etLastName, etEmail, etMobile, etUsername, etPassword, etConfirmPassword;
    RecyclerView rvPreferences;
    PreferenceAdapter preferenceAdapter;
    ArrayList<Preference> preferenceList;
    User currentUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        currentUser = (User) getArguments().get("user");

        buttonSave = (Button) rootView.findViewById(R.id.button_save);
        buttonCancel = (Button) rootView.findViewById(R.id.button_cancel);
        etFirstName = (EditText) rootView.findViewById(R.id.et_first_name);
        etLastName = (EditText) rootView.findViewById(R.id.et_last_name);
        etEmail = (EditText) rootView.findViewById(R.id.et_email);
        etMobile = (EditText) rootView.findViewById(R.id.et_mobile);
        etUsername = (EditText) rootView.findViewById(R.id.et_username);
        etPassword = (EditText) rootView.findViewById(R.id.et_password);
        etConfirmPassword = (EditText) rootView.findViewById(R.id.et_confirm_password);
        rvPreferences = (RecyclerView) rootView.findViewById(R.id.recycler_view_preference);

        etFirstName.setText(currentUser.getFirstName());
        etLastName.setText(currentUser.getLastName());
        etEmail.setText(currentUser.getEmail());
        etMobile.setText(currentUser.getMobileNo());
        etUsername.setText(currentUser.getUsername());
        etPassword.setText(currentUser.getPassword());

        // get all preferences from the database
        new GetPreferenceListHelper().execute();

        buttonSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
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
                    ((MainActivity) getActivity()).updateCurrentUser(user);
                }
                else
                {
                    Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                ((MainActivity) getActivity()).cancelFragment();
            }
        });

        return rootView;
    }

    public class GetPreferenceListHelper extends AsyncTask<Void, Void, String>
    {
        @Override
        protected String doInBackground( Void... params )
        {
            String url = "http://10.100.203.66:8080/DoggieServer/GetAllPreferencesServlet";

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

                preferenceAdapter = new PreferenceAdapter(preferenceList, true);
                preferenceAdapter.setUserPreferenceIds(currentUser.getPreferenceIds());

                rvPreferences.setAdapter(preferenceAdapter);
                rvPreferences.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
            catch ( JSONException e )
            {
                e.printStackTrace();
            }
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
