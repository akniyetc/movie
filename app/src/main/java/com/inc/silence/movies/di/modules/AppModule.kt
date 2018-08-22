package com.inc.silence.movies.di.modules

import android.content.Context
import com.inc.silence.movies.di.scopes.ApplicationScope
import com.inc.silence.movies.system.AppSchedulers
import com.inc.silence.movies.system.ResourceManager
import com.inc.silence.movies.system.SchedulersProvider
import com.inc.silence.movies.system.router.MoviesRouter
import com.inc.silence.movies.ui.movies.detail.MovieDetailAnimator
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone

@Module
class AppModule(context: Context) {

    private val context: Context = context.applicationContext

    @Provides
    @ApplicationScope
    internal fun provideApplicationContext() = context

    @Provides
    @ApplicationScope
    internal fun provideResourceManager(context: Context) = ResourceManager(context)

    @Provides
    @ApplicationScope
    internal fun provideRouter() = MoviesRouter()

    @Provides
    @ApplicationScope
    internal fun provideSchedulerProvider(): SchedulersProvider = AppSchedulers()

    @Provides
    @ApplicationScope
    internal fun provideAnimator() = MovieDetailAnimator()
}