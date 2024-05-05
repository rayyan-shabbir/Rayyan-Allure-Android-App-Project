package com.example.rayyanallureapp.Activity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class PlaceModal implements Parcelable {
    String name, address;
    int img;

    public PlaceModal(String name, String address, int img) {
        this.name = name;
        this.address = address;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }


    public PlaceModal(String name, String address) {
        this.name = name;
        this.address = address;
    }

    protected PlaceModal(Parcel in) {
        name = in.readString();
        address = in.readString();
        img = in.readInt();
    }

    public static final Creator<PlaceModal> CREATOR = new Creator<PlaceModal>() {
        @Override
        public PlaceModal createFromParcel(Parcel in) {
            return new PlaceModal(in);
        }

        @Override
        public PlaceModal[] newArray(int size) {
            return new PlaceModal[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeInt(img);
    }
}
