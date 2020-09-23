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
import com.practice_project.movieapp.MovieConstants
import com.practice_project.movieapp.viewmodel.DetailsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_details.*
import java.lang.Exception
import javax.inject.Inject

class MovieDetailsFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val movie: Movie? = arguments?.getParcelable<Movie>(MovieConstants.BUNDLE_KEY)
        if (movie != null) {
            // Setup view
            txt_title.text = movie.title
            txt_overview.text = movie.overview
            Picasso.get().load(
                MovieConstants.IMAGE_BASE_URL + movie.backdrop_path
            )
                .into(img_poster, object : com.squareup.picasso.Callback {
                    override fun onSuccess() {
                        progress_bar.visibility = View.GONE
                    }
                    override fun onError(e: Exception?) { }
                })

            detailsVM.getGenreNameById(movie.genre_ids)
                .observe(viewLifecycleOwner, androidx.lifecycle.Observer { genre ->
                    val genres = genre.joinToString()
                    txt_genre.text = genres
                })

            txt_release.text = movie.release_date
        }
        //
    }
}