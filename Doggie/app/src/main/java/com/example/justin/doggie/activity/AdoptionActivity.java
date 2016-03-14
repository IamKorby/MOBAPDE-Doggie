package com.example.justin.doggie.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.justin.doggie.model.Dog;
import com.example.justin.doggie.R;
import com.example.justin.doggie.adapter.DogAdapter;

import java.util.ArrayList;

public class AdoptionActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Dog> dogs = new ArrayList<>();
    DogAdapter dogAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption);

        //start of code
        recyclerView = (RecyclerView) findViewById(R.id.rvAdoption);
        dogs.add(new Dog("Elizabeth",true,"Taft","09178889999"));
        dogs.add(new Dog("Kerrbie",false,"Makati","09178880000"));
        dogs.add(new Dog("AJ",false,"Paranaque","09178865800"));

        dogAdapter = new DogAdapter(dogs);

        recyclerView.setAdapter(dogAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    }

}
