package com.mobdeve.s11.restotinder

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mobdeve.s11.restotinder.R
import com.mobdeve.s11.restotinder.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private var restaurantLatitude: Double = 14.5648 // default dlsu
    private var restaurantLongitude: Double = 120.9932 // default dlsu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide() // remove action bar

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val restaurantLatitude = intent.getDoubleExtra("restaurantLatitude", 14.5648)
        val restaurantLongitude = intent.getDoubleExtra("restaurantLongitude", 120.9932)
        mMap.isMyLocationEnabled = true // enable location tracking
        mMap.uiSettings.isMyLocationButtonEnabled = true // enable location button
        val restaurantLocation = LatLng(restaurantLatitude, restaurantLongitude)
        val zoomLevel = 15.0f  // Adjust this value to control the zoom level

        // Add marker
        mMap.addMarker(MarkerOptions().position(restaurantLocation).title("Restaurant"))

        // Center camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(restaurantLocation))

        // Fix zoom
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(restaurantLocation, zoomLevel))
    }
}
