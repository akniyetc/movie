package com.inc.silence.movies.ui.movies.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.inc.silence.movies.ui.base.BaseActivity
import com.inc.silence.movies.ui.base.BaseFragment

class MovieDetailsActivity : BaseActivity() {

    override fun fragment() = MovieDetailsFragment.newInstance(intent.getLongExtra(INTENT_EXTRA_PARAM_MOVIE, 0))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)
    }

    companion object {

        private const val INTENT_EXTRA_PARAM_MOVIE = "com.silence.INTENT_PARAM_MOVIE"

        fun callingIntent(context: Context, movieId: Long): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PARAM_MOVIE, movieId)
            return intent
        }
    }
}
