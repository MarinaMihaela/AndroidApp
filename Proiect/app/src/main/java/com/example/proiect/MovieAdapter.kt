package com.example.proiect.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proiect.data.model.Film
import com.example.proiect.R

class MovieAdapter(private var movies: List<Film>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.movieTitle)
        val genre: TextView = view.findViewById(R.id.movieGenre)
        val rating: TextView = view.findViewById(R.id.movieRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.title.text = movie.nume
        holder.genre.text = movie.gen
        val ratingValue = movie.rating?.toString() ?: "N/A"
        holder.rating.text = holder.rating.context.getString(R.string.rating_text, ratingValue)
    }

    override fun getItemCount() = movies.size

    fun setMovies(newMovies: List<Film>) {
        this.movies = newMovies
        notifyDataSetChanged()
    }
}
