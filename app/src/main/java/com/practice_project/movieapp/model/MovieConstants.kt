package com.practice_project.movieapp.model

import com.practice_project.movieapp.BuildConfig

object MovieConstants {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY = BuildConfig.MOVIE_KEY
    const val FIXED_QUERY_PARAMS ="?language=en-US&include_adult=false&api_key=$API_KEY"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    const val BUNDLE_KEY = "bundle_key"
}