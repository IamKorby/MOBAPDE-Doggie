package com.example.justin.doggie.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Preference implements Parcelable
{
    private int id;
    private String preference;

    public Preference(int id, String preference)
    {
            this.id = id;
            this.preference = preference;
    }

    protected Preference( Parcel in )
    {
        id = in.readInt();
        preference = in.readString();
    }

    public static final Creator<Preference> CREATOR = new Creator<Preference>()
    {
        @Override
        public Preference createFromParcel( Parcel in )
        {
            return new Preference(in);
        }

        @Override
        public Preference[] newArray( int size )
        {
            return new Preference[size];
        }
    };

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

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags )
    {
        dest.writeInt(id);
        dest.writeString(preference);
    }
}
