package com.practice_project.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practice_project.movieapp.model.Movie
import com.practice_project.movieapp.model.MovieList
import com.practice_project.movieapp.retrofit.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private val movieService: MovieService): ViewModel() {
    private var disposable: Disposable? = null
    val movieList: MutableLiveData<List<Movie>> = MutableLiveData()

    fun getPopularMovies(page: Int = 1) {
        disposable = movieService.getPopularMovies(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ movies ->
                movieList.value = movies.results
            }
    }

    fun searchMovies(search: String, page: Int = 1) {
        disposable = movieService.searchMovies(search, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ movies ->
                movieList.value = movies.results
            }
    }

    override fun onCleared() {
        if (disposable != null){
            disposable = null
        }
        super.onCleared()
    }
}