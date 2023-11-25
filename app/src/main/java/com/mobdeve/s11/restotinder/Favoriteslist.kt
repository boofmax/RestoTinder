package com.mobdeve.s11.restotinder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class FavoritesList : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var username: String

    private val FAVORITES_LIST_REQUEST_CODE = 100
    private lateinit var dbRef: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_favorites)

        dbRef = Firebase.firestore
        val favoritesRef = dbRef.collection(MyFirestoreReferences.FAVORITE_COLLECTION)
        username = intent.getStringExtra("FavoriteUname").toString()

        // Set up the RecyclerView, layout manager, and SnapHelper
        this.recyclerView = findViewById(R.id.recyclerViewFavorites)

        // Load favorites data using coroutine
        lifecycleScope.launch {
            // Inside the coroutine, you can call your suspend function
            val favoritedRestos = loadFavorites(favoritesRef)
            Log.d(TAG, "$username NOMNOM IT ENTERED FAVORITES BOI \t" + favoritedRestos.joinToString("\n"))

            // Initialize the adapter with the loaded data
            val adapterObject = MyAdapter(favoritedRestos)
            adapterObject.setUsername(username)
            recyclerView.adapter = adapterObject
        }

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager

        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
    }

    private suspend fun loadFavorites(favoritesRef: CollectionReference): ArrayList<RestaurantModel> {
        val restaurants = ArrayList<RestaurantModel>()

        try {
            val result = favoritesRef.get().await()

            for (document in result) {
                if (document.getString("username") == username) {
                    Log.d("IT ENTERED FAVORITES BOI", "${document.id} => ${document.data}")
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
            Log.d("LOGGINGSTUF", "Ended")
        } catch (e: Exception) {
            Log.e("FirestoreError", "Error loading favorites", e)
        }
        return restaurants
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == FAVORITES_LIST_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // FavoritesList has finished loading data
                Log.d(TAG, "FavoritesList has finished loading data.")
            }
        }
    }

    companion object {
        private const val TAG = "FavoritesList"
    }
}
