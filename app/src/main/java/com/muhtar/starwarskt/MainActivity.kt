package com.muhtar.starwarskt

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CharacterAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewCharacters)
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = MainViewModel()

        viewModel.characters.observe(this, Observer { characters ->
            adapter = CharacterAdapter(characters)
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        })

        viewModel.fetchCharacters()
    }
}