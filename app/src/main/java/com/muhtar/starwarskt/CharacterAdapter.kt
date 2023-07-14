package com.muhtar.starwarskt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CharacterAdapter(private val characters: List<Character>) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.textViewName)
        private val heightTextView: TextView = itemView.findViewById(R.id.textViewHeight)
        private val massTextView: TextView = itemView.findViewById(R.id.textViewMass)
        private val hairColorTextView: TextView = itemView.findViewById(R.id.textViewHairColor)

        fun bind(character: Character) {
            nameTextView.text = character.name
            heightTextView.text = "Height: ${character.height}"
            massTextView.text = "Mass: ${character.mass}"
            hairColorTextView.text = "Hair Color: ${character.hairColor}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int {
        return characters.size
    }
}
