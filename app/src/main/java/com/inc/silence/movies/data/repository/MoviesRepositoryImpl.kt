package com.inc.silence.movies.data.repository

import com.inc.silence.movies.data.repository.source.MoviesDataStoreFactory
import com.inc.silence.movies.domain.entities.Movie
import com.inc.silence.movies.domain.repositories.MoviesRepository
import com.inc.silence.movies.system.SchedulersProvider
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(val factory: MoviesDataStoreFactory,
                                               val schedulers: SchedulersProvider)
    : MoviesRepository {

    //we always get movies list from the cloud
    override fun getMovies(page: Int): Single<List<Movie>> {
        return factory.retrieveRemoteDataStore()
                .getMovies(page)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())

    }

    override fun getMoverDetails(id: Long): Single<Movie> {
        return factory.retrieveDataStore(id)
                .flatMap { it.getMoviesDetails(id) }
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
    }
}