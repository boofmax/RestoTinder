package com.mobdeve.s11.restotinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s11.restotinder.R

class UserRatings : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_ratings)

        this.recyclerView = findViewById(R.id.rvRatings)
        var layoutManager = LinearLayoutManager(this)
        this.recyclerView.layoutManager = layoutManager
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        this.recyclerView.layoutManager = layoutManager
    }
}