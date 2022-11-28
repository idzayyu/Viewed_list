package com.idzayu.kinoline.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.idzayu.viewedlist.R
import com.idzayu.viewedlist.databinding.MovieItemBinding
import com.idzayu.viewedlist.model.Movie
import com.idzayu.viewedlist.model.repo.MovieList

class MovieListAdapter(
    private val listener: NewsClickListener
) : RecyclerView.Adapter<MovieListAdapter.MovieHolder>() {
    private val movielist = MovieList.movieList

    class MovieHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            movie: Movie,
            position: Int,
            listener: NewsClickListener
        ) = with(binding) {

            title.text = movie.nameFilm
            ratingBar2.rating = movie.rating
            if (movie.isSelected) title.setBackgroundResource(R.color.purple_500)

            movieItem.setOnClickListener {
                title.setBackgroundResource(R.color.purple_500)
                movie.isSelected = true
                listener.onMovieDetailClicked(position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = MovieItemBinding
            .bind(
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.movie_item, parent, false)
            )
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie = movielist[position]
        holder.bind(movie, position, listener)
    }

    interface NewsClickListener {
        fun onMovieDetailClicked(position: Int)
    }

    override fun getItemCount(): Int {
        return movielist.size
    }

}
