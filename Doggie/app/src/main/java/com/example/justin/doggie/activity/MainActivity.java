package com.example.justin.doggie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.justin.doggie.R;
import com.example.justin.doggie.adapter.DogAdapter;
import com.example.justin.doggie.fragment.AdoptionFragment;
import com.example.justin.doggie.fragment.SettingsFragment;
import com.example.justin.doggie.fragment.TimelineFragment;
import com.example.justin.doggie.fragment.postDialogFragment;
import com.example.justin.doggie.model.Dog;
import com.example.justin.doggie.model.Post;
import com.example.justin.doggie.model.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    ArrayList<Dog> dogs;
    ArrayList<Post> posts;
    User currentUser;
    Fragment adoptionFragment = null, settingsFragment = null, timelineFragment = null;
    FragmentManager fragmentManager;
    DrawerLayout drawerLayout;
    TextView sampleText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Add a new post
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Supposed to open a dialog", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                postDialogFragment df = new postDialogFragment();
                df.show(getFragmentManager(), "");

            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        currentUser = (User) getIntent().getExtras().get(LoginActivity.KEY_USER);

        // temporary solution
        dogs = new ArrayList<>(0);
        dogs.add(new Dog("Elizabeth", true, "Taft", "09178889999"));
        dogs.add(new Dog("Kerrbie", false, "Makati", "09178880000"));
        dogs.add(new Dog("AJ", false, "Paranaque", "09178865800"));


        posts = new ArrayList<>(0);
        posts.add(new Post("kerbbie","Taft","my dog lit fam",9,99));
        posts.add(new Post("AJ","Bicuts","asdfasdfasf",9,95));
        posts.add(new Post("Hannah","IDK","Dog be crazy",9,2));
        posts.add(new Post("WIll","Near taft daw","great dog",9,199));

    }

    @Override
    public void onBackPressed()
    {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            // TODO: exitApplication
            //super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        fragmentManager = getSupportFragmentManager();
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if ( id == R.id.nav_timeline )
        {
            getSupportActionBar().setTitle("Timeline");

            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("posts", posts);

            if( timelineFragment == null )
            {
                timelineFragment = new TimelineFragment();
                timelineFragment.setArguments(bundle);
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            fragmentManager.beginTransaction().replace(R.id.content_main, timelineFragment).commit();
        }
        else if( id == R.id.nav_adoption )
        {
            getSupportActionBar().setTitle("Adoption");

            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("dogs", dogs);

            if( adoptionFragment == null )
            {
                adoptionFragment = new AdoptionFragment();
                adoptionFragment.setArguments(bundle);
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            fragmentManager.beginTransaction().replace(R.id.content_main, adoptionFragment).commit();
        }
        else if( id == R.id.nav_edit_account )
        {
            getSupportActionBar().setTitle("Edit Profile Settings");

            Bundle bundle = new Bundle();
            bundle.putParcelable(LoginActivity.KEY_USER, currentUser);

            if( settingsFragment == null )
            {
                settingsFragment = new SettingsFragment();
                settingsFragment.setArguments(bundle);
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            fragmentManager.beginTransaction().replace(R.id.content_main, settingsFragment).commit();
        }
        else if( id == R.id.nav_logout )
        {
            // TODO: temp solution
            Intent intent = new Intent();
            intent.putExtra(LoginActivity.KEY_USER, currentUser);
            setResult(RESULT_OK, intent);

            finish();
        }

        return true;
    }

    public void updateCurrentUser( User u )
    {
        currentUser = u;
        fragmentManager.beginTransaction().remove(settingsFragment).commit();
        settingsFragment = null;
        getSupportActionBar().setTitle("MainActivity");
    }

    public void cancelFragment()
    {
        fragmentManager.beginTransaction().remove(settingsFragment).commit();
        settingsFragment = null;
        getSupportActionBar().setTitle("MainActivity");
    }

    public void onYesSelected( String s )
    {
        sampleText.setText(s);
        Toast.makeText(getBaseContext(), "The user has selected yes.", Toast.LENGTH_LONG).show();
    }

}
