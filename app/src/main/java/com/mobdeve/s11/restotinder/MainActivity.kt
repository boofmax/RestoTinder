package com.mobdeve.s11.restotinder

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.mobdeve.s11.restotinder.DataGenerator
import com.mobdeve.s11.restotinder.MyAdapter
import com.mobdeve.s11.restotinder.R
import com.mobdeve.s11.restotinder.RestaurantModel


class MainActivity : AppCompatActivity() {
    private lateinit var restaurants: ArrayList<RestaurantModel>
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Request location permissions if not granted
        if (checkLocationPermissions()) {
            Log.d("Perms", "onCreate: Permission granted")
            loadRestaurants()
        }

        this.recyclerView = findViewById(R.id.recyclerView)
        // Initialize restaurants with an empty list to avoid null reference
        restaurants = loadRestaurants()
        recyclerView.adapter = MyAdapter(restaurants)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager

        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(this.recyclerView)

    }

    private fun loadRestaurants(): ArrayList<RestaurantModel> {
        // Initialize the location manager
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        // Check if location is enabled
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // Request the last known location
            val location: Location? = if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                null
            } else {
                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            }

            // Check if the location is not null
            if (location != null) {
                // Get latitude and longitude from the location
                val latitude = location.latitude
                val longitude = location.longitude

                // Call the loadData method with the current location
                try {
                    restaurants = DataGenerator.loadData(latitude, longitude)
                    Log.d("Restaurants", "loadRestaurants: $restaurants")
                    recyclerView.adapter = MyAdapter(restaurants)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return restaurants;
    }

    private fun checkLocationPermissions(): Boolean {
        val fineLocationPermission =
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val coarseLocationPermission =
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)

        if (fineLocationPermission != PackageManager.PERMISSION_GRANTED ||
            coarseLocationPermission != PackageManager.PERMISSION_GRANTED
        ) {
            // Request location permissions
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return false
        }
        return true
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
}