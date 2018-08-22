package com.inc.silence.movies.data.remote

import com.inc.silence.movies.data.repository.store.MoviesRemote
import javax.inject.Inject

class MoviesRemoteImpl @Inject constructor(private val service: MovieService) : MoviesRemote {


    override fun getMovies(page: Int) = service.getMovies(page)

    override fun getMovieDetails(id: Long) = service.getMovieDetails(id)

}