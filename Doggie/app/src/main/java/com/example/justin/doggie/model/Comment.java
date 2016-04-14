package com.example.justin.doggie.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Date;

public class Comment implements Parcelable
{
    private String commentId;
    private String userId;
    private String postId;
    private String comment;
    private Date dateTime;

    public Comment()
    {

    }

    protected Comment( Parcel in )
    {
        commentId = in.readString();
        userId = in.readString();
        postId = in.readString();
        comment = in.readString();
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>()
    {
        @Override
        public Comment createFromParcel( Parcel in )
        {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray( int size )
        {
            return new Comment[size];
        }
    };

    public String getCommentId()
    {
            return commentId;
    }

    public String getUserId()
    {
            return userId;
    }

    public String getPostId()
    {
            return postId;
    }

    public String getComment()
    {
            return comment;
    }

    public Date getDateTime()
    {
            return dateTime;
    }

    public void setCommentId(String commentId)
    {
            this.commentId = commentId;
    }

    public void setUserId(String userId)
    {
            this.userId = userId;
    }

    public void setPostId(String postId)
    {
            this.postId = postId;
    }

    public void setComment(String comment)
    {
            this.comment = comment;
    }

    public void setDateTime(Date dateTime)
    {
            this.dateTime = dateTime;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags )
    {
        dest.writeString(commentId);
        dest.writeString(userId);
        dest.writeString(postId);
        dest.writeString(comment);
    }
}
