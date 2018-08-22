package com.inc.silence.movies.data.repository.source

import com.inc.silence.movies.data.repository.store.MoviesCache
import com.inc.silence.movies.data.repository.store.MoviesStore
import javax.inject.Inject

class MoviesCacheDataStore @Inject constructor(private val moviesCache: MoviesCache)
    : MoviesStore {

    //does not need to be saved
    override fun getMovies(page: Int) = throw UnsupportedOperationException()

    override fun getMoviesDetails(id: Long) = moviesCache.getMovieDetail(id)
}