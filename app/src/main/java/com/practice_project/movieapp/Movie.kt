package com.practice_project.movieapp

data class MovieList(val movies: List<Movie>)

data class Movie(val title: String, val posterPath: String)