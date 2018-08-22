package com.inc.silence.movies.presentation.movies.list

import android.support.v4.app.FragmentActivity
import com.arellomobile.mvp.InjectViewState
import com.inc.silence.movies.domain.entities.Movie
import com.inc.silence.movies.domain.interactors.MoviesInteractor
import com.inc.silence.movies.presentation.base.BasePresenter
import com.inc.silence.movies.presentation.base.ErrorHandler
import com.inc.silence.movies.presentation.base.Paginator
import com.inc.silence.movies.system.ResourceManager
import com.inc.silence.movies.system.router.MoviesRouter
import javax.inject.Inject

@InjectViewState
class MoviesPresenter @Inject constructor(val moviesInteractor: MoviesInteractor,
                                          val errorHandler: ErrorHandler,
                                          val router: MoviesRouter)
    : BasePresenter<MoviesView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        refreshMovies()
    }

    private val paginator = Paginator(
            { moviesInteractor.getMovies(it) },
            object : Paginator.ViewController<Movie> {
                override fun showEmptyProgress(show: Boolean) {
                    viewState.showEmptyProgress(show)
                }

                override fun showEmptyError(show: Boolean, error: Throwable?) {
                    if (error != null) {
                        errorHandler.proceed(error, { viewState.showEmptyError(show, it) })
                    } else {
                        viewState.showEmptyError(show, null)
                    }
                }

                override fun showEmptyView(show: Boolean) {
                    viewState.showEmptyView(show)
                }

                override fun showData(show: Boolean, data: List<Movie>) {
                    viewState.showMovies(show, data)
                }

                override fun showErrorMessage(error: Throwable) {
                    errorHandler.proceed(error, { viewState.showMessage(it) })
                }

                override fun showRefreshProgress(show: Boolean) {
                    viewState.showRefreshProgress(show)
                }

                override fun showPageProgress(show: Boolean) {
                    viewState.showPageProgress(show)
                }
            }
    )

    override fun onDestroy() {
        super.onDestroy()
        paginator.release()
    }

    fun refreshMovies() = paginator.refresh()
    fun loadNextMoviesPage() = paginator.loadNewPage()
    fun onMoviesClicked(activity: FragmentActivity, id: Long,
                        navigationExtras: MoviesRouter.Extras) =
            router.showMovieDetails(activity, id, navigationExtras)
}