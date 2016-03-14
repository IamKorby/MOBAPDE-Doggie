package com.example.justin.doggie.model;

/**
 * Created by Angelo Amadora on 3/14/2016.
 */
public class Post {
    private int UserId;
    private int PostId;
    private int picture;
    private String message;
    private int numLikes;
    private int numComments;

    public Post(String message, int numLikes, int numComments){
        this.message = message;
        this.numLikes = numLikes;
        this.numComments = numComments;
    }

    public String getMessage(){
        return this.message;
    }

    public int getNumLikes(){
        return this.numLikes;
    }

    public int getNumComments(){
        return this.numComments;
    }
}
