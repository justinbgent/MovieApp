package com.practice_project.movieapp.views

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.practice_project.movieapp.MovieAdapter
import com.practice_project.movieapp.R
import com.practice_project.movieapp.util.disableButton
import com.practice_project.movieapp.util.enableButton
import com.practice_project.movieapp.util.hideSoftKeyboard
import com.practice_project.movieapp.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {
    private lateinit var mainActivity: MainActivity
    private val moviesVM: MoviesViewModel by viewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
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

        var searchedMovies = false
        var totalPages = 1
        var pageNumber = 1
        var lastSearch = ""
        val navController = findNavController()

        val layoutManager = GridLayoutManager(mainActivity, 3, GridLayoutManager.VERTICAL, false)
        recycler_view.layoutManager = layoutManager

        moviesVM.movieList.observe(viewLifecycleOwner,
            androidx.lifecycle.Observer { movies ->
                progress_bar.visibility = View.GONE
                recycler_view.adapter = MovieAdapter(movies.results, navController)
                if (movies != null){
                    val pageProgress = "${movies.page} of ${movies.total_pages}"
                    txt_page.text = pageProgress
                    pageNumber = movies.page
                    totalPages = movies.total_pages

                    // Keep track of when to disable and enable buttons
                    if (movies.page == 1){
                        btn_previous.disableButton()
                    }else if (movies.page == 2){
                        btn_previous.enableButton()
                    }

                    if (movies.page == movies.total_pages){
                        btn_next.disableButton()
                    }else if (movies.page == (movies.total_pages -1)){
                        btn_next.enableButton()
                    }else if(movies.page == 1){
                        btn_next.enableButton()
                    }
                }
        })

        moviesVM.getPopularMovies()

        txt_field.setOnEditorActionListener { textView, action, _ ->
            if (action == EditorInfo.IME_ACTION_SEARCH){
                mainActivity.hideSoftKeyboard()
                txt_field.clearFocus()
                lastSearch = textView.text.toString()
                if (lastSearch.isNotEmpty()){
                    moviesVM.searchMovies(lastSearch)
                    searchedMovies = true
                }
            }
            true
        }

        btn_next.setOnClickListener {
            val onLastPage = pageNumber == totalPages
            if (searchedMovies && !onLastPage){
                moviesVM.searchMovies(lastSearch, pageNumber + 1)
            }else if (!onLastPage){
                moviesVM.getPopularMovies(pageNumber + 1)
            }
        }

        btn_previous.setOnClickListener {
            val onFirstPage = pageNumber == 1
            if (searchedMovies && !onFirstPage){
                moviesVM.searchMovies(lastSearch, pageNumber - 1)
            }else if (!onFirstPage){
                moviesVM.getPopularMovies(pageNumber - 1)
            }
        }
    }
}