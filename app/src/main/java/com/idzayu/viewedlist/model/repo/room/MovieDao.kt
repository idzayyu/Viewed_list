package com.idzayu.kinoline.model.movies.Repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert
    fun insert(entity: MovieEntity)

    @Insert
    fun insert(list: List<MovieEntity>)

    @Query("SELECT * FROM MovieEntity ")
    fun selectMovie(): List<MovieEntity>

    @Query("SELECT MAX(id) FROM MovieEntity ")
    fun selectMaxIndexMovie(): Int

}