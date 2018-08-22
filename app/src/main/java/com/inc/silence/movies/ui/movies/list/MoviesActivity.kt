package com.inc.silence.movies.ui.movies.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.inc.silence.movies.ui.base.BaseActivity

class MoviesActivity : BaseActivity() {


    override fun fragment() = MoviesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)
    }

    companion object {
        fun callingIntent(context: Context) = Intent(context, MoviesActivity::class.java)
    }

}
