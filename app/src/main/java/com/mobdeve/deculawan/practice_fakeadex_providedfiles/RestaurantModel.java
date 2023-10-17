package com.mobdeve.deculawan.practice_fakeadex_providedfiles;

public class RestaurantModel {
    private String name, location;
    private int pricing, rating, imageId;

    public RestaurantModel(String name, String location, int pricing, int rating, int imageId) {
        this.name = name;
        this.location = location;
        this.pricing = pricing;
        this.rating = rating;
        this.imageId = imageId;
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
