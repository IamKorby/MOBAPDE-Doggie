package com.example.justin.doggie.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Angelo Amadora on 3/14/2016.
 */
public class Post implements Parcelable{
    private int UserId;
    private int PostId;
    private int picture;
    private int userPicture;
    private String message;
    private int numLikes;
    private int numComments;

    //tempshit
    private String username;
    private String location;

    public Post(String message, int numLikes, int numComments){
        this.message = message;
        this.numLikes = numLikes;
        this.numComments = numComments;
    }
    public Post(String username,String location,String message, int numLikes, int numComments){
        this.username = username;
        this.location = location;
        this.message = message;
        this.numLikes = numLikes;
        this.numComments = numComments;
    }

    protected Post(Parcel in) {
        UserId = in.readInt();
        PostId = in.readInt();
        picture = in.readInt();
        userPicture = in.readInt();
        message = in.readString();
        numLikes = in.readInt();
        numComments = in.readInt();
        username = in.readString();
        location = in.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public String getMessage(){
        return this.message;
    }

    public int getNumLikes(){
        return this.numLikes;
    }

    public int getNumComments(){
        return this.numComments;
    }

    public String getUsername(){
        return  this.username;
    }

    public String getLocation(){
        return this.location;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(UserId);
        dest.writeInt(PostId);
        dest.writeInt(picture);
        dest.writeInt(userPicture);
        dest.writeString(message);
        dest.writeInt(numLikes);
        dest.writeInt(numComments);
        dest.writeString(username);
        dest.writeString(location);
    }
}
