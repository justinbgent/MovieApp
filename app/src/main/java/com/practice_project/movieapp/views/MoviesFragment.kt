package com.practice_project.movieapp.views

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.button.MaterialButton
import com.practice_project.movieapp.MovieAdapter
import com.practice_project.movieapp.R
import com.practice_project.movieapp.di.App
import com.practice_project.movieapp.util.disableButton
import com.practice_project.movieapp.util.enableButton
import com.practice_project.movieapp.util.hideSoftKeyboard
import com.practice_project.movieapp.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

class MoviesFragment : Fragment() {
    private lateinit var mainActivity: MainActivity
    @Inject lateinit var moviesVM: MoviesViewModel

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
        var searchedMovies = false
        val layoutManager = LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL, false)
        recycler_view.layoutManager = layoutManager

        moviesVM.movieList.observe(viewLifecycleOwner,
            androidx.lifecycle.Observer { movies ->
                progress_bar.visibility = View.GONE
                recycler_view.adapter = MovieAdapter(movies.results, navController)
                if (movies != null){
                    val pageProgress = "${movies.page} of ${movies.total_pages}"
                    txt_page.text = pageProgress

                    if (movies.page == 1){
                        btn_previous.disableButton()
                    }else if (movies.page == 2){
                        btn_previous.enableButton()
                    }

                    if (movies.page == movies.total_pages){
                        btn_next.disableButton()
                    }else if (movies.page == (movies.total_pages -1)){
                        btn_next.enableButton()
                    }
                }
        })

        moviesVM.getPopularMovies()

        txt_field.setOnEditorActionListener { textView, action, _ ->
            if (action == EditorInfo.IME_ACTION_SEARCH){
                mainActivity.hideSoftKeyboard()
                txt_field.clearFocus()
                val text = textView.text.toString()
                if (text.isNotEmpty()){
                    moviesVM.searchMovies(text)
                    searchedMovies = true
                }
            }
            true
        }
    }
}