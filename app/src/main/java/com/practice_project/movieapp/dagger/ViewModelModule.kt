package com.practice_project.movieapp.dagger

import com.practice_project.movieapp.retrofit.MovieService
import com.practice_project.movieapp.viewmodel.DetailsViewModel
import com.practice_project.movieapp.viewmodel.PopularViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    fun providePopularViewModel(movieService: MovieService): PopularViewModel {
        return PopularViewModel(movieService)
    }

    @Provides
    fun provideDetailsViewModel(movieService: MovieService): DetailsViewModel {
        return DetailsViewModel(movieService)
    }
}