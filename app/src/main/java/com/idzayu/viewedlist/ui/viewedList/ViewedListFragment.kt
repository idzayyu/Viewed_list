package com.idzayu.viewedlist.ui.viewedList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.idzayu.kinoline.adapters.MovieListAdapter
import com.idzayu.kinoline.ui.detail.DetailFilmFragment
import com.idzayu.viewedlist.R
import com.idzayu.viewedlist.databinding.FragmentViewedListBinding
import com.idzayu.viewedlist.model.repo.MovieList

class ViewedListFragment : Fragment() {

    private var _binding: FragmentViewedListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ViewModelProvider(this).get(ViewedListViewModel::class.java)

        _binding = FragmentViewedListBinding.inflate(inflater, container, false)
        binding.root

        init(binding)
        return binding.root
    }

    private fun init(binding: FragmentViewedListBinding) {

        setupMovieList(binding)

    }

    private fun setupMovieList(fragmentFilmListBinding: FragmentViewedListBinding) {
        val movieAdapter = MovieListAdapter(object : MovieListAdapter.NewsClickListener {

            override fun onMovieDetailClicked(position: Int) {
                MovieList.setPositionSelectedMovie(position)
                val detailFragment = DetailFilmFragment()
                requireActivity().supportFragmentManager.beginTransaction()
                    .add(R.id.nav_host_fragment_activity_main, detailFragment)
                    .addToBackStack("Main")
                    .commit()

            }

        })
        fragmentFilmListBinding.apply {
            recyclerView.adapter = movieAdapter

            recyclerView.layoutManager = GridLayoutManager(activity,1)
            (recyclerView.itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}