package com.example.justin.doggie.Objects;

/**
 * Created by Angelo Amadora on 3/8/2016.
 */
public class Dog {
    public String name;
    public boolean isStray;
    public int mainPic;
    public String description;
    public String location;

    //temporary since wala pang database
    public String contact;
    //more temporary things
    public Dog(String name,boolean isStray,String location, String contact){
        this.name = name;
        this.isStray = isStray;
        this.location = location;
        this.contact = contact;
    }

}
