package com.inc.silence.movies.data.cache.db

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.inc.silence.movies.domain.entities.TrailerList

class Converters {

    val gson = Gson()

    @TypeConverter
    fun fromString(value: String): TrailerList? {
        val listType = object : TypeToken<TrailerList>() {
        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: TrailerList): String? {
        return gson.toJson(list)
    }
}