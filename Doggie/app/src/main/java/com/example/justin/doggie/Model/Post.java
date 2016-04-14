package com.example.justin.doggie.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable
{
    private String postId;
    private String userId;
    private String username;
    private String post;
    private String imageURL;
    private int numFavorite;
    private int numComment;
    private double latitude;
    private double longitude;
    private boolean isDeleted;

    public Post()
    {

    }

    public Post(String username, double latitude, double longitude, String post, int numFavorite, int numComment)
    {
        this.username = username;
        this.latitude = latitude;
        this.longitude = longitude;
        this.post = post;
        this.numFavorite = numFavorite;
        this.numComment = numComment;
    }

    protected Post( Parcel in )
    {
        postId = in.readString();
        userId = in.readString();
        username = in.readString();
        post = in.readString();
        imageURL = in.readString();
        numFavorite = in.readInt();
        numComment = in.readInt();
        latitude = in.readDouble();
        longitude = in.readDouble();
        isDeleted = in.readByte() != 0;
    }

    public static final Creator<Post> CREATOR = new Creator<Post>()
    {
        @Override
        public Post createFromParcel( Parcel in )
        {
            return new Post(in);
        }

        @Override
        public Post[] newArray( int size )
        {
            return new Post[size];
        }
    };

    public String getPostId()
    {
        return postId;
    }

    public String getUserId()
    {
        return userId;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPost()
    {
        return post;
    }

    public String getImageURL()
    {
        return imageURL;
    }

    public int getNumFavorite()
    {
        return numFavorite;
    }

    public int getNumComment()
    {
        return numComment;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public boolean isIsDeleted()
    {
        return isDeleted;
    }

    public void setPostId(String postId)
    {
        this.postId = postId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPost(String post)
    {
        this.post = post;
    }

    public void setImageURL(String imageURL)
    {
        this.imageURL = imageURL;
    }

    public void setNumFavorite(int numFavorite)
    {
        this.numFavorite = numFavorite;
    }

    public void setNumComment(int numComment)
    {
        this.numComment = numComment;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public void setIsDeleted(boolean isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags )
    {
        dest.writeString(postId);
        dest.writeString(userId);
        dest.writeString(username);
        dest.writeString(post);
        dest.writeString(imageURL);
        dest.writeInt(numFavorite);
        dest.writeInt(numComment);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeByte((byte) (isDeleted ? 1 : 0));
    }
}