package com.idzayu.viewedlist.model.repo

import android.content.Context
import com.idzayu.kinoline.model.movies.Repository.room.AppDataBase
import com.idzayu.kinoline.model.movies.Repository.room.MovieEntity
import com.idzayu.viewedlist.model.Movie
import java.util.concurrent.Executors

object MovieList {

    val movieList = ArrayList<Movie>()
    private var positionSelectedMovie = 0


    fun getMaxMovieId(): Int{
        return  movieList.size
    }

    fun setPositionSelectedMovie(position: Int) {
        positionSelectedMovie = position
    }

    fun getPositionSelectedMovie(): Int{
        return  positionSelectedMovie
    }

    fun averageRating(): String{
        var cntRating = 0.0
        for ( movie in movieList){
            cntRating+=movie.rating
        }
        return (cntRating / movieList.size.toFloat()).toString()
    }

    fun getMovieEntity(): ArrayList<MovieEntity> {
        val list = ArrayList<MovieEntity>()

        movieList.forEach {
            if (!it.isDbSave) {
                list.add(
                    MovieEntity(
                        it.id,
                        it.nameFilm,
                        it.category,
                        it.comment,
                        it.rating,
                    )
                )
            }
        }

        return list
    }

    private fun getMovie(list: List<MovieEntity>?){
        list?.forEach {
            movieList.add(
                Movie(
                    it.id,
                    it.nameFilm,
                    it.category,
                    it.comment,
                    it.rating,
            )
            )
        }

    }

    fun addMovieDb(context: Context){
        val appDb = AppDataBase.getInstance(context)?.getMovieDao()
        Executors.newSingleThreadExecutor().execute {
            Runnable {
                getMovie(appDb?.selectMovie())
            }.run()
        }
    }

    fun setMovieDb(context: Context) {
        Executors.newSingleThreadExecutor().execute {
            Runnable {
                val appDb = AppDataBase.getInstance(context)?.getMovieDao()
                appDb?.insert(getMovieEntity())
            }.run()
        }
    }

}