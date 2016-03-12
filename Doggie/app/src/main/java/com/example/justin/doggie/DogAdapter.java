package com.example.justin.doggie;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Angelo Amadora on 3/11/2016.
 */
public class DogAdapter extends RecyclerView.Adapter<DogAdapter.DogHolder>{

    @Override
    public DogHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(DogHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DogHolder extends RecyclerView.ViewHolder{

        public DogHolder(View itemView) {
            super(itemView);
        }
    }
}
