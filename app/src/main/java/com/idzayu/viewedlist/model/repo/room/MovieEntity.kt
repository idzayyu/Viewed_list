package com.idzayu.kinoline.model.movies.Repository.room

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [
        Index(value = ["id"])
    ]
)
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val nameFilm: String,
    val category: String,
    var comment: String = "",
    val rating: Float,
    var isDbSave: Boolean = false,
    var isSelected: Boolean = false
)