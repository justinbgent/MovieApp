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

class PopularViewModel @Inject constructor(private val movieService: MovieService): ViewModel() {
    private var disposable: Disposable? = null

    fun getPopularMovies(page: Int = 1): MutableLiveData<List<Movie>> {
        val popularMovies = MutableLiveData<List<Movie>>()
        disposable = movieService.getPopularMovies(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ movies ->
                popularMovies.value = movies.results
            }
        return popularMovies
    }

    fun searchMovies(search: String, page: Int = 1): MutableLiveData<List<Movie>> {
        val searchMovies = MutableLiveData<List<Movie>>()
        disposable = movieService.searchMovies(search, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ movies ->
                searchMovies.value = movies.results
            }
        return searchMovies
    }

    override fun onCleared() {
        if (disposable != null){
            disposable = null
        }
        super.onCleared()
    }
}