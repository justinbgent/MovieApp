package com.practice_project.movieapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.practice_project.movieapp.model.Movie
import com.practice_project.movieapp.retrofit.MovieConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_item.view.*

class MovieAdapter(private val movies: List<Movie>): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title: MaterialTextView = view.txt_title
        val image: AppCompatImageView = view.img_movie
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        Picasso.get().load(MovieConstants.IMAGE_BASE_URL + movie.poster_path).into(holder.image)
        holder.title.text = movie.title
    }
}