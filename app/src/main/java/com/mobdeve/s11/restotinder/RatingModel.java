package com.mobdeve.s11.restotinder;

public class RatingModel {
    private String username, comment;
    private int rating;

    public RatingModel(String username, String comment, int rating) {
        this.username = username;
        this.comment = comment;
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public String getComment() {
        return comment;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "RatingModel{" +
                "username='" + username + '\'' +
                ", comment='" + comment + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
