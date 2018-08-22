package com.inc.silence.movies.presentation.movies.list

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.inc.silence.movies.domain.entities.Movie

@StateStrategyType(AddToEndSingleStrategy::class)
interface MoviesView : MvpView {
    fun showEmptyProgress(show: Boolean)
    fun showEmptyError(show: Boolean, message: String?)
    fun showEmptyView(show: Boolean)
    fun showMovies(show: Boolean, data: List<Movie>)
    fun showRefreshProgress(show: Boolean)
    fun showPageProgress(show: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showMessage(message: String)
}