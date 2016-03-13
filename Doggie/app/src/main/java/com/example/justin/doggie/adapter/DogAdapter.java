package com.example.justin.doggie.adapter;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.justin.doggie.Objects.Dog;
import com.example.justin.doggie.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Angelo Amadora on 3/11/2016.
 */


    public class DogAdapter extends RecyclerView.Adapter<DogAdapter.DogHolder> {
        ArrayList<Dog> dogs;

        public DogAdapter(ArrayList<Dog> dogs) {
            //arraylist of dogs gets passed here
            this.dogs = dogs;
        }

        @Override
        public DogHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //attaches the dog_item.xml
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_item, null);
            return new DogHolder(v);
        }

        @Override
        public void onBindViewHolder(DogHolder holder, int position) {
//        String name = dogs.get(position);
//        String location;
//        Image owner;
//        Image dog;
//        String contact;
//        boolean hasPapers;


            Dog temp = dogs.get(position);

            holder.tvDogName.setText(temp.name);
            holder.tvDogLocation.setText(temp.location);
            holder.tvOwnerContact.setText(temp.contact);

            if (temp.isStray) {
                holder.ivHasPapers.setVisibility(View.INVISIBLE);
            }
        }


            public int getItemCount() {
                return dogs.size();
            }

            public class DogHolder extends RecyclerView.ViewHolder {
                TextView tvDogName;
                TextView tvDogLocation;

                CircleImageView civOwnerPicture;
                ImageView ivDogPicture;
                ImageView ivHasPapers;
                TextView tvOwnerContact;

                public DogHolder(View itemView) {
                    super(itemView);
                    tvDogName = (TextView) itemView.findViewById(R.id.tvDogName);
                    tvDogLocation = (TextView) itemView.findViewById(R.id.tvDogLocation);
                    civOwnerPicture = (CircleImageView) itemView.findViewById(R.id.civOwnerPic);
                    ivDogPicture = (ImageView) itemView.findViewById(R.id.ivDogPicture);
                    ivHasPapers = (ImageView) itemView.findViewById(R.id.ivHasPapers);
                    tvOwnerContact = (TextView) itemView.findViewById(R.id.tvOwnerNo);
                }
            }

    }
