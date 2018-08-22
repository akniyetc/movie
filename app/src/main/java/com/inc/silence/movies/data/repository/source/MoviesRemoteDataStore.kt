package com.inc.silence.movies.data.repository.source

import com.inc.silence.movies.data.repository.store.MoviesCache
import com.inc.silence.movies.data.repository.store.MoviesRemote
import com.inc.silence.movies.data.repository.store.MoviesStore
import com.inc.silence.movies.domain.entities.Movie
import io.reactivex.Single
import javax.inject.Inject

class MoviesRemoteDataStore @Inject constructor(val moviesRemote: MoviesRemote,
                                                val moviesCache: MoviesCache) : MoviesStore {

    override fun getMovies(page: Int): Single<List<Movie>> {
        return moviesRemote.getMovies(page)
                .map { it.results }
    }

    override fun getMoviesDetails(id: Long): Single<Movie> {
        return moviesRemote.getMovieDetails(id)
                .doAfterSuccess { moviesCache.saveMovieDetails(it).subscribe() }
    }
}