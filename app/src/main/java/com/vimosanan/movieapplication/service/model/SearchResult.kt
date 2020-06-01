package com.vimosanan.movieapplication.service.model

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("Search")
    val movies: List<Movie>,
    @SerializedName("totalResults")
    val total: Int,
    @SerializedName("Response")
    val response: Boolean
)