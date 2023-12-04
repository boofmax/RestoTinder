package com.mobdeve.s11.restotinder

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mobdeve.s11.restotinder.R
import com.bumptech.glide.Glide
class MyViewHolder(itemView: View): ViewHolder(itemView) {

    companion object {
        private const val TAG = "MyViewHolder"
    }
    private val restaurantImage: ImageView = itemView.findViewById(R.id.ivRestaurantImage)
    private val restaurantName: TextView= itemView.findViewById(R.id.tvRestaurantName)
    private val restaurantLocation: TextView= itemView.findViewById(R.id.tvLocation)
    private val restaurantOpenHours: TextView= itemView.findViewById(R.id.tvOpenHours)
    private val restaurantRatings: TextView= itemView.findViewById(R.id.tvRatings)
    private val delButton: ImageButton = itemView.findViewById(R.id.delButton)
    private val btnFavorite: ImageButton = itemView.findViewById(R.id.btnFavorite)
    private val mapButton: ImageButton = itemView.findViewById(R.id.mapButton)
    private val linearLayoutRating : LinearLayout = itemView.findViewById(R.id.linearLayoutRating)
    private val profileButton: ImageButton = itemView.findViewById(R.id.btnProfile)

    var isFavorite: Boolean = false // default value

    fun bindData(character: RestaurantModel) {
        Glide.with(itemView.context)
            .load(character.imageId) // Replace 'character.imageUrl' with the URL string
            .into(restaurantImage)
        restaurantName.text = character.name
        restaurantLocation.text = character.location
        restaurantOpenHours.text = character.open.toString()
        restaurantRatings.text = character.rating.toString().plus(" / 5.0")
        Log.d(TAG, "DETAILS: "+ character.name)
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

    fun setRatingOnClickListener(onClickListener: View.OnClickListener) {
        linearLayoutRating.setOnClickListener(onClickListener)
    }

    fun setMapOnClickListener(onClickListener: View.OnClickListener, restaurantModel: RestaurantModel) {
        mapButton.setOnClickListener {

            val intent = Intent(itemView.context, MapsActivity::class.java)
            intent.putExtra("restaurantLatitude", restaurantModel.latitude)
            intent.putExtra("restaurantLongitude", restaurantModel.longitude)
            itemView.context.startActivity(intent)
        }
    }

    fun setProfileOnClickListener(onClickListener: View.OnClickListener){
        profileButton.setOnClickListener(onClickListener)
    }

}