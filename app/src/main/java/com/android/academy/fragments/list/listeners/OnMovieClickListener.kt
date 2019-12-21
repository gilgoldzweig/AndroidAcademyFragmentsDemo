package com.android.academy.fragments.list.listeners

import com.android.academy.model.MovieModel

interface OnMovieClickListener {
    fun onMovieClicked(movie: MovieModel)
}