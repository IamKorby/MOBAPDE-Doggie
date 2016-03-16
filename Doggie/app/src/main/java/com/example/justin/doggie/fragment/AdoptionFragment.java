package com.example.justin.doggie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.justin.doggie.R;
import com.example.justin.doggie.adapter.DogAdapter;
import com.example.justin.doggie.model.Dog;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Justin on 14/03/2016.
 */
public class AdoptionFragment extends Fragment
{
    RecyclerView recyclerView;
    DogAdapter dogAdapter;
    ArrayList<Dog> dogs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_adoption, container, false);

        // start of code
        dogs = getArguments().getParcelableArrayList("dogs");

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rvAdoption);

        dogAdapter = new DogAdapter(dogs);

        recyclerView.setAdapter(dogAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));

        return rootView;
    }
}
