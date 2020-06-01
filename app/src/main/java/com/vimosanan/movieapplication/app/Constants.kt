package com.vimosanan.movieapplication.app

const val BASE_URL = "http://www.omdbapi.com"
const val API_KEY = "b9bd48a6"

val MOVIE_ID: String get() = "MOVIE_ID"

var IS_CONNECTED: Boolean = false

const val default_text = "unknown"
const val default_time = "0h 0m"
const val default_rating = "0.0"