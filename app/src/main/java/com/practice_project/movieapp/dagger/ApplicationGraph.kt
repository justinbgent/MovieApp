package com.practice_project.movieapp.dagger

import com.practice_project.movieapp.views.MainActivity
import com.practice_project.movieapp.viewmodel.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class])
interface ApplicationGraph {
    fun inject(mainActivity: MainActivity)

    fun mainViewModel(): MainViewModel
}