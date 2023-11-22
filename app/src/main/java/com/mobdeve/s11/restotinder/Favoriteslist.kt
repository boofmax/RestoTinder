package com.mobdeve.s11.restotinder

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FavoritesList : AppCompatActivity() {
    companion object {
        private const val TAG = "FavoritesList"
    }

    // Load data
    private lateinit var restaurants: ArrayList<RestaurantModel>

    private lateinit var dbRef: FirebaseFirestore

    // Our RecyclerView
    private lateinit var recyclerView: RecyclerView
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_favorites)

        dbRef = Firebase.firestore
        val favoritesRef = dbRef.collection(MyFirestoreReferences.FAVORITE_COLLECTION)
        val username = intent.getStringExtra("uname").toString()
        Log.d("LOGGINGSTUF", "Entered")
        favoritesRef
            .get()
            .addOnSuccessListener { result ->
                restaurants = ArrayList()
                for (document in result) {
                    if (document.getString("username") == username) {
                        Log.d("TEST WORK", "${document.id} => ${document.data}")
                        val resto = document.getString(MyFirestoreReferences.rating_FIELD)?.let {
                            RestaurantModel(
                                document.getString(MyFirestoreReferences.imageId_FIELD),
                                document.getString(MyFirestoreReferences.isFavorite_FIELD).toBoolean(),
                                document.getString(MyFirestoreReferences.location_FIELD),
                                document.getString(MyFirestoreReferences.name_FIELD),
                                document.getString(MyFirestoreReferences.pricing_FIELD),
                                it.toDouble()
                            )
                        }
                        if (resto != null) {
                            restaurants.add(resto)
                        }
                    }
                }

                Log.d("LOGGINGSTUF", "Left")
                Log.d(TAG, "Restaurant Stuf to Display: " + restaurants.joinToString("\n"))

                recyclerView = findViewById(R.id.recyclerViewFavorites)

                val layoutManager = LinearLayoutManager(this)
                layoutManager.orientation = LinearLayoutManager.HORIZONTAL
                recyclerView.layoutManager = layoutManager
                val snapHelper: SnapHelper = PagerSnapHelper()
                snapHelper.attachToRecyclerView(recyclerView)

                // Now you can initialize your adapter and set it to the RecyclerView
                val adapterObject = MyAdapter(restaurants)
                adapterObject.setUsername(username)
                recyclerView.adapter = adapterObject
            }
    }
}
