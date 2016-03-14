package com.example.justin.doggie.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Angelo Amadora on 3/8/2016.
 */
public class User implements Parcelable
{
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNo;
    private String username;
    private String password;
    private ArrayList<Preference> preferences;

    //These are just temporary datatypes for the variables
    private String location;

    public User( String firstName, String lastName, String email, String mobileNo, String username, String password, ArrayList<Preference> preferences )
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNo = mobileNo;
        this.username = username;
        this.password = password;
        this.preferences = preferences;
    }

    public User( int id, String firstName, String lastName, String email, String mobileNo, String username, String password, ArrayList<Preference> preferences )
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNo = mobileNo;
        this.username = username;
        this.password = password;
        this.preferences = preferences;
    }

    protected User(Parcel in)
    {
        id = in.readInt();
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
        mobileNo = in.readString();
        username = in.readString();
        password = in.readString();
        location = in.readString();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getMobileNo()
    {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo)
    {
        this.mobileNo = mobileNo;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public ArrayList<Preference> getPreferences()
    {
        return preferences;
    }

    public void setPreferences(ArrayList<Preference> preferences)
    {
        this.preferences = preferences;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(id);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(email);
        dest.writeString(mobileNo);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(location);
    }

    public static final Creator<User> CREATOR = new Creator<User>()
    {
        @Override
        public User createFromParcel(Parcel in)
        {
            return new User(in);
        }

        @Override
        public User[] newArray(int size)
        {
            return new User[size];
        }
    };
}
