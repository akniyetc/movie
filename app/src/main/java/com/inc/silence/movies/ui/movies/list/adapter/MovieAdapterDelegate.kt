package com.inc.silence.movies.ui.movies.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import com.inc.silence.movies.R
import com.inc.silence.movies.domain.entities.Movie
import com.inc.silence.movies.system.router.MoviesRouter
import com.inc.silence.movies.utils.extension.inflate
import com.inc.silence.movies.utils.extension.loadFromUrl
import kotlinx.android.synthetic.main.row_movie.view.*

class MovieAdapterDelegate(private val clickListener: (Movie, MoviesRouter.Extras) -> Unit = { _, _ -> })
    : AdapterDelegate<MutableList<Any>>() {

    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder =
            MovieViewHolder(parent!!.inflate(R.layout.row_movie), clickListener)

    override fun isForViewType(items: MutableList<Any>, position: Int) =
            items[position] is Movie

    override fun onBindViewHolder(items: MutableList<Any>,
                                  position: Int,
                                  holder: RecyclerView.ViewHolder,
                                  payloads: MutableList<Any>) =
            (holder as MovieViewHolder).bind(items[position] as Movie)

    private class MovieViewHolder(val view: View, val clickListener: (Movie, MoviesRouter.Extras) -> Unit)
        : RecyclerView.ViewHolder(view) {

        private val IMG_URL = "http://image.tmdb.org/t/p/w185/"

        fun bind(movie: Movie) {
            view.moviePoster.loadFromUrl(IMG_URL + movie.posterPath)
            view.setOnClickListener { clickListener.invoke(movie, MoviesRouter.Extras(view.moviePoster)) }
        }
    }
}