package com.vimosanan.testapplication.dagger

import com.vimosanan.movieapplication.dagger.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {

}