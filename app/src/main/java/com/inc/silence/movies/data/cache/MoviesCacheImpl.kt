package com.inc.silence.movies.data.cache

import com.inc.silence.movies.data.cache.db.MoviesDB
import com.inc.silence.movies.data.repository.store.MoviesCache
import com.inc.silence.movies.domain.entities.Movie
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviesCacheImpl @Inject constructor(val moviesDB: MoviesDB,
                                          val preferenceHelper: PreferenceHelper)
    : MoviesCache {

    private val EXPIRATION_TIME = (60 * 10 * 1000).toLong()


    override fun clearMovieDetail(id: Long): Completable {
        return Completable.defer {
            moviesDB.cachedMovieDetailDao().clearMovieDetail(id)
            Completable.complete()
        }
    }

    override fun saveMovieDetails(movie: Movie): Completable {
        return Completable.defer {
            moviesDB.cachedMovieDetailDao().insertMovieDetail(movie)
            Completable.complete()
        }
                .doOnComplete { setLastCacheTime(System.currentTimeMillis()) }
    }

    override fun getMovieDetail(id: Long): Single<Movie> {
        return Single.defer {
            Single.just(moviesDB.cachedMovieDetailDao().getMovieDetail(id))
        }
    }

    override fun isMovieDetailCached(id: Long) : Boolean {
        return moviesDB.cachedMovieDetailDao().getMovieDetail(id) != null
    }

    override fun setLastCacheTime(lastCache: Long) = preferenceHelper.setLastCacheTime(lastCache)

    override fun isExpired(id: Long): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis(id)
        val expired = currentTime - lastUpdateTime > EXPIRATION_TIME
        if (expired) clearMovieDetail(id)
        return expired
    }

    private fun getLastCacheUpdateTimeMillis(id: Long) = preferenceHelper.getLastCacheTime(id)
}