package com.android.academy.fragments.pager

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.android.academy.R
import com.android.academy.SimplePagerAdapter
import com.android.academy.fragments.details.DetailsFragment
import com.android.academy.fragments.list.listeners.OnMovieClickListener
import com.android.academy.model.MovieModel

class MoviesPagerFragment : Fragment() {

    private var moviesPager: ViewPager? = null

    private var listener: OnMovieClickListener? = null
    private var movies: List<MovieModel> = ArrayList()
    private var fragments: List<Fragment> = ArrayList()
    private var selectedPosition = -1

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnMovieClickListener) {
            listener = context
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_pager, container, false)
        moviesPager = view.findViewById(R.id.movies_pager_fragment_pager)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesPager?.adapter = SimplePagerAdapter(activity!!.supportFragmentManager, fragments)
        if (selectedPosition != -1) {
            moviesPager?.currentItem = selectedPosition
        }
    }

    fun loadMovies(movies: List<MovieModel>) {
        this.movies = movies
        fragments = movies.map { DetailsFragment.newInstance(it) }
    }

    fun selectMovie(movie: MovieModel) {
        val moviePosition = movies.indexOf(movie)

        if (moviePosition != -1) {
            selectedPosition = moviePosition
            moviesPager?.currentItem = moviePosition
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        fun newInstance(movies: List<MovieModel>, position: Int): MoviesPagerFragment {
            val fragment = MoviesPagerFragment()
            return fragment
        }
    }
}