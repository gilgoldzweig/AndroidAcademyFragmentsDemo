package com.android.academy.fragments.pager

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.android.academy.R
import com.android.academy.SamplePagerAdapter
import com.android.academy.fragments.list.listeners.OnMovieClickListener

class MoviesPagerFragment : Fragment() {
    private lateinit var adapter: SamplePagerAdapter
    private var listener: OnMovieClickListener? = null
    private var movies: List<Fragment> = ArrayList()

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
    ): View? = inflater.inflate(R.layout.fragment_movies_pager, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SamplePagerAdapter(activity!!.supportFragmentManager, movies)
        (view as ViewPager).adapter = adapter
    }

    fun loadMovies(movies: List<Fragment>) {
        this.movies = movies
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}