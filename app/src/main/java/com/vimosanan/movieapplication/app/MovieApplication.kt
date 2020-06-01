package com.vimosanan.movieapplication.app

import android.app.Application
import com.vimosanan.movieapplication.dagger.AppModule
import com.vimosanan.testapplication.dagger.AppComponent
import com.vimosanan.testapplication.dagger.DaggerAppComponent

class MovieApplication : Application() {

    val appComponent: AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
}