package com.practice_project.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practice_project.movieapp.retrofit.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val movieService: MovieService): ViewModel() {
    private var disposable: Disposable? = null

    fun getGenreNameById(id: Int): MutableLiveData<String> {
        val genreName: MutableLiveData<String> = MutableLiveData()
        disposable = movieService.getGenres()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ genreList ->
                genreList.genres.forEach { genre ->
                    if (genre.id == id){
                        genreName.value = genre.name
                    }
                }
            }
        return genreName
    }

    override fun onCleared() {
        if (disposable != null){
            disposable = null
        }
        super.onCleared()
    }
}