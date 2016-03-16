package com.example.justin.doggie.fragment;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.justin.doggie.R;
import com.example.justin.doggie.activity.AccountDetailsActivity;
import com.example.justin.doggie.activity.CommentActivity;
import com.example.justin.doggie.adapter.DogAdapter;
import com.example.justin.doggie.adapter.TimelineAdapter;
import com.example.justin.doggie.model.Dog;
import com.example.justin.doggie.model.Post;

import java.util.ArrayList;

/**
 * Created by Justin on 14/03/2016.
 */
public class TimelineFragment extends Fragment
{

    RecyclerView recyclerView;
    TimelineAdapter TimelineAdapter;
    ArrayList<Post> posts;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_timeline, container, false);

        // start of code
        posts = getArguments().getParcelableArrayList("posts");

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rvTimeline);

        TimelineAdapter = new TimelineAdapter(posts);

        recyclerView.setAdapter(TimelineAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));

        TimelineAdapter.setmOnItemClickListener(new TimelineAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick( Post p, String s )
            {
                if ( s == TimelineAdapter.DESTINATION_ACCOUNT_DETAILS )
                {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), AccountDetailsActivity.class);
                    intent.putExtra("post", p);
                    startActivity(intent);
                }
                else if ( s == TimelineAdapter.DESTINATION_COMMENT )
                {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), CommentActivity.class);
                    intent.putExtra("post", p);
                    startActivity(intent);
                }

            }
        });

        return rootView;
    }
}
