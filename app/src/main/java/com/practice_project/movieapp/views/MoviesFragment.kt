package com.practice_project.movieapp.views

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice_project.movieapp.MovieAdapter
import com.practice_project.movieapp.R
import com.practice_project.movieapp.di.App
import com.practice_project.movieapp.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

class MoviesFragment : Fragment() {
    private lateinit var mainActivity: MainActivity
    @Inject lateinit var popularVM: MoviesViewModel

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
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        val layoutManager = LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL, false)
        recycler_view.layoutManager = layoutManager

        popularVM.movieList.observe(viewLifecycleOwner,
            androidx.lifecycle.Observer { movies ->
                progress_bar.visibility = View.GONE
                recycler_view.adapter = MovieAdapter(movies, navController)
        })

        popularVM.getPopularMovies()
    }
}