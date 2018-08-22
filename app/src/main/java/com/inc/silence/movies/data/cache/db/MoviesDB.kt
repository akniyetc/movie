package com.inc.silence.movies.data.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.inc.silence.movies.data.cache.dao.MoviesDao
import com.inc.silence.movies.domain.entities.Movie

@Database(
        version = 1,
        exportSchema = false,
        entities = [
            Movie::class
        ]
)
@TypeConverters(Converters::class)
abstract class MoviesDB : RoomDatabase() {

    //abstract fun cachedMoviesDao(): MoviesDao

    abstract fun cachedMovieDetailDao(): MoviesDao

    private var mINSTANCE: MoviesDB? = null

    private val sLock = Any()

    fun getInstance(context: Context): MoviesDB {
        if (mINSTANCE == null) {
            synchronized(sLock) {
                if (mINSTANCE == null) {
                    mINSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            MoviesDB::class.java,
                            "movies.db")
                            .build()
                }
                return mINSTANCE!!
            }
        }
        return mINSTANCE!!
    }
}