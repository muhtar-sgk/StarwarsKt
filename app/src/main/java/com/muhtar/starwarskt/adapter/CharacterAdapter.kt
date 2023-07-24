package com.muhtar.starwarskt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.muhtar.starwarskt.R
import com.muhtar.starwarskt.model.Character

class CharacterAdapter(private val characterList: List<Character>) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characterList[position]
        holder.bind(character)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val heightTextView: TextView = itemView.findViewById(R.id.heightTextView)
        private val massTextView: TextView = itemView.findViewById(R.id.massTextView)

        fun bind(character: Character) {
            nameTextView.text = character.name
            heightTextView.text = character.height
            massTextView.text = character.mass
        }
    }
}
