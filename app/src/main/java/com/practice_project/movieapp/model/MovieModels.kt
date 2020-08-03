package com.practice_project.movieapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieList(val results: List<Movie>): Parcelable

@Parcelize
data class Movie(
    val title: String,
    val id: Int,
    val release_date: String,
    val overview: String,
    val poster_path: String,
    val backdrop_path: String): Parcelable

data class GenreList(val genres: List<Genre>)

data class Genre(val id: Int, val name: String)