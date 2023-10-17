package com.mobdeve.deculawan.practice_fakeadex_providedfiles

import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MyViewHolder(itemView: View): ViewHolder(itemView) {
    private val restaurantImage: ImageView = itemView.findViewById(R.id.ivRestaurantImage)
    private val restaurantName: TextView= itemView.findViewById(R.id.tvRestaurantName)
    private val restaurantLocation: TextView= itemView.findViewById(R.id.tvLocation)
    private val restaurantPricing: TextView= itemView.findViewById(R.id.tvPricing)
    private val restaurantRatings: TextView= itemView.findViewById(R.id.tvRatings)
    private val delButton: Button = itemView.findViewById(R.id.delButton)
    private val saveButton: Button = itemView.findViewById(R.id.saveButton)
    private val btnFavorite: ImageButton = itemView.findViewById(R.id.btnFavorite)

    var isFavorite: Boolean = false // default value
        fun bindData(character: RestaurantModel) {
            restaurantImage.setImageResource(character.imageId)
            restaurantName.text = character.name
            restaurantLocation.text = character.location
            restaurantPricing.text = character.pricing.toString()
            restaurantRatings.text = character.rating.toString()
    }

    fun setDeleteOnClickListener(onClickListener: View.OnClickListener){
        delButton.setOnClickListener(onClickListener)
    }

    fun setFavoriteOnClickListener(onClickListener: OnClickListener){
        btnFavorite.setOnClickListener {
        isFavorite = !isFavorite

        if (isFavorite){
            btnFavorite.setBackgroundResource(R.drawable.baseline_favorite_24)
        } else {
            btnFavorite.setBackgroundResource(R.drawable.baseline_favorite_border_24)
        }

        onClickListener.onClick(btnFavorite)
        }
    }
}