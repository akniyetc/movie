package com.inc.silence.movies.ui.movies.list

import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.inc.silence.movies.R
import com.inc.silence.movies.app.App
import com.inc.silence.movies.di.components.AppComponent
import com.inc.silence.movies.di.components.presentation.DaggerMoviesPresenterComponent
import com.inc.silence.movies.domain.entities.Movie
import com.inc.silence.movies.presentation.movies.list.MoviesPresenter
import com.inc.silence.movies.presentation.movies.list.MoviesView
import com.inc.silence.movies.ui.base.BaseFragment
import com.inc.silence.movies.ui.base.ZeroViewHolder
import com.inc.silence.movies.ui.movies.list.adapter.MoviesAdapter
import com.inc.silence.movies.utils.extension.visible
import kotlinx.android.synthetic.main.layout_base_list.*
import kotlinx.android.synthetic.main.layout_zero.*

class MoviesFragment : BaseFragment(), MoviesView{

    override val layoutRes = R.layout.fragment_movies

    private var zeroViewHolder: ZeroViewHolder? = null

    @InjectPresenter
    lateinit var presenter: MoviesPresenter

    @ProvidePresenter
    internal fun providePresenter(): MoviesPresenter {
        val presenterComponent = DaggerMoviesPresenterComponent.builder()
                .appComponent(appComponent)
                .build()

        return presenterComponent.getMoviesPresenter()
    }

    private val adapter = MoviesAdapter({ movie, extras ->
        presenter.onMoviesClicked(activity!!, movie.id, extras)
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        adapter.bottomReachedListener = { presenter.loadNextMoviesPage() }

        swipeToRefresh.setOnRefreshListener { presenter.refreshMovies() }
        zeroViewHolder = ZeroViewHolder(zeroLayout, { presenter.refreshMovies() })
    }

    override fun showEmptyProgress(show: Boolean) {
        fullscreenProgressView.visible(show)

        //trick for disable and hide swipeToRefresh on fullscreen progress
        swipeToRefresh.visible(!show)
        swipeToRefresh.post { swipeToRefresh?.isRefreshing = false }
    }

    override fun showEmptyError(show: Boolean, message: String?) {
        if (show) zeroViewHolder?.showEmptyError(message)
        else zeroViewHolder?.hide()
    }

    override fun showEmptyView(show: Boolean) {
        if (show) zeroViewHolder?.showEmptyData()
        else zeroViewHolder?.hide()
    }

    override fun showMovies(show: Boolean, data: List<Movie>) {
        recyclerView.visible(show)
        recyclerView.post { adapter.setData(data) }
    }

    override fun showRefreshProgress(show: Boolean) {
        swipeToRefresh.post { swipeToRefresh?.isRefreshing = show }
    }

    override fun showPageProgress(show: Boolean) {
        recyclerView.post { adapter.showProgress(show) }
    }

    override fun showMessage(message: String) {
        showSnackMessage(message)
    }
}