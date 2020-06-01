package com.vimosanan.movieapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vimosanan.movieapplication.service.model.Movie
import com.vimosanan.movieapplication.service.repository.MovieRepository
import com.vimosanan.movieapplication.util.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val repo: MovieRepository) : ViewModel() {
    private val _movies = MutableLiveData<Result<List<Movie>>>()
    val movies: LiveData<Result<List<Movie>>>
        get() = _movies

    private val _movie = MutableLiveData<Result<Movie>>()
    val movie: LiveData<Result<Movie>>
        get() = _movie

    fun search(searchedQuery: String, type: String) {
        viewModelScope.launch {
            with(Dispatchers.IO) {
                _movies.postValue(Result.Loading)
                val result = repo.getMovieListSearch(searchedQuery, type)

                _movies.postValue(result)
            }
        }
    }

    fun getMovieDetail(movieId: String) {
        viewModelScope.launch {
            with(Dispatchers.IO){
                _movie.postValue(Result.Loading)
                val result = repo.getMovieDetails(movieId)

                _movie.postValue(result)
            }
        }
    }
}