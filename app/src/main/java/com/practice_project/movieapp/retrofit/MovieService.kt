package com.practice_project.movieapp.retrofit

import com.practice_project.movieapp.model.GenreList
import com.practice_project.movieapp.model.Movie
import com.practice_project.movieapp.MovieConstants
import com.practice_project.movieapp.model.MovieList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("trending/movie/week${MovieConstants.FIXED_QUERY_PARAMS}")
    fun getPopularMovies(@Query("page")page: Int) : Single<MovieList>

    @GET("search/movie${MovieConstants.FIXED_QUERY_PARAMS}")
    fun searchMovies(@Query("query")query: String,
        @Query("page")page: Int) : Single<MovieList>

    @GET("movie/{id}${MovieConstants.FIXED_QUERY_PARAMS}")
    fun getMovieById(@Path("id")id: Int) : Single<Movie>

    @GET("genre/movie/list${MovieConstants.FIXED_QUERY_PARAMS}")
    fun getGenres(): Single<GenreList>
}