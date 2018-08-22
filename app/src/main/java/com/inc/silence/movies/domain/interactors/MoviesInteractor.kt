package com.inc.silence.movies.domain.interactors

import com.inc.silence.movies.domain.repositories.MoviesRepository
import javax.inject.Inject

class MoviesInteractor @Inject constructor(private val moviesRepository: MoviesRepository) {

    fun getMovies(page: Int) = moviesRepository.getMovies(page)
}