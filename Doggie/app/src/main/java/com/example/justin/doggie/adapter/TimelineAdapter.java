package com.example.justin.doggie.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Angelo Amadora on 3/14/2016.
 */
public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.TimelineHolder> {

    @Override
    public TimelineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(TimelineHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TimelineHolder extends RecyclerView.ViewHolder {

        public TimelineHolder(View itemView) {
            super(itemView);
        }
    }
}
