package com.practice_project.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practice_project.movieapp.retrofit.MovieList
import com.practice_project.movieapp.retrofit.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PopularViewModel: ViewModel() {
    private var disposable: Disposable? = null
    private var movieList = MutableLiveData<MovieList>()
    val liveMovieList: LiveData<MovieList> = movieList

    fun getPopularMovies(movieService: MovieService, page: Int = 1) {
        disposable = movieService.getPopularMovies(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ movies ->
                movieList.value = movies
            }
    }

    fun searchMovies(search: String, movieService: MovieService, page: Int = 1) {
        disposable = movieService.searchMovies(search, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ movies ->
                movieList.value = movies
            }
    }

    override fun onCleared() {
        if (disposable != null){
            disposable = null
        }
        super.onCleared()
    }
}