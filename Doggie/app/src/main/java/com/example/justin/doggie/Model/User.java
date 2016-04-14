package com.example.justin.doggie.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class User implements Parcelable
{
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String username;
    private String password;
    private double latitude;
    private double longitude;
    private ArrayList<Preference> userPreferences;

    public User()
    {

    }

    public User(String userId, String firstName, String lastName, String email, String mobileNumber, String username, String password, double latitude, double longitude) 
    {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.username = username;
        this.password = password;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected User( Parcel in )
    {
        userId = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
        mobileNumber = in.readString();
        username = in.readString();
        password = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        userPreferences = in.createTypedArrayList(Preference.CREATOR);
    }

    public static final Creator<User> CREATOR = new Creator<User>()
    {
        @Override
        public User createFromParcel( Parcel in )
        {
            return new User(in);
        }

        @Override
        public User[] newArray( int size )
        {
            return new User[size];
        }
    };

    public String getUserId()
    {
            return userId;
    }

    public String getFirstName()
    {
            return firstName;
    }

    public String getLastName()
    {
            return lastName;
    }

    public String getEmail()
    {
            return email;
    }

    public String getMobileNumber()
    {
            return mobileNumber;
    }

    public String getUsername()
    {
            return username;
    }

    public String getPassword()
    {
            return password;
    }

    public double getLatitude()
    {
            return latitude;
    }

    public double getLongitude()
    {
            return longitude;
    }

    public ArrayList<Preference> getUserPreferences()
    {
            return userPreferences;
    }

    public ArrayList<Integer> getUserPreferenceIds()
    {
        ArrayList<Integer> userPreferenceIds = new ArrayList<>(0);

        for( int i = 0; i < userPreferences.size(); i++ )
        {
            userPreferenceIds.add((Integer) userPreferences.get(i).getId());
        }

        return userPreferenceIds;
    }

    public void setUserId(String userId)
    {
            this.userId = userId;
    }

    public void setFirstName(String firstName)
    {
            this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
            this.lastName = lastName;
    }

    public void setEmail(String email)
    {
            this.email = email;
    }

    public void setMobileNumber(String mobileNumber)
    {
            this.mobileNumber = mobileNumber;
    }

    public void setUsername(String username)
    {
            this.username = username;
    }

    public void setPassword(String password)
    {
            this.password = password;
    }

    public void setLatitude(double latitude)
    {
            this.latitude = latitude;
    }

    public void setLongitude(double longitude)
    {
            this.longitude = longitude;
    }

    public void setUserPreferences(ArrayList<Preference> userPreferences)
    {
            this.userPreferences = userPreferences;
    }


    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags )
    {
        dest.writeString(userId);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(email);
        dest.writeString(mobileNumber);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeTypedList(userPreferences);
    }
}
