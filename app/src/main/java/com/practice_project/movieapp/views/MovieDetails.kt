package com.practice_project.movieapp.views

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.practice_project.movieapp.R
import com.practice_project.movieapp.di.App
import com.practice_project.movieapp.model.Movie
import com.practice_project.movieapp.model.MovieConstants
import com.practice_project.movieapp.viewmodel.DetailsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_details.*
import javax.inject.Inject

class MovieDetails : Fragment() {
    private lateinit var mainActivity: MainActivity
    @Inject lateinit var detailsVM: DetailsViewModel

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
        return inflater.inflate(R.layout.movie_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val movie: Movie? = arguments?.getParcelable<Movie>(MovieConstants.BUNDLE_KEY)
        if (movie != null){
            // Setup view
            txt_title.text = movie.title
            txt_overview.text = movie.overview
            Picasso.get().load(
                MovieConstants.IMAGE_BASE_URL + movie.backdrop_path).into(img_poster)
        }
        // detailsVM.getGenreNameById()
    }
}