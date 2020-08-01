package com.practice_project.movieapp.retrofit

data class MovieList(val movies: List<Movie>)

data class Movie(val title: String, val id: Int, val releaseDate: String, val posterPath: String)