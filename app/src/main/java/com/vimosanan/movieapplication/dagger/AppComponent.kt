package com.vimosanan.testapplication.dagger

import com.vimosanan.movieapplication.dagger.AppModule
import com.vimosanan.movieapplication.dagger.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent {

}