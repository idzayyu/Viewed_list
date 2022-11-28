package com.idzayu.viewedlist.model

import android.widget.RatingBar

data class Movie(
    val id: Int,
    val nameFilm: String,
    val category: String,
    var comment: String = "",
    val rating: Float,
    var isDbSave: Boolean = false,
    var isSelected: Boolean = false
    ) {

}









