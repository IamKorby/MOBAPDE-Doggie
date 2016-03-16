package com.example.justin.doggie.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.justin.doggie.R;
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

        View rootView = inflater.inflate(R.layout.fragment_adoption, container, false);

        // start of code
        posts = getArguments().getParcelableArrayList("posts");

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rvAdoption);

        TimelineAdapter = new TimelineAdapter(posts);

        recyclerView.setAdapter(TimelineAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));

        return rootView;
    }
}
