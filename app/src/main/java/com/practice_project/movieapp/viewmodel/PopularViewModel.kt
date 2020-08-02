package com.practice_project.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practice_project.movieapp.retrofit.MovieList
import com.practice_project.movieapp.retrofit.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PopularViewModel @Inject constructor(private val movieService: MovieService): ViewModel() {
    private var disposable: Disposable? = null
    private var popularMovies = MutableLiveData<MovieList>()
    val popularMovieList: LiveData<MovieList> = popularMovies
    private var searchMovies = MutableLiveData<MovieList>()
    val searchMovieList: LiveData<MovieList> = searchMovies

    fun getPopularMovies(page: Int = 1) {
        disposable = movieService.getPopularMovies(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ movies ->
                popularMovies.value = movies
            }
    }

    fun searchMovies(search: String, page: Int = 1) {
        disposable = movieService.searchMovies(search, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ movies ->
                searchMovies.value = movies
            }
    }

    override fun onCleared() {
        if (disposable != null){
            disposable = null
        }
        super.onCleared()
    }
}