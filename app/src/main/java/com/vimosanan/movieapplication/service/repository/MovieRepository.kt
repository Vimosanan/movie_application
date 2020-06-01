package com.vimosanan.movieapplication.service.repository

import com.vimosanan.movieapplication.app.API_KEY
import com.vimosanan.movieapplication.service.ApiInterface
import com.vimosanan.movieapplication.service.model.Movie
import com.vimosanan.movieapplication.util.Result
import javax.inject.Inject
import kotlin.Exception

class MovieRepository @Inject constructor(private val api: ApiInterface) {

    suspend fun getMovieListSearch(searchQuery: String, type: String): Result<List<Movie>> {
        val response = api.getSearchResults(
            API_KEY,
            searchQuery,
            type
        )

        return if (response.isSuccessful) {
            val data = response.body()
            Result.Success(data!!.movies)
        } else {
            Result.Error(Exception("Network Failed..."))
        }
    }

    suspend fun getMovieDetails(movieId: String): Result<Movie> {
        val response = api.getMovieDetail(API_KEY, movieId)

        return if (response.isSuccessful) {
            val movie = response.body()
            Result.Success(movie!!)
        } else {
            Result.Error(Exception("Network failed"))
        }
    }
}