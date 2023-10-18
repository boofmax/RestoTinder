package com.mobdeve.deculawan.practice_fakeadex_providedfiles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RatingAdapter(private val data: ArrayList<RatingModel>) : RecyclerView.Adapter<RatingViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_layout, parent, false)

        return RatingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        holder.bindData(data.get(position))
    }
}