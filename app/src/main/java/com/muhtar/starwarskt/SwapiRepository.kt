package com.muhtar.starwarskt

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SwapiRepository(private val swapiApiService: SwapiApiService) {
    fun getCharacters(): Flow<List<Character>> = flow {
        val response = swapiApiService.getCharacters()
        if (response.isSuccessful) {
            val characters = response.body()?.results
            emit(characters ?: emptyList())
        } else {
            throw Exception("Failed to fetch characters")
        }
    }
}


