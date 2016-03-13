package com.example.justin.doggie.Model;

/**
 * Created by Angelo Amadora on 3/8/2016.
 */
public class Dog
{
    private int id;
    private String name;
    private boolean isStray;
    private int mainPic;
    private String description;
    private String location;

    //temporary since wala pang database
    private String contact;

    public Dog( String name, boolean isStray, String location, String contact)
    {
        this.name = name;
        this.isStray = isStray;
        this.location = location;
        this.contact = contact;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isStray()
    {
        return isStray;
    }

    public void setIsStray(boolean isStray)
    {
        this.isStray = isStray;
    }

    public int getMainPic()
    {
        return mainPic;
    }

    public void setMainPic(int mainPic)
    {
        this.mainPic = mainPic;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getContact()
    {
        return contact;
    }

    public void setContact(String contact)
    {
        this.contact = contact;
    }
}
