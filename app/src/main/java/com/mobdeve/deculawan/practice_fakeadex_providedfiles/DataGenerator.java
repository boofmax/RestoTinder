package com.mobdeve.deculawan.practice_fakeadex_providedfiles;

// your package here

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
}
