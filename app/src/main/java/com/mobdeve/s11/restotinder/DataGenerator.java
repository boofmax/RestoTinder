package com.mobdeve.s11.restotinder;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.google.maps.model.Photo;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import com.mobdeve.s11.restotinder.R;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class DataGenerator {
    public DataGenerator() throws IOException, InterruptedException, ApiException {
    }

    static DecimalFormat decimalFormat = new DecimalFormat("#.#");

    public static ArrayList<RestaurantModel> loadData(double curLatitude, double curLongitude) throws IOException, InterruptedException, ApiException {
        ArrayList<RestaurantModel> data = new ArrayList<RestaurantModel>();
        String icon = "https://cdn.vectorstock.com/i/preview-1x/65/30/default-image-icon-missing-picture-page-vector-40546530.jpg";
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyACiFUGdPCbbEHfCMXqtBa2RzuvOYAtptM")
                .build();

        LatLng location = new LatLng(curLatitude, curLongitude); // hard coded dlsu

        PlacesSearchResponse response = PlacesApi.nearbySearchQuery(context, location)
                .radius(1000)
                .type(PlaceType.RESTAURANT).await();

        for(
            PlacesSearchResult result : response.results){
            String name = result.name;
            String address = result.vicinity;
            String placeId = result.placeId;
            double rating = Double.parseDouble(decimalFormat.format(result.rating));
            double latitude = result.geometry.location.lat;
            double longitude = result.geometry.location.lng;
            if(result.photos != null) {
                icon = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" + result.photos[0].photoReference + "&key=" + "AIzaSyACiFUGdPCbbEHfCMXqtBa2RzuvOYAtptM";
            }
            else{
                icon = "https://cdn.vectorstock.com/i/preview-1x/65/30/default-image-icon-missing-picture-page-vector-40546530.jpg";
            }

            data.add(new RestaurantModel(name, address, 1, rating, icon, false, placeId, latitude, longitude));
        }
        return data;
    }
}
