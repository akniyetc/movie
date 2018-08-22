package com.inc.silence.movies.data.repository.source

import com.inc.silence.movies.data.repository.store.MoviesCache
import com.inc.silence.movies.data.repository.store.MoviesStore
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviesDataStoreFactory @Inject constructor(val moviesCache: MoviesCache,
                                                 val moviesCacheDataStore: MoviesCacheDataStore,
                                                 val moviesRemoteDataStore: MoviesRemoteDataStore) {

    fun retrieveDataStore(id: Long): Single<MoviesStore> {
        return Single.create<MoviesStore> {
            if (moviesCache.isMovieDetailCached(id) && !moviesCache.isExpired(id)) {
                it.onSuccess(retrieveCacheDataStore())
            }
            it.onSuccess(retrieveRemoteDataStore())
        }
    }

    fun retrieveCacheDataStore(): MoviesStore = moviesCacheDataStore

    fun retrieveRemoteDataStore(): MoviesStore = moviesRemoteDataStore

}