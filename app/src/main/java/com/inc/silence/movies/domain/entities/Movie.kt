package com.inc.silence.movies.domain.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.inc.silence.movies.data.cache.db.constants.DBConstants

@Entity(tableName = DBConstants.MOVIE_DETAIL_NAME)
data class Movie(
        @PrimaryKey
        val id: Long,
        val voteCount: Int,
        val title: String,
        val popularity: Float,
        val posterPath: String,
        val backdropPath: String?,
        val overview: String,
        val tagline: String?,
        val releaseDate: String,
        val videos: TrailerList?
)