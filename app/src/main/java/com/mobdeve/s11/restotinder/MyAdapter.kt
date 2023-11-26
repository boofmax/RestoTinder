package com.mobdeve.s11.restotinder

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s11.restotinder.R

class MyAdapter(private val data: ArrayList<RestaurantModel>): RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_layout, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(data.get(position))

        holder.setDeleteOnClickListener(View.OnClickListener {
            holder.itemView.setOnClickListener{
                Toast.makeText(holder.itemView.context, "Restaurant Deleted: "+
                        data[holder.adapterPosition].name, Toast.LENGTH_SHORT).show()
            }

            this.data.removeAt(position)
            notifyDataSetChanged()
        })

        holder.setFavoriteOnClickListener(View.OnClickListener {
            if(holder.isFavorite){
                Toast.makeText(
                    holder.itemView.context,
                    "Restaurant Removed from Favorites: " + data[holder.adapterPosition].name,
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    holder.itemView.context,
                    "Restaurant Added to Favorites: " + data[holder.adapterPosition].name,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        holder.setRatingOnClickListener(View.OnClickListener {
            val placeName = data[holder.adapterPosition].name

            // Check if Google Maps is installed on the device
            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$placeName"))
            mapIntent.setPackage("com.google.android.apps.maps")

            if (mapIntent.resolveActivity(holder.itemView.context.packageManager) != null) {
                holder.itemView.context.startActivity(mapIntent)
            } else {
                Toast.makeText(
                    holder.itemView.context,
                    "Google Maps is not installed on your device.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        holder.setMapOnClickListener(View.OnClickListener {
            val intent = Intent(holder.itemView.context, MapsActivity::class.java)
            intent.putExtra("restaurantLatitude", data[position].latitude)
            intent.putExtra("restaurantLongitude", data[position].longitude)
            holder.itemView.context.startActivity(intent)
        }, data[position])

    }

}