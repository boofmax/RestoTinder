package com.mobdeve.s11.restotinder;

public class RestaurantModel {
    private String name, location, imageId, placeId, open;

    private double latitude, longitude;

    private double rating;
    private boolean isFavorite = false;


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

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getPricing() {
        return open;
    }

    public double getRating() {
        return rating;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getImageId() {
        return imageId;
    }

    public String getPlaceId() {
        return placeId;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void toggleFavorite() {
        isFavorite = !isFavorite;
    }

    @Override
    public String toString() {
        return "RestaurantModel{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", open='" + open + '\'' +
                ", rating='" + rating + '\'' +
                ", imageId=" + imageId +
                '}';
    }
}
