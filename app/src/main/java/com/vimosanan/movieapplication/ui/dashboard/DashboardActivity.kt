package com.vimosanan.movieapplication.ui.dashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.vimosanan.movieapplication.R
import com.vimosanan.movieapplication.app.MOVIE_ID
import com.vimosanan.movieapplication.app.MovieApplication
import com.vimosanan.movieapplication.databinding.ActivityDashboardBinding
import com.vimosanan.movieapplication.service.model.Movie
import com.vimosanan.movieapplication.ui.MovieViewModel
import com.vimosanan.movieapplication.ui.adapters.MovieListAdapter
import com.vimosanan.movieapplication.ui.detail.MovieDetailActivity
import com.vimosanan.movieapplication.util.Result
import javax.inject.Inject


class DashboardActivity : AppCompatActivity(), MovieListAdapter.Interaction {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var adapterMovies: MovieListAdapter

    private var searchedQuery = "Captain"
    private var searchingQuery = "Captain"

    override fun onCreate(savedInstanceState: Bundle?) {
        val appComponent = (applicationContext as MovieApplication).appComponent
        appComponent.inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieViewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(MovieViewModel::class.java)

        //init adapters
        adapterMovies = MovieListAdapter(this)

        //init recycler view and set adapter
        val layoutManagerCategory = GridLayoutManager(this, 2)
        binding.recyclerView.layoutManager = layoutManagerCategory
        binding.recyclerView.adapter = adapterMovies

        movieViewModel.search(searchedQuery, type = "movie")

        initObservers()

    }

    private fun initObservers() {
        movieViewModel.movies.observe(this, Observer {
            when(it) {
                is Result.Loading -> showProgress()
                is Result.Success -> {
                    hideProgress()
                    if(it.data != null) {
                        searchedQuery = searchingQuery
                        binding.txtInfo.text = getString(R.string.app_dashboard_info, searchedQuery)
                        adapterMovies.submitList(it.data)
                    } else {
                        updateError("No result found for the query...!")
                    }
                }
                is Result.Error -> {
                    hideProgress()
                    updateError(it.exception.message!!)
                }
            }

        })

        binding.btnSearch.setOnClickListener {
            hideKeyboard(this)//hide the keyboard

            searchingQuery = binding.edtTxtSearch.text.toString().trim()
            if(searchingQuery.isNotEmpty() && searchingQuery.isNotBlank()) {
                movieViewModel.search(searchingQuery, type = "movie")
            } else {
                updateError("search query cannot be empty!")
            }
        }
    }

    private fun updateError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    private fun showProgress() {
        binding.animationView.playAnimation()
        binding.animationView.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.animationView.pauseAnimation()
        binding.animationView.visibility = View.GONE
    }

    override fun onItemSelected(position: Int, movie: Movie) {
        startActivityForResult(Intent(this, MovieDetailActivity::class.java)
            .apply {
                putExtra(MOVIE_ID, movie.id)
            }, REQUEST_CODE_MOVIE
        )
    }

    companion object {
        const val REQUEST_CODE_MOVIE = 900
    }
}


fun hideKeyboard(activity: Activity) {
    val imm: InputMethodManager =
        activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = activity.currentFocus
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}
