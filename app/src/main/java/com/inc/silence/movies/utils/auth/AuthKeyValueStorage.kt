package com.inc.silence.movies.utils.auth


interface AuthKeyValueStorage {

    fun getKey(): String

    fun isAuthorized(): Boolean

    companion object {
        const val AUTH_API_KEY = "token"
    }

}
