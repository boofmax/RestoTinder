package com.mobdeve.deculawan.practice_fakeadex_providedfiles

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

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
            val intent = Intent(holder.itemView.context, RatingActivity::class.java)
            intent.putExtra("restaurantName", data[holder.adapterPosition].name)
            intent.putExtra("restaurantRating", data[holder.adapterPosition].rating)
            holder.itemView.context.startActivity(intent)
        })
    }

}