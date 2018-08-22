package com.inc.silence.movies.data.repository.store

import com.inc.silence.movies.domain.entities.Movie
import io.reactivex.Completable
import io.reactivex.Single

interface MoviesCache {

    fun saveMovieDetails(movie: Movie): Completable

    fun getMovieDetail(id: Long) : Single<Movie>

    fun isMovieDetailCached(id: Long): Boolean

    fun clearMovieDetail(id: Long) : Completable


    fun setLastCacheTime(lastCache: Long)

    fun isExpired(id: Long): Boolean
}