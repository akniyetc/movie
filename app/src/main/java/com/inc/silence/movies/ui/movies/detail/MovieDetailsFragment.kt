package com.inc.silence.movies.ui.movies.detail

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.inc.silence.movies.R
import com.inc.silence.movies.di.components.presentation.DaggerMovieDetailsPresenterComponent
import com.inc.silence.movies.di.modules.presentation.MovieDetailsPresenterModule
import com.inc.silence.movies.domain.entities.Movie
import com.inc.silence.movies.presentation.movies.detail.MovieDetailsPresenter
import com.inc.silence.movies.presentation.movies.detail.MovieDetailsView
import com.inc.silence.movies.ui.base.BaseFragment
import com.inc.silence.movies.utils.extension.isVisible
import com.inc.silence.movies.utils.extension.loadFromUrl
import com.inc.silence.movies.utils.extension.loadUrlAndPostponeEnterTransition
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class MovieDetailsFragment : BaseFragment(), MovieDetailsView {

    @Inject
    lateinit var movieDetailAnimator: MovieDetailAnimator

    @InjectPresenter
    lateinit var detailsPresenter: MovieDetailsPresenter

    @ProvidePresenter
    internal fun providePresenter(): MovieDetailsPresenter {
        val movieId = arguments?.getLong(PARAM_MOVIE, 0)

        val presenterComponent = DaggerMovieDetailsPresenterComponent.builder()
                .appComponent(appComponent)
                .movieDetailsPresenterModule(MovieDetailsPresenterModule(movieId!!))
                .build()

        return presenterComponent.getMovieDetailsPresenter()
    }

    override val layoutRes = R.layout.fragment_movie_details


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        activity?.let { movieDetailAnimator.postponeEnterTransition(it) }
    }


    override fun onBackPressed() {
        movieDetailAnimator.fadeInvisible(scrollView, movieDetails)
        if (moviePlay.isVisible())
            movieDetailAnimator.scaleDownView(moviePlay)
        else
            movieDetailAnimator.cancelTransition(moviePoster)
    }


    override fun showMovieDetails(movie: Movie) {
        movieDetailAnimator.scaleUpView(moviePlay)
        movieDetailAnimator.cancelTransition(moviePlay)
        moviePoster.loadFromUrl(IMG_URL + movie.backdropPath)

        movie.let {
            with(movie) {
                activity?.let {
                    moviePoster.loadUrlAndPostponeEnterTransition(IMG_URL + backdropPath, it)
                    it.toolbar.title = title
                }
                movieSummary.text = overview
                movieCast.text = tagline
                movieDirector.text = popularity.toString()
                movieYear.text = releaseDate
                moviePlay.setOnClickListener {
                    detailsPresenter.onPlayTrailerClicked(context!!, movie.videos?.results!![0].key)
                }
            }
        }
        movieDetailAnimator.fadeVisible(scrollView, movieDetails)
        movieDetailAnimator.scaleUpView(moviePlay)
    }

    override fun showProgress(show: Boolean) {
        showProgressDialog(show)
    }

    override fun showMessage(message: String) {
        showSnackMessage(message)
    }

    companion object {
        private const val PARAM_MOVIE = "param_movie"
        private val IMG_URL = "http://image.tmdb.org/t/p/w780/"

        fun newInstance(movieId: Long): MovieDetailsFragment {
            val movieDetailsFragment = MovieDetailsFragment()
            val arguments = Bundle()
            arguments.putLong(PARAM_MOVIE, movieId)
            movieDetailsFragment.arguments = arguments

            return movieDetailsFragment
        }
    }
}