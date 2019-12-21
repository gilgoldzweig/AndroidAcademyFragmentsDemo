package com.android.academy.activities.main

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.android.academy.R
import com.android.academy.fragments.details.DetailsFragment
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
        supportFragmentManager.beginTransaction().apply {
            if (tabletFragmentContainer == null) {
                //Phone mode
                addToBackStack(null)
                replace(R.id.activity_main_frame, moviesPagerFragment)
            } else {
                //Tablet mode
                replace(R.id.activity_main_tablet_frame, moviesPagerFragment)
            }
        }.commit()

        moviesPagerFragment.loadMovies(moviesFragment.loadMovies().map {
            DetailsFragment.newInstance(it)
        })
    }

    override fun onMovieClicked(movie: MovieModel) {
        val detailsFragment = DetailsFragment.newInstance(movie)

        supportFragmentManager.beginTransaction().apply {
            if (tabletFragmentContainer == null) {
                //Phone mode
                addToBackStack(null)
                replace(R.id.activity_main_frame, detailsFragment)
            } else {
                //Tablet mode
                replace(R.id.activity_main_tablet_frame, detailsFragment)
            }
        }.commit()
    }
}