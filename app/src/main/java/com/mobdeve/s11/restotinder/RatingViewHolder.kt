package com.mobdeve.s11.restotinder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s11.restotinder.R

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