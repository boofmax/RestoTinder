package com.mobdeve.s11.restotinder

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mobdeve.s11.restotinder.DataGenerator
import com.mobdeve.s11.restotinder.MyAdapter
import com.mobdeve.s11.restotinder.R
import com.mobdeve.s11.restotinder.RestaurantModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class MainActivity : AppCompatActivity() {
    private lateinit var restaurants: ArrayList<RestaurantModel>
    private lateinit var recyclerView: RecyclerView

    private lateinit var username : String

    private val FAVORITES_LIST_REQUEST_CODE = 100
    private lateinit var dbRef: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbRef = Firebase.firestore
        val favoritesRef = dbRef.collection(MyFirestoreReferences.FAVORITE_COLLECTION)
        Log.d("MainActivity", "Favorites Ref: $favoritesRef")
        username = intent.getStringExtra("uname").toString()

        //var favoritedRestos = loadFavoritesBlocking(favoritesRef)
        // Initialize restaurants with an empty list to avoid null reference
        restaurants = ArrayList()



        // Request location permissions if not granted
        if (checkLocationPermissions()) {
            loadRestaurants()
            // Load restaurants data with startActivityForResult
        }

        this.recyclerView = findViewById(R.id.recyclerView)

/*
        // Initialize restaurants with an empty list to avoid null reference
        restaurants = loadRestaurants()
        var adapterObject  = MyAdapter(restaurants)
        adapterObject.setUsername(username)
        //adapterObject.setFavoriteRestos(favoritedRestos)

        this.recyclerView.adapter = adapterObject
*/
        lifecycleScope.launch {
            // Inside the coroutine, you can call your suspend function
            val favoritedRestos = loadFavorites(favoritesRef)
            Log.d("test", "$username favorites \t" + favoritedRestos.joinToString("\n"))

            // Use the loaded data to update UI or perform any necessary actions
            // For example:
            val adapterObject = MyAdapter(restaurants)
            adapterObject.setUsername(username)
            adapterObject.setFavoriteRestos(favoritedRestos)
            recyclerView.adapter = adapterObject

            adapterObject.notifyDataSetChanged()
        }

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager

        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(this.recyclerView)
    }

    private suspend fun loadFavorites(favoritesRef: CollectionReference): ArrayList<RestaurantModel> {
        val restaurants = ArrayList<RestaurantModel>()
        Log.d("LOGGINGSTUF", "Entered")

        try {
            Log.d("Rec view", "Before recview")
            val result = favoritesRef.get().await()

            Log.d("Rec view", "After recview")
            for (document in result) {
                if (document.getString("username") == username) {
                    Log.d("IT ENTERED MY BOI", "${document.id} => ${document.data}")
                    val isFavorite = document.getBoolean(MyFirestoreReferences.isFavorite_FIELD) ?: false
                    val imageId = document.getString(MyFirestoreReferences.imageId_FIELD)
                    val location = document.getString(MyFirestoreReferences.location_FIELD)
                    val name = document.getString(MyFirestoreReferences.name_FIELD)
                    val pricing = document.get(MyFirestoreReferences.pricing_FIELD)?.toString()
                    val rating = document.getDouble(MyFirestoreReferences.rating_FIELD) ?: 0.0

                    val resto = RestaurantModel(imageId, isFavorite, location, name, pricing, rating)
                    restaurants.add(resto)
                }
            }
        } catch (e: Exception) {
            Log.e("FirestoreError", "Error loading favorites", e)
        }

        return restaurants
    }


    private fun loadRestaurants() {
        Log.d("LoadRestaurants", "loadRestaurants: First")

        // Initialize the location manager
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        // Check if location is enabled
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // Check if location permissions are granted
            if (checkLocationPermissions()) {
                // Request a single location update
                try {
                    locationManager.requestSingleUpdate(
                        LocationManager.GPS_PROVIDER,
                        object : android.location.LocationListener {
                            override fun onLocationChanged(location: Location) {
                                // Get latitude and longitude from the location
                                val latitude = location.latitude
                                val longitude = location.longitude

                                // Call the loadData method with the current location
                                try {
                                    restaurants = DataGenerator.loadData(latitude, longitude)
                                    Log.d("Restaurants", "loadRestaurants: $restaurants")
                                    recyclerView.adapter = MyAdapter(restaurants)
                                } catch (e: Exception) {
                                    Log.d("Restaurants", "loadRestaurants: $e")
                                    e.printStackTrace()
                                }
                            }

                            override fun onStatusChanged(
                                provider: String?,
                                status: Int,
                                extras: Bundle?
                            ) {
                            }

                            override fun onProviderEnabled(provider: String) {
                            }

                            override fun onProviderDisabled(provider: String) {
                            }
                        },
                        null
                    )
                } catch (e: SecurityException) {
                    e.printStackTrace()
                }
            }
        } else {
            // Handle the case when GPS is not enabled
            buildAlertMessageNoGPS()
        }
    }

    private fun checkLocationPermissions(): Boolean {

        if(!isLocationEnabled()) {
            Log.d("Perms", "checkLocationPermissions: Location not enabled")
            buildAlertMessageNoGPS()
        }

        val fineLocationPermission =
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val coarseLocationPermission =
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)

        if (fineLocationPermission != PackageManager.PERMISSION_GRANTED ||
            coarseLocationPermission != PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("Perms", "checkLocationPermissions: Permission not granted")
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
        Log.d("Perms", "checkLocationPermissions: Permission granted")
        return true
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun buildAlertMessageNoGPS() {
        val builder = android.app.AlertDialog.Builder(this)
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
            .setCancelable(false)
            .setPositiveButton(
                "Yes"
            ) { _, _ -> startActivity(Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)) }
            .setNegativeButton(
                "No"
            ) { dialog, _ -> dialog.cancel() }
        val alert = builder.create()
        alert.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == FAVORITES_LIST_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // FavoritesList has finished loading data
                Log.d("MainActivity", "FavoritesList has finished loading data.")
            }
        }
    }
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
}
