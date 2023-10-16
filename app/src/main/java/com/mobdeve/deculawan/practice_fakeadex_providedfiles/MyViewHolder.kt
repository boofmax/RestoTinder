package com.mobdeve.deculawan.practice_fakeadex_providedfiles

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MyViewHolder(itemView: View): ViewHolder(itemView) {
    private val pokeImage: ImageView = itemView.findViewById(R.id.pokeImage)
    private val pokeName: TextView= itemView.findViewById(R.id.pokemon)
    private val pokeDescription: TextView= itemView.findViewById(R.id.pokeDescription)
    private val pokeSpecies: TextView= itemView.findViewById(R.id.pokeSpecies)
    private val pokeLocation: TextView= itemView.findViewById(R.id.pokeLocation)
    private val delButton: Button = itemView.findViewById(R.id.delButton)
    private val saveButton: Button = itemView.findViewById(R.id.saveButton)
        fun bindData(character: PokemonModel) {
            pokeImage.setImageResource(character.imageId)
            pokeName.text = character.name
            pokeDescription.text = character.desc
            pokeSpecies.text = character.specie
            pokeLocation.text = character.location
    }

    fun setDeleteOnClickListener(onClickListener: View.OnClickListener){
        delButton.setOnClickListener(onClickListener)
    }
}