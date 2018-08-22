package com.inc.silence.movies.di.components.presentation

import com.inc.silence.movies.di.components.AppComponent
import com.inc.silence.movies.di.modules.AppModule
import com.inc.silence.movies.di.modules.presentation.MovieDetailsPresenterModule
import com.inc.silence.movies.di.scopes.Presenter
import com.inc.silence.movies.presentation.movies.detail.MovieDetailsPresenter
import dagger.Component

@Presenter
@Component(dependencies = [AppComponent::class], modules = [MovieDetailsPresenterModule::class])
interface MovieDetailsPresenterComponent {

    fun getMovieDetailsPresenter() : MovieDetailsPresenter
}