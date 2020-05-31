package com.example.dotinternshiptest.data.remote.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Gallery implements Parcelable {
    private String caption;
    private String thumbnail;
    private String image;

    public Gallery(String caption, String thumbnail, String image) {
        this.caption = caption;
        this.thumbnail = thumbnail;
        this.image = image;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.caption);
        dest.writeString(this.thumbnail);
        dest.writeString(this.image);
    }

    protected Gallery(Parcel in) {
        this.caption = in.readString();
        this.thumbnail = in.readString();
        this.image = in.readString();
    }

    public static final Creator<Gallery> CREATOR = new Creator<Gallery>() {
        @Override
        public Gallery createFromParcel(Parcel source) {
            return new Gallery(source);
        }

        @Override
        public Gallery[] newArray(int size) {
            return new Gallery[size];
        }
    };
}
