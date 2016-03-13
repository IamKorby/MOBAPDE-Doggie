package com.example.justin.doggie.Model;

/**
 * Created by Justin on 12/03/2016.
 */
public class Preference
{
    private int id;
    private String preference;

    public Preference( int id, String preference )
    {
        this.id = id;
        this.preference = preference;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getPreference()
    {
        return preference;
    }

    public void setPreference(String preference)
    {
        this.preference = preference;
    }
}
