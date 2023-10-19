package com.mobdeve.deculawan.practice_fakeadex_providedfiles;

public class RestaurantModel {
    private String name, location;
    private int pricing, rating, imageId;
    private boolean isFavorite = false;

    public RestaurantModel(String name, String location, int pricing, int rating, int imageId, boolean isFavorite) {
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

    public int getRating() {
        return rating;
    }

    public int getImageId() {
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
