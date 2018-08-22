package com.inc.silence.movies.domain.entities

import android.arch.persistence.room.Entity
import com.inc.silence.movies.data.cache.db.constants.DBConstants

@Entity(tableName = DBConstants.MOVIES_NAME)
data class Movies (
        val page: Int,
        val totalResults: Long,
        val totalPages: Long,
        val results: List<Movie>
)