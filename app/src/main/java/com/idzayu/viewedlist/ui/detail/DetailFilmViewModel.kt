package com.idzayu.kinoline.ui.detail

import androidx.lifecycle.ViewModel
import com.idzayu.viewedlist.model.repo.MovieList

class DetailFilmViewModel :  ViewModel() {

    private var position = MovieList.getPositionSelectedMovie()
    private val movieList = MovieList.movieList

    fun getDescriptionText(): String {
        return movieList[position].comment
    }

    fun getCategory(): String {
        return movieList[position].category
    }

    fun getRating(): Float {
        return movieList[position].rating
    }

    fun addNameFilm(): String {
        return movieList[position].nameFilm
    }
}