package com.practice_project.movieapp.retrofit

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("search/movie${MovieConstants.FIXED_QUERY_PARAMS}")
    fun searchMovies(
        @Query("api_key")apiKey: String,
        @Query("query")search: String,
        @Query("page")page: Int = 1) : Single<MovieList>
}