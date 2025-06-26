package com.example.proiect.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proiect.R
import com.example.proiect.data.model.Rated

class MovieRatingAdapter(
    private var ratings: List<Rated>
) : RecyclerView.Adapter<MovieRatingAdapter.RatingViewHolder>() {

    inner class RatingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title   = view.findViewById<TextView>(R.id.tvMovieTitle)
        val rating  = view.findViewById<TextView>(R.id.tvMovieRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_rating, parent, false)
        return RatingViewHolder(view)
    }

    @SuppressLint("StringFormatMatches")
    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        val r = ratings[position]

        // Display filmId, or fetch more details about the film if necessary
        holder.title.text  = "Film #${r.filmId}"

        // Use rating directly from Rated model
        holder.rating.text = holder.rating.context
            .getString(R.string.rating_text, r.rating)

    }

    override fun getItemCount() = ratings.size

    fun setRatings(newRatings: List<Rated>) {
        this.ratings = newRatings
        notifyDataSetChanged()
    }
}

