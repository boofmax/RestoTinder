package com.mobdeve.s11.restotinder

import android.os.Bundle
import android.os.Parcelable
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

    private lateinit var favoriteRestos: ArrayList<RestaurantModel>

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

        /*
       * TODO:
       *  1. Retrieve favorites from MyAadapter
       *  2. Display the list of favorites of the user
       * */
        //favoriteRestos = intent.getSerializableExtra("favoriteRestos") as ArrayList<RestaurantModel> // ISSUE: Depracated
        //Log.d(TAG, "Received Restaurants: " + this.favoriteRestos?.joinToString("\n"))
    }
}
