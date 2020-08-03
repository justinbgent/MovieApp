package com.practice_project.movieapp.di

import com.practice_project.movieapp.retrofit.MovieService
import com.practice_project.movieapp.viewmodel.DetailsViewModel
import com.practice_project.movieapp.viewmodel.MoviesViewModel
import com.practice_project.movieapp.views.MovieDetailsFragment
import com.practice_project.movieapp.views.MoviesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, ApiModule::class])
interface ApplicationGraph {
    fun inject(popularMovies: MoviesFragment)
    fun inject(movieDetails: MovieDetailsFragment)

    fun popularViewModel(): MoviesViewModel
    fun detailsViewModel(): DetailsViewModel
    fun movieService(): MovieService
}