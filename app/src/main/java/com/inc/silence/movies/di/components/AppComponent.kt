package com.inc.silence.movies.di.components

import android.content.Context
import com.inc.silence.movies.app.App
import com.inc.silence.movies.di.modules.AppModule
import com.inc.silence.movies.di.modules.DataModule
import com.inc.silence.movies.di.modules.NetworkModule
import com.inc.silence.movies.di.modules.OkHttpInterceptorsModule
import com.inc.silence.movies.di.scopes.ApplicationScope
import com.inc.silence.movies.domain.repositories.MoviesRepository
import com.inc.silence.movies.ui.movies.detail.MovieDetailsActivity
import com.inc.silence.movies.ui.movies.detail.MovieDetailsFragment
import com.inc.silence.movies.ui.movies.list.MoviesActivity
import com.inc.silence.movies.ui.movies.list.MoviesFragment
import dagger.Component

@ApplicationScope
@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    DataModule::class,
    OkHttpInterceptorsModule::class
])
interface AppComponent {
    fun inject(application: App)
    fun inject(moviesActivity: MoviesActivity)
    fun inject(movieDetailsActivity: MovieDetailsActivity)
    fun inject(moviesFragment: MoviesFragment)
    fun inject(movieDetailsFragment: MovieDetailsFragment)

    fun provideContext(): Context
    fun provideMoviesRepository() : MoviesRepository
}