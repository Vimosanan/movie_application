package com.vimosanan.movieapplication.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.vimosanan.movieapplication.R
import com.vimosanan.movieapplication.app.MovieApplication
import com.vimosanan.movieapplication.app.default_rating
import com.vimosanan.movieapplication.app.default_text
import com.vimosanan.movieapplication.app.default_time
import com.vimosanan.movieapplication.databinding.ActivityDashboardBinding
import com.vimosanan.movieapplication.databinding.ActivityMovieDetailBinding
import com.vimosanan.movieapplication.service.model.Movie
import com.vimosanan.movieapplication.ui.MovieViewModel
import com.vimosanan.movieapplication.util.Result
import kotlinx.android.synthetic.main.activity_movie_detail.*
import java.lang.NumberFormatException
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val appComponent = (applicationContext as MovieApplication).appComponent
        appComponent.inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieViewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(MovieViewModel::class.java)

        movieViewModel.getMovieDetail("tt0033317")
        initObservers()
    }

    private fun initObservers() {
        movieViewModel.movie.observe(this, Observer {
            when (it) {
                is Result.Loading -> showProgress()
                is Result.Success -> {
                    hideProgress()
                    val movie = it.data
                    updateView(movie)
                }
                is Result.Error -> {
                    hideProgress()
                    updateError(it.exception)
                }
            }
        })
    }

    private fun updateView(movie: Movie) {
        Glide.with(this)
            .load(movie.imageUrl)
            .placeholder(R.drawable.test_movie)
            .error(R.drawable.test_movie)
            .into(binding.imgMovie)

        binding.txtViewName.text = movie.title ?: default_text

        binding.txtViewYear.text = if (movie.year != null) {
            movie.year.toString()
        } else {
            default_text
        }

        binding.txtViewCategories.text = movie.categories

        binding.txtViewDuration.text = if (movie.duration != null) {
            convertMinToHrs(movie.duration)
        } else {
            default_time
        }
        binding.txtViewRating.text = movie.rating ?: default_rating

        binding.txtViewDetails.text = movie.detail ?: default_text

        binding.txtViewDirector.text = movie.director ?: default_text
        binding.txtViewWriters.text = movie.writers ?: default_text
        binding.txtViewActors.text = movie.actors ?: default_text
    }

    private fun convertMinToHrs(str: String): String {
        return try {
            val min = str.split(" ")[0].toInt()
            val hrs = min / 60
            val newMin = min % 60

            "${hrs}h ${newMin}m"
        } catch (exception: NumberFormatException) {
            Log.e("Error", exception.message!!)
            str
        }
    }

    private fun updateError(exception: Exception) {

    }

    private fun showProgress() {

    }

    private fun hideProgress() {

    }
}