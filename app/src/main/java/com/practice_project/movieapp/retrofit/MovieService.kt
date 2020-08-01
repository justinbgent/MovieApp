package com.practice_project.movieapp.retrofit

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("search/movie${MovieConstants.FIXED_QUERY_PARAMS}")
    fun searchMovies(
        @Query("query")search: String,
        @Query("page")page: Int = 1) : Single<MovieList>

    @GET("movie/{id}${MovieConstants.FIXED_QUERY_PARAMS}")
    fun getMovieById(@Path("id")id: Int) : Single<Movie>
}