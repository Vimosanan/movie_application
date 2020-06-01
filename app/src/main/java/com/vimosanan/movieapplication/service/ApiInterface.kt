package com.vimosanan.movieapplication.service

import com.vimosanan.movieapplication.service.model.Movie
import com.vimosanan.movieapplication.service.model.SearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/")
    suspend fun getSearchResults(
        @Query("apikey") apiKey: String,
        @Query("s") searchQuery: String,
        @Query("type") type: String
    ): Response<SearchResult>

    @GET("/")
    suspend fun getMovieDetail(
        @Query("apiKey") apiKey: String,
        @Query("i") id: String
    ):Response<Movie>
}