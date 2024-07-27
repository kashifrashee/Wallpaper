package com.example.wallpaper.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PexelsApiService {
    @GET("search")
    suspend fun getPhotos(
        @Query("query") query: String,
        @Query("per_page") perPage: Int,
        @Query("orientation") orientation: String, // Add this parameter
        @Header("Authorization") apiKey: String
    ): Response<PexelsResponse>
}

data class PexelsResponse(
    val photos: List<Photo>
)

data class Photo(
    val src: Src?
)

data class Src(
    val original: String?
)
