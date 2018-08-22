package com.inc.silence.movies.data.repository.store

import com.inc.silence.movies.domain.entities.Movie
import com.inc.silence.movies.domain.entities.Movies
import io.reactivex.Single

interface MoviesRemote {

    fun getMovies(page: Int) : Single<Movies>

    fun getMovieDetails(id: Long) : Single<Movie>
}