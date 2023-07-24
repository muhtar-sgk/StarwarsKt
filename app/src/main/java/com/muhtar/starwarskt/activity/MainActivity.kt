package com.muhtar.starwarskt.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.muhtar.starwarskt.R
import com.muhtar.starwarskt.adapter.CharacterAdapter
import com.muhtar.starwarskt.api.SwapiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var characterAdapter: CharacterAdapter
    private var characterList: MutableList<com.muhtar.starwarskt.model.Character> = mutableListOf()
    private var currentPage = 1
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewCharacters)
        recyclerView.layoutManager = LinearLayoutManager(this)
        characterAdapter = CharacterAdapter(characterList)
        recyclerView.adapter = characterAdapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (!isLoading && totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD)) {
                    loadMoreCharacters()
                }
            }
        })

        fetchData()
    }

    private fun fetchData() {
        isLoading = true
        val swapiService = Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SwapiService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = swapiService.getCharacters(currentPage)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val characterResponse = response.body()
                        characterResponse?.let {
                            characterList.addAll(it.results)
                            characterAdapter.notifyDataSetChanged()
                            currentPage++
                        }
                    } else {
                        // Handle error
                    }
                    isLoading = false
                }
            } catch (e: Exception) {
                // Handle exception
                isLoading = false
            }
        }
    }

    private fun loadMoreCharacters() {
        fetchData()
    }

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }
}
