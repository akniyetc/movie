package com.inc.silence.movies.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.inc.silence.movies.data.cache.MoviesCacheImpl
import com.inc.silence.movies.data.cache.PreferenceHelper
import com.inc.silence.movies.data.cache.db.MoviesDB
import com.inc.silence.movies.data.remote.MoviesRemoteImpl
import com.inc.silence.movies.data.repository.MoviesRepositoryImpl
import com.inc.silence.movies.data.repository.store.MoviesCache
import com.inc.silence.movies.data.repository.store.MoviesRemote
import com.inc.silence.movies.di.scopes.ApplicationScope
import com.inc.silence.movies.domain.repositories.MoviesRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    @ApplicationScope
    internal fun provideDictionaryDataBase(context: Context): MoviesDB {
        return Room.databaseBuilder(context.applicationContext,
                MoviesDB::class.java, "movies.db")
                .build()
    }

    @Provides
    @ApplicationScope
    internal fun providePrefHelper(context: Context) = PreferenceHelper(context)

    @Provides
    @ApplicationScope
    internal fun provideMoviesCache(cacheImpl: MoviesCacheImpl) : MoviesCache = cacheImpl

    @Provides
    @ApplicationScope
    internal fun provideMoviesRemote(remoteImpl: MoviesRemoteImpl) : MoviesRemote = remoteImpl

    @Provides
    @ApplicationScope
    internal fun provideMoviesRepository(repository: MoviesRepositoryImpl) : MoviesRepository = repository
}