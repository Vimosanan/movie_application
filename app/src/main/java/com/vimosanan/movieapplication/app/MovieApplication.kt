package com.vimosanan.movieapplication.app

import android.app.Application
import com.vimosanan.movieapplication.dagger.AppComponent
import com.vimosanan.movieapplication.dagger.DaggerAppComponent
import com.vimosanan.movieapplication.util.NetworkManager

class MovieApplication : Application() {

    val appComponent: AppComponent =
        DaggerAppComponent.builder()
            .build()

    override fun onCreate() {
        super.onCreate()
        NetworkManager.registerNetworkCallback(this)
    }
}