package com.practice_project.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import com.practice_project.movieapp.retrofit.MovieConstants
import com.practice_project.movieapp.retrofit.MovieService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel(): ViewModel() {
    private val retrofit = Retrofit.Builder().baseUrl(MovieConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    val movieService = retrofit.create(MovieService::class.java)
}