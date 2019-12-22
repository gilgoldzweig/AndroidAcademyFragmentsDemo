package com.android.academy.fragments.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.academy.R
import com.android.academy.fragments.list.adapters.MoviesViewAdapter
import com.android.academy.fragments.list.listeners.OnMovieClickListener
import com.android.academy.model.MovieModel

class MoviesFragment : Fragment(), OnMovieClickListener {

    private var moviesRcv: RecyclerView? = null

    private val movies: MutableList<MovieModel> = ArrayList()

    private lateinit var moviesAdapter: MoviesViewAdapter
    private var listener: OnMovieClickListener? = null

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

        val view = inflater.inflate(R.layout.fragment_movies, container, false)

        moviesRcv = view.findViewById(R.id.fragment_movies_rcv)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadMovies()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        context?.let {
            // Create Movies Adapter
            moviesAdapter = MoviesViewAdapter(it, this@MoviesFragment)

            // Attach Adapter to RecyclerView
            moviesRcv?.adapter = moviesAdapter

            // Populate Adapter with data
            moviesAdapter.setData(movies)
        }
    }

    override fun onMovieClicked(movie: MovieModel) {
        listener?.onMovieClicked(movie)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun loadMovies(): List<MovieModel> {
        if (movies.isEmpty()) {
            movies.add(
                MovieModel(
                    "Jurassic World - Fallen Kingdom",
                    R.drawable.jurassic_world_fallen_kingdom,
                    "Three years after the demise of Jurassic World, a volcanic eruption threatens the remaining dinosaurs on the isla Nublar, so Claire Dearing, the former park manager, recruits Owen Grady to help prevent the extinction of the dinosaurs once again"
                )
            )
            movies.add(
                MovieModel(
                    "The Meg",
                    R.drawable.the_meg,
                    "A deep sea submersible pilot revisits his past fears in the Mariana Trench, and accidentally unleashes the seventy foot ancestor of the Great White Shark believed to be extinct"
                )
            )
            movies.add(
                MovieModel(
                    "The First Purge",
                    R.drawable.the_first_purge,
                    "To push the crime rate below one percent for the rest of the year, the New Founding Fathers of America test a sociological theory that vents aggression for one night in one isolated community. But when the violence of oppressors meets the rage of the others, the contagion will explode from the trial-city borders and spread across the nation"
                )
            )
            movies.add(
                MovieModel(
                    "Deadpool 2",
                    R.drawable.deadpool_2,
                    "Wisecracking mercenary Deadpool battles the evil and powerful Cable and other bad guys to save a boy's life"
                )
            )
            movies.add(
                MovieModel(
                    "Black Panther",
                    R.drawable.black_panther,
                    "King T'Challa returns home from America to the reclusive, technologically advanced African nation of Wakanda to serve as his country's new leader. However, T'Challa soon finds that he is challenged for the throne by factions within his own country as well as without. Using powers reserved to Wakandan kings, T'Challa assumes the Black Panther mantel to join with girlfriend Nakia, the queen-mother, his princess-kid sister, members of the Dora Milaje (the Wakandan 'special forces') and an American secret agent, to prevent Wakanda from being dragged into a world war"
                )
            )
            movies.add(
                MovieModel(
                    "Ocean's Eight",
                    R.drawable.ocean_eight,
                    "Debbie Ocean, a criminal mastermind, gathers a crew of female thieves to pull off the heist of the century at New York's annual Met Gala"
                )
            )
            movies.add(
                MovieModel(
                    "Interstellar",
                    R.drawable.interstellar,
                    "Interstellar chronicles the adventures of a group of explorers who make use of a newly discovered wormhole to surpass the limitations on human space travel and conquer the vast distances involved in an interstellar voyage"
                )
            )
            movies.add(
                MovieModel(
                    "Thor - Ragnarok",
                    R.drawable.thor_ragnarok,
                    "Thor is on the other side of the universe and finds himself in a race against time to get back to Asgard to stop Ragnarok, the prophecy of destruction to his homeworld and the end of Asgardian civilization, at the hands of an all-powerful new threat, the ruthless Hela"
                )
            )
            movies.add(
                MovieModel(
                    "Guardians of the Galaxy",
                    R.drawable.guardians_of_the_galaxy,
                    "Light years from Earth, 26 years after being abducted, Peter Quill finds himself the prime target of a manhunt after discovering an orb wanted by Ronan the Accuser"
                )
            )
        }
        return movies
    }

    companion object {
        val TAG = MoviesFragment::class.java.simpleName
    }
}