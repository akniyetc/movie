package com.inc.silence.movies.data.remote

import com.inc.silence.movies.domain.entities.Movie
import com.inc.silence.movies.domain.entities.Movies
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieService {

    companion object {
        private const val PARAM_MOVIE_ID = "movieId"
        private const val MOVIES_PATH = "discover/movie"
        private const val MOVIE_DETAILS = "movie/{$PARAM_MOVIE_ID}"
    }

    @GET(MOVIES_PATH)
    fun getMovies(@Query("page") page: Int) : Single<Movies>

    @GET(MOVIE_DETAILS)
    fun getMovieDetails(@Path("movieId") id: Long) : Single<Movie>
}