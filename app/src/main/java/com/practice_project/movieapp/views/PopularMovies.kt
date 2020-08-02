package com.practice_project.movieapp.views

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice_project.movieapp.MovieAdapter
import com.practice_project.movieapp.R
import com.practice_project.movieapp.dagger.App
import com.practice_project.movieapp.retrofit.Movie
import com.practice_project.movieapp.viewmodel.PopularViewModel
import kotlinx.android.synthetic.main.popular_movies.*
import javax.inject.Inject

class PopularMovies : Fragment() {
    @Inject lateinit var popularVM: PopularViewModel
    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
        (mainActivity.application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.popular_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var movieList = listOf<Movie>()
        val layoutManager = LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL, false)
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = MovieAdapter(movieList)
        popularVM.popularMovieList.observe(viewLifecycleOwner, androidx.lifecycle.Observer { movies ->
            if (movies != null){
                movieList = movies.movies
                recycler_view.adapter?.notifyDataSetChanged()
            }
        })

        popularVM.getPopularMovies()

        // popularVM.searchMovies("asd", mainActivity.mainVM.movieService)
    }
}