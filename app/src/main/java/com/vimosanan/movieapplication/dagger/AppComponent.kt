package com.vimosanan.movieapplication.dagger

import com.vimosanan.movieapplication.ui.dashboard.DashboardActivity
import com.vimosanan.movieapplication.ui.detail.MovieDetailActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(dashboardActivity: DashboardActivity)
    fun inject(movieDetailActivity: MovieDetailActivity)
}