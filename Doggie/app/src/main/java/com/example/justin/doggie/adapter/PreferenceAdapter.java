package com.example.justin.doggie.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.justin.doggie.model.Preference;
import com.example.justin.doggie.R;

import java.util.ArrayList;

/**
 * Created by Justin on 12/03/2016.
 */
public class PreferenceAdapter extends RecyclerView.Adapter<PreferenceAdapter.PreferenceViewHolder>
{
    private ArrayList<Preference> preferenceList;
    private ArrayList<Integer> preferenceId;
    private boolean isEdit;

    public PreferenceAdapter( ArrayList<Preference> preferenceList, boolean isEdit )
    {
        this.preferenceList = preferenceList;
        this.preferenceId = new ArrayList<>(0);
        this.isEdit = isEdit;
    }

    public class PreferenceViewHolder extends RecyclerView.ViewHolder
    {
        CheckBox cbPreference;
        View container;

        public PreferenceViewHolder(View itemView)
        {
            super(itemView);

            cbPreference = (CheckBox) itemView.findViewById(R.id.cb_preference);
            container = itemView.findViewById(R.id.container);
        }
    }

    @Override
    public PreferenceViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.preference_item, parent, false);

        return new PreferenceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder, int position)
    {
        Preference preference = preferenceList.get(position);

        holder.cbPreference.setText(preference.getPreference());
        holder.cbPreference.setId(preference.getId());

        if( isEdit && preferenceId.indexOf((Integer) holder.cbPreference.getId()) != -1 )
        {
            holder.cbPreference.setChecked(true);
        }

        holder.container.setTag(holder);
        holder.container.setId(preference.getId());

        holder.container.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean hasCheck = ((PreferenceViewHolder) v.getTag()).cbPreference.isChecked();

                ((PreferenceViewHolder) v.getTag()).cbPreference.setChecked(!hasCheck);

                if (!hasCheck)
                {
                    if( preferenceId.indexOf((Integer) v.getId()) == -1  )
                    {
                        preferenceId.add((Integer) v.getId());
                    }
                } else
                {
                    preferenceId.remove((Integer) v.getId());
                }

                String ids = "";

                for (int i = 0; i < preferenceId.size(); i++)
                {
                    ids += preferenceId.get(i).toString();
                }

                //Toast.makeText(v.getContext(), "V:" + ids, Toast.LENGTH_SHORT).show();
            }
        });

        holder.cbPreference.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if( isChecked )
                {
                    if( preferenceId.indexOf((Integer) buttonView.getId()) == -1  )
                    {
                        preferenceId.add((Integer) buttonView.getId());
                    }
                }
                else
                {
                    preferenceId.remove((Integer) buttonView.getId());
                }

                String ids = "";

                for( int i = 0; i < preferenceId.size(); i++ )
                {
                    ids += preferenceId.get(i).toString();
                }

                //Toast.makeText(buttonView.getContext(), "CB:" + ids, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return preferenceList.size();
    }

    public ArrayList<Integer> getUserPreferenceIds()
    {
        return preferenceId;
    }

    public void setUserPreferenceIds(ArrayList<Integer> userPreferenceIds) { this.preferenceId = userPreferenceIds; }
}
