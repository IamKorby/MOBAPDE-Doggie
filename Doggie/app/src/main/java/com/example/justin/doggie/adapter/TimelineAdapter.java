package com.example.justin.doggie.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.justin.doggie.R;
import com.example.justin.doggie.model.Post;

import org.w3c.dom.Text;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Angelo Amadora on 3/14/2016.
 */
public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.TimelineHolder> {

    ArrayList<Post> posts;

    public TimelineAdapter(ArrayList<Post> posts){
        this.posts = posts;
    }

    @Override
    public TimelineHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.userpost_item, null);
        return new TimelineHolder(v);
    }

    @Override
    public void onBindViewHolder(TimelineHolder holder, int position) {
        Post temp = posts.get(position);

        //civDisplayPicture
        holder.username.setText(temp.getUsername());
        holder.location.setText(temp.getLocation());
        //ivPostPicture
        holder.message.setText(temp.getMessage());

        holder.civDisplayPicture.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                
            }
        });

        holder.username.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class TimelineHolder extends RecyclerView.ViewHolder {

        TextView username;
        TextView location;
        CircleImageView civDisplayPicture;
        TextView message;
        ImageView ivPostPicture;
        ImageView ivFavorite;
        ImageView ivComment;
        TextView tvNumComment;


        public TimelineHolder(View itemView) {
            super(itemView);

            username = (TextView) itemView.findViewById(R.id.tvUserName);
            location = (TextView) itemView.findViewById(R.id.tvUserLocation);
            message = (TextView) itemView.findViewById(R.id.tvUserMessage);
            civDisplayPicture = (CircleImageView) itemView.findViewById(R.id.civDisplayPic);
            ivPostPicture = (ImageView) itemView.findViewById(R.id.ivPostPicture);
            ivFavorite = (ImageView) itemView.findViewById(R.id.ivFavorite);
            ivComment = (ImageView) itemView.findViewById(R.id.ivComment);
            tvNumComment = (TextView) itemView.findViewById(R.id.tvCommentCount);
        }
    }
}
