package com.inc.silence.movies.domain.repositories

import com.inc.silence.movies.domain.entities.Movie
import io.reactivex.Single

interface MoviesRepository {

    fun getMovies(page: Int): Single<List<Movie>>
    fun getMovieDetails(id: Long): Single<Movie>
}