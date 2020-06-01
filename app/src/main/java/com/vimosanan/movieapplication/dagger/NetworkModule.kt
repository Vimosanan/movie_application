package com.vimosanan.movieapplication.dagger

import com.vimosanan.movieapplication.app.BASE_URL
import com.vimosanan.movieapplication.service.ApiInterface

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesApiInterface(): ApiInterface {
        return retrofit().create(ApiInterface::class.java)
    }

    @Provides
    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }
}