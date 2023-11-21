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

        username = intent.getStringExtra("uname").toString()
        val query = favoritesRef
            .whereEqualTo(MyFirestoreReferences.USER_FIELD, username)
        Log.d(TAG, "HERE $query")
        try {
            query.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    restaurants = ArrayList() // Initialize restaurants here
                    for (document in task.result!!) {
                        // Handle each matching document here
                        // document.data gives you the data of the document
                        Log.d(TAG, "Document Instance: $document")
                    }

                    // After initializing restaurants, set up the adapter
                    val adapterObject = MyAdapter(restaurants)
                    adapterObject.setUsername(username)

                    recyclerView.adapter = adapterObject
                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            }

        }
        catch (e: Exception) {
            Log.d(TAG, "NOMNOM")
        }

        recyclerView = findViewById(R.id.recyclerViewFavorites)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
    }
}
