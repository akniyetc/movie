package com.inc.silence.movies.utils.auth

import com.inc.silence.movies.utils.text.TextUtils
import com.pixplicity.easyprefs.library.Prefs


class AuthUtils : AuthKeyValueStorage {

    private val apiKey = "b24b155781b11c6f1857ff445d1ee0ef"

    override fun getKey(): String {
        Prefs.putString(AuthKeyValueStorage.AUTH_API_KEY, apiKey)
        return Prefs.getString(AuthKeyValueStorage.AUTH_API_KEY, "")
    }

    override fun isAuthorized() = !TextUtils.isEmpty(getKey())
}
