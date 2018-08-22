package com.inc.silence.movies.di.components.presentation

import com.inc.silence.movies.di.components.AppComponent
import com.inc.silence.movies.di.scopes.Presenter
import com.inc.silence.movies.presentation.movies.list.MoviesPresenter
import dagger.Component

@Presenter
@Component(dependencies = [AppComponent::class])
interface MoviesPresenterComponent {

    fun getMoviesPresenter() : MoviesPresenter
}