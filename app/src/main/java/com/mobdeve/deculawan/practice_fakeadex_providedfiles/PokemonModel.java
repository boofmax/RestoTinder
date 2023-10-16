package com.mobdeve.deculawan.practice_fakeadex_providedfiles;

public class PokemonModel {
    private String name, specie, location, desc;
    private int imageId;

    public PokemonModel(String name, String specie, String location, String desc, int imageId) {
        this.name = name;
        this.specie = specie;
        this.location = location;
        this.desc = desc;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public String getSpecie() {
        return specie;
    }

    public String getLocation() {
        return location;
    }

    public String getDesc() {
        return desc;
    }

    public int getImageId() {
        return imageId;
    }

    @Override
    public String toString() {
        return "PokemonModel{" +
                "name='" + name + '\'' +
                ", specie='" + specie + '\'' +
                ", location='" + location + '\'' +
                ", desc='" + desc + '\'' +
                ", imageId=" + imageId +
                '}';
    }
}
