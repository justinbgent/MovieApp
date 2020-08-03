package com.practice_project.movieapp.di

import com.practice_project.movieapp.retrofit.MovieService
import com.practice_project.movieapp.viewmodel.DetailsViewModel
import com.practice_project.movieapp.viewmodel.PopularViewModel
import com.practice_project.movieapp.views.MovieDetails
import com.practice_project.movieapp.views.PopularMovies
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, ApiModule::class])
interface ApplicationGraph {
    fun inject(popularMovies: PopularMovies)
    fun inject(movieDetails: MovieDetails)

    fun popularViewModel(): PopularViewModel
    fun detailsViewModel(): DetailsViewModel
    fun movieService(): MovieService
}