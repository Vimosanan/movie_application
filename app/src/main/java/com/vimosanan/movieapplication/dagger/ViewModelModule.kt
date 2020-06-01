package com.vimosanan.testapplication.dagger

import androidx.lifecycle.ViewModelProvider
import com.vimosanan.movieapplication.viewmodel.AppViewModelFactory
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Singleton
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

}