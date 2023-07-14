package com.muhtar.starwarskt

import retrofit2.Response
import retrofit2.http.GET

interface SwapiApiService {
    @GET("people")
    suspend fun getCharacters(): Response<CharacterResponse>
}
