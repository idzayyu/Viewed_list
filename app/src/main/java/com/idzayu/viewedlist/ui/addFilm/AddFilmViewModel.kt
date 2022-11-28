package com.idzayu.kinoline.ui.detail

import androidx.lifecycle.ViewModel
import com.idzayu.viewedlist.model.Movie
import com.idzayu.viewedlist.model.repo.MovieList

class AddFilmViewModel :  ViewModel() {

    private val movieList = MovieList.movieList

    fun onClickSave(movie: Movie){
        movieList.add(movie)
    }

}