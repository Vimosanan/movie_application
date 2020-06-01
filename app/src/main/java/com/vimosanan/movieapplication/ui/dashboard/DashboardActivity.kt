package com.vimosanan.movieapplication.ui.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
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

    @SuppressLint("WrongConstant")
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
        val layoutManagerCategory = LinearLayoutManager(this, LinearLayout.VERTICAL,false)
        binding.recyclerView.layoutManager = layoutManagerCategory
        binding.recyclerView.adapter = adapterMovies

        //decorate the recycler view by adding a line between each element
        val mDividerItemDecoration = DividerItemDecoration(
            binding.recyclerView.context,
            layoutManagerCategory.orientation
        )

        /*ContextCompat.getDrawable(this, R.drawable.divider_line_pink)?.let {
            mDividerItemDecoration.setDrawable(it)
        }*/

        movieViewModel.search("Captain", type = "movie")

        initObservers()

    }

    private fun initObservers() {
        movieViewModel.movies.observe(this, Observer {
            when(it) {
                is Result.Loading -> showProgress()
                is Result.Success -> {
                    hideProgress()
                    adapterMovies.submitList(it.data)
                }
                is Result.Error -> {
                    hideProgress()
                }
            }

        })
    }

    private fun showProgress() {

    }

    private fun hideProgress() {

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