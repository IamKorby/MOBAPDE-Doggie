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
}
