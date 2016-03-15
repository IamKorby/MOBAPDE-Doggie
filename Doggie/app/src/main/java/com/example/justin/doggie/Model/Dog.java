package com.example.justin.doggie.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Angelo Amadora on 3/8/2016.
 */
public class Dog implements Parcelable
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

    protected Dog(Parcel in)
    {
        id = in.readInt();
        name = in.readString();
        isStray = in.readByte() != 0;
        mainPic = in.readInt();
        description = in.readString();
        location = in.readString();
        contact = in.readString();
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

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeByte((byte) (isStray ? 1 : 0));
        dest.writeInt(mainPic);
        dest.writeString(description);
        dest.writeString(location);
        dest.writeString(contact);
    }

    public static final Creator<Dog> CREATOR = new Creator<Dog>()
    {
        @Override
        public Dog createFromParcel(Parcel in)
        {
            return new Dog(in);
        }

        @Override
        public Dog[] newArray(int size)
        {
            return new Dog[size];
        }
    };
}
