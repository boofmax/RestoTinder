package com.mobdeve.deculawan.practice_fakeadex_providedfiles

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MyViewHolder(itemView: View): ViewHolder(itemView) {
    private val restaurantImage: ImageView = itemView.findViewById(R.id.ivRestaurantImage)
    private val restaurantName: TextView= itemView.findViewById(R.id.tvRestaurantName)
    private val restaurantLocation: TextView= itemView.findViewById(R.id.tvLocation)
    private val restaurantPricing: TextView= itemView.findViewById(R.id.tvPricing)
    private val restaurantRatings: TextView= itemView.findViewById(R.id.tvRatings)
    private val delButton: Button = itemView.findViewById(R.id.delButton)
    private val saveButton: Button = itemView.findViewById(R.id.saveButton)
    private val favoriteButton: FloatingActionButton = itemView.findViewById(R.id.fab)



    fun bindData(character: RestaurantModel) {
        restaurantImage.setImageResource(character.imageId)
        restaurantName.text = character.name
        restaurantLocation.text = character.location
        restaurantPricing.text = character.pricing.toString()
        restaurantRatings.text = character.rating.toString()

        // Check the favorite status and set the appropriate image
        favoriteButton.setOnClickListener {
            character.toggleFavorite() // Toggle the favorite status
            val favoriteImageResource = if (character.isFavorite) {
                R.drawable.baseline_favorite_24
            } else {
                R.drawable.baseline_favorite_border_24
            }
            favoriteButton.setImageResource(favoriteImageResource)
        }
    }

    fun setDeleteOnClickListener(onClickListener: View.OnClickListener){
        delButton.setOnClickListener(onClickListener)
    }

}