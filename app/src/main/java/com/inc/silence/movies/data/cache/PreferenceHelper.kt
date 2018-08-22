package com.inc.silence.movies.data.cache

import android.content.Context
import android.content.SharedPreferences
import com.inc.silence.movies.di.scopes.ApplicationScope
import javax.inject.Inject

@ApplicationScope
open class PreferenceHelper @Inject constructor(context: Context) {

    companion object {
        private const val PREF_INVEST_PACKAGE_NAME = "com.inc.silence.movies"

        private const val PREF_KEY_LAST_CACHE = "last_cache_"
    }

    private val pref: SharedPreferences

    init {
        pref = context.getSharedPreferences(PREF_INVEST_PACKAGE_NAME, Context.MODE_PRIVATE)
    }

    /**
     * Store and retrieve the last time data was cached
     */
    fun getLastCacheTime(id: Long): Long {
        return pref.getLong(PREF_KEY_LAST_CACHE + id, 0)
    }

    fun setLastCacheTime(time: Long, id: Long) {
        pref.edit().putLong(PREF_KEY_LAST_CACHE + id, time).apply()
    }
}