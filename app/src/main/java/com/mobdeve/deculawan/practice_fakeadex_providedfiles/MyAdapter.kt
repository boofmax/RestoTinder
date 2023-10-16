package com.mobdeve.deculawan.practice_fakeadex_providedfiles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class MyAdapter(private val data: ArrayList<PokemonModel>): RecyclerView.Adapter<MyViewHolder>() {
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
                Toast.makeText(holder.itemView.context, "Pokemon Deleted: "+
                data[holder.adapterPosition].name, Toast.LENGTH_SHORT).show()
            }

            this.data.removeAt(position)
            notifyDataSetChanged()
        })
    }

}