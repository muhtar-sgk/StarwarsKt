package com.muhtar.starwarskt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val swapiApiService = ApiClient.swapiApiService
    private val swapiRepository = SwapiRepository(swapiApiService)

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> = _characters

    fun fetchCharacters() {
        viewModelScope.launch {
            try {
                val characters = swapiRepository.getCharacters().first()
                _characters.value = characters
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
