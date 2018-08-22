package com.inc.silence.movies.presentation.movies.detail

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.inc.silence.movies.domain.entities.Trailer
import com.inc.silence.movies.domain.interactors.MovieDetailsInteractor
import com.inc.silence.movies.presentation.base.BasePresenter
import com.inc.silence.movies.presentation.base.ErrorHandler
import com.inc.silence.movies.system.SchedulersProvider
import com.inc.silence.movies.system.router.MoviesRouter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class MovieDetailsPresenter @Inject constructor(val detailsInteractor: MovieDetailsInteractor,
                                                val errorHandler: ErrorHandler,
                                                val id: Long,
                                                val router: MoviesRouter)
    : BasePresenter<MovieDetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadDetails()
    }

    fun loadDetails() {
        detailsInteractor.getMoviesDetail(id)
                .doOnSubscribe { viewState.showProgress(true) }
                .doAfterTerminate { viewState.showProgress(false) }
                .subscribe(
                        { movie -> viewState.showMovieDetails(movie) },
                        { errorHandler.proceed(it, {
                            viewState.showMessage(it)
                            viewState.cancelPostponeTransition()
                        }) }
                )
                .connect()
    }

    fun onPlayTrailerClicked(context: Context, trailer: String) = router.openVideo(context, trailer)
}