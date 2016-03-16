package com.example.justin.doggie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.justin.doggie.R;
import com.example.justin.doggie.adapter.DogAdapter;
import com.example.justin.doggie.adapter.TimelineAdapter;
import com.example.justin.doggie.adapter.UserPreferenceAdapter;
import com.example.justin.doggie.model.Post;
import com.example.justin.doggie.model.Preference;
import com.example.justin.doggie.model.User;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountDetailsActivity extends AppCompatActivity
{
    CircleImageView civImage;
    TextView tvUsername, tvMobile;
    RecyclerView rvUserPreference, rvPost;
    User user;
    TimelineAdapter timelineAdapter;
    UserPreferenceAdapter userPreferenceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);

        civImage = (CircleImageView) findViewById(R.id.civDisplayPic);
        tvUsername = (TextView) findViewById(R.id.tvUserName);
        tvMobile = (TextView) findViewById(R.id.tv_mobile);
        rvUserPreference = (RecyclerView) findViewById(R.id.recycler_view_user_preference);
        rvPost = (RecyclerView) findViewById(R.id.recycler_view_post);

        Post p = (Post) getIntent().getExtras().get("post");

        ArrayList<Preference> preferences = new ArrayList<>(0);
        preferences.add(new Preference(1, "White"));
        preferences.add(new Preference(2, "Brown"));
        preferences.add(new Preference(3, "Black"));

        user = new User(p.getUsername(), "", "", "", "", "", preferences);

        tvUsername.setText(user.getFirstName() + " " + user.getLastName());
        tvMobile.setText(user.getMobileNo());

        userPreferenceAdapter = new UserPreferenceAdapter(user.getPreferences());
        rvUserPreference.setAdapter(userPreferenceAdapter);
        rvUserPreference.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        ArrayList<Post> posts = new ArrayList<>(0);
        posts.add(p);
        posts.add(p);

        timelineAdapter = new TimelineAdapter(posts);
        rvPost.setAdapter(timelineAdapter);
        rvPost.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        timelineAdapter.setmOnItemClickListener(new TimelineAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(Post p, String s)
            {

            }
        });
    }
}
