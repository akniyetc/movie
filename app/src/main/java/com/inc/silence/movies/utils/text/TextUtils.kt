package com.inc.silence.movies.utils.text

import android.support.annotation.Nullable

class TextUtils {

    companion object {
        /**
         * Returns true if the string is null or 0-length.
         * @param str the string to be examined
         * @return true if str is null or zero length
         */
        fun isEmpty(@Nullable str: CharSequence?): Boolean {
            return str == null || str.isEmpty()
        }
    }

}