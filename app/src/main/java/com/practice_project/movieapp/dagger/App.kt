package com.practice_project.movieapp.dagger

import android.app.Application

class App: Application() {
    val appComponent: ApplicationGraph by lazy {
        DaggerApplicationGraph.builder()
            .viewModelModule(ViewModelModule())
            .build()
    }
}