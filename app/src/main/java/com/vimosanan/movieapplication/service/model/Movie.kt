package com.vimosanan.movieapplication.service.model

import com.google.gson.annotations.SerializedName

/*
* {
    "Title": "Marvel One-Shot: Item 47",
    "Year": "2012",
    "imdbID": "tt2247732",
    "Type": "movie",
    "Poster": "https://m.media-amazon.com/images/M/MV5BMjNlMzAxNmQtOGEwZi00NTEyLWI0NWYtMTlhNmE2YTA3ZDVhXkEyXkFqcGdeQXVyNTE1NjY5Mg@@._V1_SX300.jpg"
}
 */

data class Movie(
    @SerializedName("imdbID")
    val id: String,
    @SerializedName("Title")
    val title: String?,
    @SerializedName("Year")
    val year: Int?,
    @SerializedName("Type")
    val type: String?,
    @SerializedName("Poster")
    val imageUrl: String?,
    @SerializedName("Runtime")
    val duration: String?,
    @SerializedName("Director")
    val director: String?,
    @SerializedName("Writer")
    val writers: String?,
    @SerializedName("Actors")
    val actors: String?,
    @SerializedName("Plot")
    val detail: String?,
    @SerializedName("imdbRating")
    val rating: String?,
    @SerializedName("Genre")
    val categories: String?

)
/*
*{
    "Title": "Captain Marvel",
    "Year": "2019",
    "Rated": "PG-13",
    "Released": "08 Mar 2019",
    "Runtime": "123 min",
    "Genre": "Action, Adventure, Sci-Fi",
    "Director": "Anna Boden, Ryan Fleck",
    "Writer": "Anna Boden (screenplay by), Ryan Fleck (screenplay by), Geneva Robertson-Dworet (screenplay by), Nicole Perlman (story by), Meg LeFauve (story by), Anna Boden (story by), Ryan Fleck (story by), Geneva Robertson-Dworet (story by)",
    "Actors": "Brie Larson, Samuel L. Jackson, Ben Mendelsohn, Jude Law",
    "Plot": "Carol Danvers becomes one of the universe's most powerful heroes when Earth is caught in the middle of a galactic war between two alien races.",
    "Language": "English",
    "Country": "USA, Australia",
    "Awards": "7 wins & 46 nominations.",
    "Poster": "https://m.media-amazon.com/images/M/MV5BMTE0YWFmOTMtYTU2ZS00ZTIxLWE3OTEtYTNiYzBkZjViZThiXkEyXkFqcGdeQXVyODMzMzQ4OTI@._V1_SX300.jpg",
    "Ratings": [
        {
            "Source": "Internet Movie Database",
            "Value": "6.9/10"
        },
        {
            "Source": "Rotten Tomatoes",
            "Value": "78%"
        },
        {
            "Source": "Metacritic",
            "Value": "64/100"
        }
    ],
    "Metascore": "64",
    "imdbRating": "6.9",
    "imdbVotes": "412,176",
    "imdbID": "tt4154664",
    "Type": "movie",
    "DVD": "N/A",
    "BoxOffice": "N/A",
    "Production": "N/A",
    "Website": "N/A",
    "Response": "True"
}
*/