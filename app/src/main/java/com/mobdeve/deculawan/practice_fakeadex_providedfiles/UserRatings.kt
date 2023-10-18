package com.mobdeve.deculawan.practice_fakeadex_providedfiles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UserRatings : AppCompatActivity() {
    private val ratings: ArrayList<RatingModel> = DataGenerator.loadRatingData()
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_ratings)

        this.recyclerView = findViewById(R.id.rvRatings)
        this.recyclerView.adapter = RatingAdapter(this.ratings)
        var layoutManager = LinearLayoutManager(this)
        this.recyclerView.layoutManager = layoutManager
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        this.recyclerView.layoutManager = layoutManager
    }
}