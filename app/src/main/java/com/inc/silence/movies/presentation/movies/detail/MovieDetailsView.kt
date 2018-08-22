package com.inc.silence.movies.presentation.movies.detail

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.inc.silence.movies.domain.entities.Movie

@StateStrategyType(AddToEndSingleStrategy::class)
interface MovieDetailsView : MvpView {
    fun showMovieDetails(movie: Movie)
    fun showProgress(show: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showMessage(message: String)
}