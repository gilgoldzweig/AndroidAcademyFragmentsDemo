package com.android.academy.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.academy.R
import com.android.academy.model.MovieModel

class DetailsFragment : Fragment() {

    private lateinit var posterImage: ImageView
    private lateinit var titleText: TextView
    private lateinit var releaseDateText: TextView
    private lateinit var trailerButton: Button
    private lateinit var overviewText: TextView

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        val movie: MovieModel? = arguments?.getParcelable(MOVIE_BUNDLE_KEY)
        initViews(view)
        movie?.let(::loadMovie)
        return view
    }


    private fun initViews(view: View) {
        posterImage = view.findViewById(R.id.details_fragment_poster)
        titleText = view.findViewById(R.id.details_fragment_title)
        releaseDateText = view.findViewById(R.id.details_fragment_release_date)
        trailerButton = view.findViewById(R.id.details_fragment_trailer_btn)
        overviewText = view.findViewById(R.id.details_fragment_overview_text)
    }

    fun loadMovie(movie: MovieModel) {
        titleText.text = movie.name
        overviewText.text = movie.overview
        posterImage.setImageResource(movie.imageRes)
    }

    companion object {
        fun newInstance(movie: MovieModel): DetailsFragment {
            val detailsFragment = DetailsFragment()
            val arguments = Bundle()
            arguments.putParcelable(MOVIE_BUNDLE_KEY, movie)
            detailsFragment.arguments = arguments
            return detailsFragment
        }

        private const val MOVIE_BUNDLE_KEY = "movie_bundle_key"
    }
}
