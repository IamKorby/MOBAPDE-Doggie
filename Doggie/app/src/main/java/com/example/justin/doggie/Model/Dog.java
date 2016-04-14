package com.example.justin.doggie.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Dog implements Parcelable
{
    private String dogId;
    private String userId;
    private String name;
    private String pictureURL;
    private String contact;
    private String location;
    private boolean hasPaper;
    private boolean isStray;
    private boolean isLost;
    private boolean isStud;
    private boolean isAdopt;

    public Dog()
    {

    }

    public Dog( String name, boolean isStray, String location, String contact)
    {
        this.name = name;
        this.isStray = isStray;
        this.location = location;
        this.contact = contact;

    }

    protected Dog( Parcel in )
    {
        dogId = in.readString();
        userId = in.readString();
        name = in.readString();
        pictureURL = in.readString();
        contact = in.readString();
        location = in.readString();
        hasPaper = in.readByte() != 0;
        isStray = in.readByte() != 0;
        isLost = in.readByte() != 0;
        isStud = in.readByte() != 0;
        isAdopt = in.readByte() != 0;
    }

    public static final Creator<Dog> CREATOR = new Creator<Dog>()
    {
        @Override
        public Dog createFromParcel( Parcel in )
        {
            return new Dog(in);
        }

        @Override
        public Dog[] newArray( int size )
        {
            return new Dog[size];
        }
    };

    public String getDogId()
    {
        return dogId;
    }

    public String getUserId()
    {
        return userId;
    }

    public String getName()
    {
        return name;
    }

    public String getPictureURL()
    {
        return pictureURL;
    }

    public String getContact()
    {
        return contact;
    }

    public String getLocation()
    {
        return location;
    }

    public boolean isHasPaper()
    {
        return hasPaper;
    }

    public boolean isStray()
    {
        return isStray;
    }

    public boolean isLost()
    {
        return isLost;
    }

    public boolean isStud()
    {
        return isStud;
    }

    public boolean isAdopt()
    {
        return isAdopt;
    }

    public void setDogId(String dogId)
    {
        this.dogId = dogId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPictureURL(String pictureURL)
    {
        this.pictureURL = pictureURL;
    }

    public void setContact(String contact)
    {
        this.contact = contact;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public void setHasPaper(boolean hasPaper)
    {
        this.hasPaper = hasPaper;
    }

    public void setStray(boolean isStray)
    {
        this.isStray = isStray;
    }

    public void setLost(boolean isLost)
    {
        this.isLost = isLost;
    }

    public void setStud(boolean isStud)
    {
        this.isStud = isStud;
    }

    public void setAdopt(boolean isAdopt)
    {
        this.isAdopt = isAdopt;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags )
    {
        dest.writeString(dogId);
        dest.writeString(userId);
        dest.writeString(name);
        dest.writeString(pictureURL);
        dest.writeString(contact);
        dest.writeString(location);
        dest.writeByte((byte) (hasPaper ? 1 : 0));
        dest.writeByte((byte) (isStray ? 1 : 0));
        dest.writeByte((byte) (isLost ? 1 : 0));
        dest.writeByte((byte) (isStud ? 1 : 0));
        dest.writeByte((byte) (isAdopt ? 1 : 0));
    }
}
