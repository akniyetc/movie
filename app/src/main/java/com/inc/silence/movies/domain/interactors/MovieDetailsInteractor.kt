package com.inc.silence.movies.domain.interactors

import com.inc.silence.movies.domain.repositories.MoviesRepository
import javax.inject.Inject

class MovieDetailsInteractor @Inject constructor(val repository: MoviesRepository) {

    fun getMoviesDetail(id: Long) = repository.getMovieDetails(id)
}