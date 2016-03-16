package com.example.justin.doggie.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.justin.doggie.R;
import com.example.justin.doggie.adapter.DogAdapter;
import com.example.justin.doggie.fragment.AdoptionFragment;
import com.example.justin.doggie.fragment.SettingsFragment;
import com.example.justin.doggie.fragment.TimelineFragment;
import com.example.justin.doggie.model.Dog;
import com.example.justin.doggie.model.Post;
import com.example.justin.doggie.model.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    ArrayList<Dog> dogs;
    ArrayList<Post> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
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
        Fragment fragment = null;

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if ( id == R.id.nav_timeline )
        {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("posts", posts);

            fragment = new TimelineFragment();
            fragment.setArguments(bundle);
        }
        else if( id == R.id.nav_adoption )
        {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("dogs", dogs);


            fragment = new AdoptionFragment();
            fragment.setArguments(bundle);
        }
        else if( id == R.id.nav_edit_account )
        {
            fragment = new SettingsFragment();
        }
        else if( id == R.id.nav_logout )
        {
            finish();
        }


        if( fragment != null )
        {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);

            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit();
        }

        return true;
    }
}
