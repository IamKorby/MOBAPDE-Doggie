package com.example.justin.doggie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.justin.doggie.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountDetailsActivity extends AppCompatActivity
{
    CircleImageView civImage;
    TextView tvUsername, tvMobile;
    RecyclerView rvUserPreference, rvPost;

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


    }
}
