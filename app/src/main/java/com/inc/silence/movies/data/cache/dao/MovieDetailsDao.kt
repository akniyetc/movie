package com.inc.silence.movies.data.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.inc.silence.movies.data.cache.db.constants.DBConstants
import com.inc.silence.movies.domain.entities.Movie

@Dao
abstract class MovieDetailsDao {

    @Query(DBConstants.DELETE_ALL + DBConstants.MOVIE_DETAIL_NAME + " WHERE id = :movieId")
    abstract fun clearMovieDetail(movieId: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovieDetail(movie: Movie)

    @Query(DBConstants.QUERY_SELECT + DBConstants.MOVIE_DETAIL_NAME + " WHERE id = :movieId")
    abstract fun getMovieDetail(movieId: Long) : Movie?
}