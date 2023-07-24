package com.muhtar.starwarskt.api

import com.muhtar.starwarskt.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SwapiService {
    @GET("people/")
    suspend fun getCharacters(@Query("page") page: Int): Response<CharacterResponse>
}
