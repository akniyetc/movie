package com.inc.silence.movies.data.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.inc.silence.movies.data.cache.db.constants.DBConstants
import com.inc.silence.movies.domain.entities.Movie


@Dao
abstract class MoviesDao {

    @Query(DBConstants.QUERY_SELECT + DBConstants.MOVIES_NAME)
    abstract fun getMovies(): List<Movie>

    @Query(DBConstants.DELETE_ALL + DBConstants.MOVIES_NAME)
    abstract fun clearMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovies(movies: List<Movie>)
}