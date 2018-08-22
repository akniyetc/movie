package com.inc.silence.movies.data.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.inc.silence.movies.data.cache.db.constants.DBConstants
import com.inc.silence.movies.domain.entities.Movie

@Dao
abstract class MoviesDao {

    @Query("DELETE FROM movie WHERE id = :movieId")
    abstract fun clearMovieDetail(movieId: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovieDetail(movie: Movie)

    @Query("SELECT * FROM movie WHERE id = :movieId")
    abstract fun getMovieDetail(movieId: Long) : Movie?



    @Query("SELECT * FROM movie")
    abstract fun getMovies() : List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovies(movies: List<Movie>)

    @Query("DELETE FROM movie")
    abstract fun clearMovies()
}