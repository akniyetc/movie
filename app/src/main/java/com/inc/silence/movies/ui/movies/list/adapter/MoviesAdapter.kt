package com.inc.silence.movies.ui.movies.list.adapter

import android.support.v7.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import com.inc.silence.movies.domain.entities.Movie
import com.inc.silence.movies.system.router.MoviesRouter

class MoviesAdapter (clickListener: (Movie, MoviesRouter.Extras) -> Unit)
    : ListDelegationAdapter<MutableList<Any>>() {

    internal var bottomReachedListener: () -> Unit = {}

    init {
        items = mutableListOf()
        delegatesManager.addDelegate(MovieAdapterDelegate(clickListener))
        delegatesManager.addDelegate(ProgressAdapterDelegate())
    }

    fun setData(movies: List<Movie>) {
        val progress = isProgress()

        items.clear()
        items.addAll(movies)
        if (progress) items.add(ProgressItem())

        notifyItemRangeInserted(itemCount, itemCount + movies.size)
    }

    fun showProgress(isVisible: Boolean) {
        val currentProgress = isProgress()

        if (isVisible && !currentProgress){
            items.add(ProgressItem())
            notifyItemInserted(items.lastIndex)
        }
        else if (!isVisible && currentProgress){
            items.remove(items.last())
            notifyItemRemoved(items.lastIndex)
        }

    }

    private fun isProgress() = items.isNotEmpty() && items.last() is ProgressItem

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any?>) {
        super.onBindViewHolder(holder, position, payloads)

        if (position == items.size - 4) bottomReachedListener.invoke()
    }
}