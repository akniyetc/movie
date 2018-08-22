package com.inc.silence.movies.presentation.base

import com.inc.silence.movies.system.ResourceManager
import com.inc.silence.movies.utils.extension.userMessage
import timber.log.Timber
import javax.inject.Inject

class ErrorHandler @Inject constructor(private val resourceManager: ResourceManager) {

    fun proceed(error: Throwable, messageListener: (String) -> Unit = {}) {
        Timber.e(error)
        messageListener(error.userMessage(resourceManager))
    }
}