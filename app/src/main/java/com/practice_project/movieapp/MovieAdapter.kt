package com.practice_project.movieapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView
import com.practice_project.movieapp.model.Movie
import com.practice_project.movieapp.model.MovieConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_item.view.*

class MovieAdapter(private val movies: List<Movie>?, private val navController: NavController) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: MaterialTextView = view.txt_title
        val image: AppCompatImageView = view.img_movie
        val layout: MaterialCardView = view.item_layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movies?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (movies != null) {
            val movie = movies[position]
            Picasso.get().load(
                MovieConstants.IMAGE_BASE_URL_WIDTH_500 + movie.poster_path
            ).into(holder.image)
            holder.title.text = movie.title

            holder.layout.setOnClickListener {
                val bundle = Bundle()
                bundle.putParcelable(MovieConstants.BUNDLE_KEY, movie)
                navController.navigate(R.id.action_moviesFragment_to_movieDetailsFragment, bundle)
            }
        }
    }
}