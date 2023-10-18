package com.mobdeve.deculawan.practice_fakeadex_providedfiles

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

class RatingViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
    private val ratingUsername: TextView = itemView.findViewById(R.id.tvReviewUsername)
    private val ratingComment: TextView = itemView.findViewById(R.id.tvReviewComment)
    private val ratingRating: TextView = itemView.findViewById(R.id.tvRatingStars)

    fun bindData(character: RatingModel) {
        ratingUsername.text = character.username
        ratingComment.text = character.comment
        ratingRating.text = character.rating.toString()
    }
}