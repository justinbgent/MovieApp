package com.practice_project.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practice_project.movieapp.retrofit.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DetailsViewModel (private val movieService: MovieService): ViewModel() {
    private var disposable: Disposable? = null

    fun getGenreNameById(ids: List<Int>): MutableLiveData<MutableList<String>> {
        val genreNames: MutableLiveData<MutableList<String>> = MutableLiveData()
        disposable = movieService.getGenres()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ genreList ->
                val list = mutableListOf<String>()
                genreList.genres.forEach { genre ->
                    if (ids.contains(genre.id)){
                        list.add(genre.name)
                    }
                }
                genreNames.value = list
            }
        return genreNames
    }

    override fun onCleared() {
        if (disposable != null){
            disposable?.dispose()
        }
        super.onCleared()
    }
}