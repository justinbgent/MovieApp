package com.practice_project.movieapp.dagger

import com.practice_project.movieapp.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    fun provideMainViewModel(): MainViewModel{
        return MainViewModel()
    }
}