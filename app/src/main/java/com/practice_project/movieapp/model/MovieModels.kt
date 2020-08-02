package com.practice_project.movieapp.model

data class MovieList(val results: List<Movie>)

data class Movie(val title: String, val id: Int, val release_date: String, val poster_path: String)