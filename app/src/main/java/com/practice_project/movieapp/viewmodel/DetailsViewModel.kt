package com.practice_project.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import com.practice_project.movieapp.retrofit.MovieService
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val movieService: MovieService): ViewModel() {

}