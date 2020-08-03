package com.practice_project.movieapp.di

import android.app.Application

class App: Application() {
    val appComponent: ApplicationGraph by lazy {
        DaggerApplicationGraph.builder()
            .viewModelModule(ViewModelModule())
            .apiModule(ApiModule())
            .build()
    }
}