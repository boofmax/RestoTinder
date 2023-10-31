package com.mobdeve.s11.restotinder;

import com.mobdeve.s11.restotinder.R;

import java.util.ArrayList;

public class DataGenerator {
    public static ArrayList<RestaurantModel> loadData() {
        ArrayList<RestaurantModel> data = new ArrayList<RestaurantModel>();

        data.add(new RestaurantModel("Mang Inasal",
                "Taft Ave, Malate, Manila, 1004 Metro Manila",
                2,
                5,
                R.drawable.img1,
                false));
        data.add(new RestaurantModel("KFC",
                "1340 Taft Ave. Ermita Mla. ( KIOSK ) 8 PGH Cmpd Bgy 669 Zone 72, Ermita, Manila, 1006 Metro Manila",
                2,
                3,
                R.drawable.img2,
                false));
        data.add(new RestaurantModel("McDonalds",
                "2399 Taft Ave, Malate, Manila, 1004 Metro Manila",
                1,
                4,
                R.drawable.img3,
                false));
        data.add(new RestaurantModel("7-11",
                "2507 Taft Ave, Malate, Manila, 1004 Metro Manila",
                2,
                3,
                R.drawable.img4,
                false));
        data.add(new RestaurantModel("Jollibee",
                "HXCR+2QQ, San Andres Corner, Taft Ave, Malate, Manila, 1017 Metro Manila",
                1,
                4,
                R.drawable.img5,
                false));
        data.add(new RestaurantModel("Burger King",
                "1017 Taft Ave, Malate, Manila, 1017 Metro Manila",
                2,
                4,
                R.drawable.img6,
                false));
        data.add(new RestaurantModel("Kenny Rogers",
                "2576 Taft Ave, Malate, Manila, 1004 Metro Manila",
                3,
                4,
                R.drawable.img7,
                false));
        data.add(new RestaurantModel("Tropical Hut",
                "Ground Floor, Star City, Vicente Sotto St, Pasay, Metro Manila",
                1,
                5,
                R.drawable.img8,
                false));
        data.add(new RestaurantModel("Ate Rica's Bacsilog",
                "2305 Fidel A.Reyes, Malate, Manila, 1004 Metro Manila",
                2,
                2,
                R.drawable.img9,
                false));
        data.add(new RestaurantModel("Starbucks",
                "D' Students' Place Bldg., Taft Ave, corner Estrada St, Malate, Manila",
                3,
                4,
                R.drawable.img10,
                false));
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
