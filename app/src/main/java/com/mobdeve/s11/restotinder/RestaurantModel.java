package com.mobdeve.s11.restotinder;

import android.os.Parcel;
import android.os.Parcelable;

public class RestaurantModel implements Parcelable {
    private String name, location, open, imageId, placeId;
    private double rating, latitude, longitude;
    private boolean isFavorite;

    public RestaurantModel(String name, String location, String open, double rating, String imageId, boolean isFavorite, String placeId, double latitude, double longitude) {
        this.name = name;
        this.location = location;
        this.open = open;
        this.rating = rating;
        this.imageId = imageId;
        this.isFavorite = isFavorite;
        this.placeId = placeId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public RestaurantModel(String imageId, boolean isFavorite, String location, String name, String status, double rating) {
        this.imageId = imageId;
        this.isFavorite = isFavorite;
        this.location = location;
        this.name = name;
        this.open = status;
        this.rating = rating;
        this.placeId = "";
        this.latitude = 0;
        this.longitude = 0;
    }

    protected RestaurantModel(Parcel in) {
        name = in.readString();
        location = in.readString();
        open = in.readString();
        rating = in.readDouble();
        imageId = in.readString();
        isFavorite = in.readByte() != 0;
        placeId = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Creator<RestaurantModel> CREATOR = new Creator<RestaurantModel>() {
        @Override
        public RestaurantModel createFromParcel(Parcel in) {
            return new RestaurantModel(in);
        }

        @Override
        public RestaurantModel[] newArray(int size) {
            return new RestaurantModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getOpen() {
        return open;
    }

    public double getRating() {
        return rating;
    }

    public String getImageId() {
        return imageId;
    }

    public String getPlaceId() {
        return placeId;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(open);
        dest.writeDouble(rating);
        dest.writeString(imageId);
        dest.writeByte((byte) (isFavorite ? 1 : 0));
        dest.writeString(placeId);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }
}
