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
import java.util.ArrayList;


public class DataGenerator {
    public DataGenerator() throws IOException, InterruptedException, ApiException {
    }

    public static ArrayList<RestaurantModel> loadData() throws IOException, InterruptedException, ApiException {
        ArrayList<RestaurantModel> data = new ArrayList<RestaurantModel>();
        String icon = "https://cdn.vectorstock.com/i/preview-1x/65/30/default-image-icon-missing-picture-page-vector-40546530.jpg";
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyACiFUGdPCbbEHfCMXqtBa2RzuvOYAtptM")
                .build();

        LatLng location = new LatLng(14.56593840313714, 120.9929982308952); // hard coded dlsu

        PlacesSearchResponse response = PlacesApi.nearbySearchQuery(context, location)
                .radius(1000)
                .type(PlaceType.RESTAURANT).await();

        for(
                PlacesSearchResult result : response.results){
            String name = result.name;
            String address = result.vicinity;
            String placeId = result.placeId;
            double rating = result.rating;
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

    public static ArrayList<RatingModel> loadRatingData() {
        ArrayList<RatingModel> ratings = new ArrayList<RatingModel>();
        ratings.add(new RatingModel("LunaSkyWatcher", "I had the pleasure of dining at this restaurant, and it was an unforgettable experience. From the moment I stepped in, I was transported to a world of culinary excellence. The chef's expertise was evident in every dish I had the privilege of trying. The flavors were an intricate dance on my palate, and the presentation was akin to a work of art. The attentive and knowledgeable staff further enhanced the overall experience. They offered insightful recommendations, ensuring I had the perfect wine pairing for my meal. The ambiance was inviting and sophisticated, creating an atmosphere where time seemed to stand still, allowing me to savor every moment. Without a doubt, this restaurant is deserving of a full 5-star rating.", 5));
        ratings.add(new RatingModel("CosmicExplorer777", "My recent visit to this establishment was decent but didn't quite reach the level of exceptional I was hoping for. While the food was certainly good, it fell a bit short of my high expectations. The ambiance was pleasant, with a warm and welcoming interior. The service was attentive, but the overall experience was more 'good' than 'outstanding.' I'd rate it 3 stars as it met the mark but didn't exceed it.", 3));
        ratings.add(new RatingModel("StarryDreamer", "The standout aspect of my visit was undoubtedly the impeccable service. The staff's friendliness and attentiveness stood out, making me feel like a valued guest. They went above and beyond to ensure my experience was memorable. However, the food, while good, didn't quite reach excellence. The dishes were flavorful, but I was hoping for something truly exceptional. A solid 4 out of 5 from me, mainly due to the outstanding service.", 4));
        ratings.add(new RatingModel("WanderlustVoyager", "I have to admit, I deeply regret choosing this restaurant. The quality of the food left much to be desired, and I wouldn't recommend this place to anyone. The flavors were bland, and the presentation was uninspiring. It was a truly disappointing experience, and I can only give it 2 stars as it's difficult to find any positives in this visit.", 2));
        ratings.add(new RatingModel("AstronomyEnthusiast", "My dining experience at this restaurant was nothing short of spectacular. I can't praise it enough! The flavors were an exquisite symphony of taste, and the presentation was like a work of art on the plate. The ambiance was cozy and inviting, creating the perfect setting for a memorable meal. The service was exceptional, with the staff going above and beyond to cater to every need. I left with a smile on my face and the satisfaction of an unforgettable dining experience. Without a doubt, it deserves a well-earned 5-star rating!", 5));
        ratings.add(new RatingModel("AdventurousFoodie2023", "While my visit here had its share of positives, there is room for improvement. The food was good, and the ambiance was pleasant, but there are areas where they could enhance the overall experience. Perhaps a more diverse menu or a bit of innovation in their dishes would make a difference. I'd rate it 3 out of 5 for the potential for growth and improvement.", 3));
        ratings.add(new RatingModel("EclipseChaserExplorer", "This place is a hidden gem! I had an incredible time here. The food was out of this world, with each dish a delightful surprise. The service was top-notch, and the staff's knowledge about the menu was impressive. The ambiance was simply breathtaking, adding to the overall experience. It undoubtedly deserves a perfect 5-star rating, and I can't wait to return for another culinary adventure!", 5));
        ratings.add(new RatingModel("MountainWanderer42", "The food at this establishment was good, but it didn't leave a lasting impression. It's a suitable place for a quick meal or a casual dine-out, but nothing extraordinary that sets it apart. The simplicity and efficiency are its strengths. I'd give it 3 stars for its reliability and convenience.", 3));
        ratings.add(new RatingModel("FriendlyNomad99", "The staff at this restaurant was incredibly friendly and welcoming, which added greatly to the positive dining experience. Their warm hospitality made me feel right at home, and their recommendations were spot on. The service was a highlight, and the staff's knowledge about the menu was impressive. I'd give it a solid 4-star rating for the exceptional service and the overall friendly atmosphere.", 4));
        ratings.add(new RatingModel("SpeedyTraveler18", "If you're in a hurry, this place is the perfect choice for a quick meal. The speed of their service is truly impressive, and they deliver on their promise of efficiency. The menu may not be extensive, but it gets the job done when you're looking for a swift dining experience. I'd rate it 4 stars for their quick and reliable service, making it a go-to spot for busy travelers.", 4));
        return ratings;
    }
}
