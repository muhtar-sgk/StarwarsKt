package com.muhtar.starwarskt

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://swapi.dev/api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val swapiApiService: SwapiApiService by lazy {
        retrofit.create(SwapiApiService::class.java)
    }
}
