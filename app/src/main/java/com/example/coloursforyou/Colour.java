package com.example.coloursforyou;

import android.os.Parcel;
import android.os.Parcelable;

public class Colour implements Parcelable {
    private String hex;
    private String name;

    Colour(String aHex, String aName) {
        hex = aHex;
        name = aName.substring(0, 1).toUpperCase() + aName.substring(1);
    }

    private Colour(Parcel parcel) {
        hex = parcel.readString();
        name = parcel.readString();
    }

    String getHex() {
        return hex;
    }

    String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hex);
        dest.writeString(name);
    }

    public static Creator<Colour> CREATOR = new Creator<Colour>() {

        @Override
        public Colour createFromParcel(Parcel source) {
            return new Colour(source);
        }

        @Override
        public Colour[] newArray(int size) {
            return new Colour[size];
        }

    };
}
