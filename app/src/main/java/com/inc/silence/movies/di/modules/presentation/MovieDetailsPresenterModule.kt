package com.inc.silence.movies.di.modules.presentation

import com.inc.silence.movies.di.scopes.Presenter
import dagger.Module
import dagger.Provides

@Module
class MovieDetailsPresenterModule constructor(private val movieId: Long) {

    @Provides
    @Presenter
    fun getMovieId(): Long = movieId
}