package com.example.justin.doggie.adapter;

import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.justin.doggie.R;
import com.example.justin.doggie.model.Post;

import org.w3c.dom.Text;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Angelo Amadora on 3/14/2016.
 */
public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.TimelineHolder> {

    public final static String DESTINATION_ACCOUNT_DETAILS = "AccountDetails", DESTINATION_COMMENT = "Comment";
    ArrayList<Post> posts;
    private OnItemClickListener mOnItemClickListener;

    public TimelineAdapter(ArrayList<Post> posts){
        this.posts = posts;
    }

    @Override
    public TimelineHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.userpost_item, parent, false);
        return new TimelineHolder(v);
    }

    @Override
    public void onBindViewHolder(TimelineHolder holder, final int position) {
        Post temp = posts.get(position);

        //civDisplayPicture
        holder.username.setText(temp.getUsername());
        holder.location.setText("( " + temp.getLatitude() + ", " + temp.getLongitude() + " )");
        //ivPostPicture
        holder.message.setText(temp.getPost());

        holder.ivPostPicture.setTag(holder);
        holder.ivFavorite.setTag(holder);
        holder.ivFavorite.setTag(R.id.IS_CLICKED, false);

        // TODO: pass userID, not post
        holder.civDisplayPicture.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mOnItemClickListener.onItemClick(posts.get(position), DESTINATION_ACCOUNT_DETAILS);
            }
        });

        holder.username.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mOnItemClickListener.onItemClick(posts.get(position), DESTINATION_ACCOUNT_DETAILS);
            }
        });

        holder.ivComment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                mOnItemClickListener.onItemClick(posts.get(position), DESTINATION_COMMENT);
            }
        });

        holder.tvNumComment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                mOnItemClickListener.onItemClick(posts.get(position), DESTINATION_COMMENT);
            }
        });

        holder.ivPostPicture.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick( View v )
            {
                setIVFavoriteImage(v);

                return true;
            }
        });

        holder.ivFavorite.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick( View v )
            {
                setIVFavoriteImage(v);

                return true;
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
        View container;

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
            container = itemView.findViewById(R.id.container);
        }
    }

    public void setmOnItemClickListener(OnItemClickListener m)
    {
        this.mOnItemClickListener = m;
    }

    public interface OnItemClickListener
    {
        public void onItemClick(Post p, String destination);
    }

    private void setIVFavoriteImage( View v )
    {
        if( ((TimelineHolder) v.getTag()).ivFavorite.getTag(R.id.IS_CLICKED) == (Object) false )
        {
            ((TimelineHolder) v.getTag()).ivFavorite.setImageResource(R.drawable.ic_favorite_black_24dp);
            ((TimelineHolder) v.getTag()).ivFavorite.setTag(R.id.IS_CLICKED, true);
        }
        else
        {
            ((TimelineHolder) v.getTag()).ivFavorite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            ((TimelineHolder) v.getTag()).ivFavorite.setTag(R.id.IS_CLICKED, false);
        }
    }
}
