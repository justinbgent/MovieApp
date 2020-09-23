package com.practice_project.movieapp.old

import com.practice_project.movieapp.retrofit.MovieService
import com.practice_project.movieapp.viewmodel.DetailsViewModel
import com.practice_project.movieapp.viewmodel.MoviesViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    fun providePopularViewModel(movieService: MovieService): MoviesViewModel {
        return MoviesViewModel(movieService)
    }

    @Provides
    fun provideDetailsViewModel(movieService: MovieService): DetailsViewModel {
        return DetailsViewModel(movieService)
    }
}