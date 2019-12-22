package com.android.academy.activities.main

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.android.academy.R
import com.android.academy.fragments.list.MoviesFragment
import com.android.academy.fragments.list.listeners.OnMovieClickListener
import com.android.academy.fragments.pager.MoviesPagerFragment
import com.android.academy.model.MovieModel

class MainActivity : AppCompatActivity(), OnMovieClickListener {

    private var tabletFragmentContainer: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabletFragmentContainer = findViewById(R.id.activity_main_tablet_frame)

        val moviesFragment: MoviesFragment = if (savedInstanceState == null) {
            MoviesFragment().also {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.activity_main_frame, it, MoviesFragment.TAG)
                    .commit()
            }
        } else {
            supportFragmentManager.findFragmentByTag(MoviesFragment.TAG) as MoviesFragment
        }

        val moviesPagerFragment = MoviesPagerFragment()
        moviesPagerFragment.loadMovies(moviesFragment.loadMovies())
        tabletFragmentContainer?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.activity_main_tablet_frame, moviesPagerFragment)
                .commit()
        }
    }

    override fun onMovieClicked(movie: MovieModel) {
        val moviesPagerFragment = MoviesPagerFragment()
        if (tabletFragmentContainer == null) {
            //Phone mode
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.activity_main_frame, moviesPagerFragment)
                .commit()
        }
        moviesPagerFragment.selectMovie(movie)
    }
}