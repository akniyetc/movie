package com.inc.silence.movies.data.repository.store

import com.inc.silence.movies.domain.entities.Movie
import io.reactivex.Single

interface MoviesStore {

    fun getMovies(page: Int): Single<List<Movie>>

    fun getMoviesDetails(id: Long) : Single<Movie>
}