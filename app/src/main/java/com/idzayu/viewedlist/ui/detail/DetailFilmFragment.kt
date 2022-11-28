package com.idzayu.kinoline.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.idzayu.viewedlist.R
import com.idzayu.viewedlist.databinding.FragmentDetailFilmBinding

class DetailFilmFragment : Fragment() {
    private lateinit var dfvm: DetailFilmViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailFilmBinding.inflate(inflater)
        init(binding)
        return binding.root
    }

    private fun init(binding: FragmentDetailFilmBinding) {
        dfvm = ViewModelProvider(this)[DetailFilmViewModel::class.java]
        binding.comment.text = dfvm.getDescriptionText()
        binding.category.text = dfvm.getCategory()
        binding.ratingBar.rating = dfvm.getRating()
        binding.title.text = dfvm.addNameFilm()

    }
}
