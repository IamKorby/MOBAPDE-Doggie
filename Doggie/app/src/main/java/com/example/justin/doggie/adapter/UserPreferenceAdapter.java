package com.example.justin.doggie.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.justin.doggie.R;
import com.example.justin.doggie.model.Preference;

import java.util.ArrayList;

/**
 * Created by Justin on 16/03/2016.
 */
public class UserPreferenceAdapter extends RecyclerView.Adapter<UserPreferenceAdapter.UserPreferenceViewHolder>
{
    ArrayList<Preference> userPreferenceList;

    public UserPreferenceAdapter( ArrayList<Preference> userPreferenceList )
    {
        this.userPreferenceList = userPreferenceList;
    }

    public class UserPreferenceViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvUserPreference;

        public UserPreferenceViewHolder(View itemView)
        {
            super(itemView);

            tvUserPreference = (TextView) itemView.findViewById(R.id.tv_user_preferences);
        }
    }

    @Override
    public UserPreferenceViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_preference_item, parent, false);

        return new UserPreferenceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UserPreferenceViewHolder holder, int position)
    {
        Preference userPreference = userPreferenceList.get(position);

        holder.tvUserPreference.setText(userPreference.getPreference());
    }

    @Override
    public int getItemCount()
    {
        return userPreferenceList.size();
    }
}
