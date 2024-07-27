package com.example.wallpaper.retroFit

import com.example.wallpaper.data.PexelsApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.pexels.com/v1/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: PexelsApiService by lazy {
        retrofit.create(PexelsApiService::class.java)
    }
}
