package com.mobdeve.s11.restotinder;

import android.media.Image;

import com.google.maps.model.Photo;

public class RestaurantModel {
    private String name, location, imageId;
    private int pricing;


    private double rating;
    private boolean isFavorite = false;


    public RestaurantModel(String name, String location, int pricing, double rating, String imageId, boolean isFavorite) {

        this.name = name;
        this.location = location;
        this.pricing = pricing;
        this.rating = rating;
        this.imageId = imageId;
        this.isFavorite = isFavorite;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getPricing() {
        return pricing;
    }

    public double getRating() {
        return rating;
    }

    public String getImageId() {
        return imageId;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void toggleFavorite() {
        isFavorite = !isFavorite;
    }

    @Override
    public String toString() {
        return "PokemonModel{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", pricing='" + pricing + '\'' +
                ", rating='" + rating + '\'' +
                ", imageId=" + imageId +
                '}';
    }
}
