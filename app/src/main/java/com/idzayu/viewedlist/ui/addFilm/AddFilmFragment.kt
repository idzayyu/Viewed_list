package com.idzayu.kinoline.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.idzayu.viewedlist.R
import com.idzayu.viewedlist.databinding.FragmentAddFilmBinding
import com.idzayu.viewedlist.model.Movie
import com.idzayu.viewedlist.model.repo.MovieList

class AddFilmFragment() : DialogFragment() {
    private lateinit var dfvm: AddFilmViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAddFilmBinding.inflate(inflater)
        init(binding)
        return binding.root
    }

    private fun init(binding: FragmentAddFilmBinding) {
        dfvm = ViewModelProvider(this)[AddFilmViewModel::class.java]

        binding.buttonSaveDetail.setOnClickListener {
            dfvm.onClickSave(
                Movie(
                    MovieList.getMaxMovieId(),
                    binding.title.text.toString(),
                    binding.category.text.toString(),
                    comment = binding.comment.text.toString(),
                    rating = binding.ratingBar.rating
                )
            )
            onStop()
        }

    }
}
