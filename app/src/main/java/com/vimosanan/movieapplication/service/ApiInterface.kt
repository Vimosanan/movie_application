package com.vimosanan.movieapplication.service

import com.vimosanan.movieapplication.service.model.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/")
    suspend fun searchMovie(
        @Query("apikey") apiKey: String,
        @Query("s") searchQuery: String,
        @Query("type") type: String
    ): Response<List<Movie>>

    @GET("/")
    suspend fun getMovieDetails(
        @Query("apiKey") apiKey: String,
        @Query("i") id: String
    )
}