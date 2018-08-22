package com.inc.silence.movies.app

import android.app.Application
import android.content.ContextWrapper
import com.inc.silence.movies.BuildConfig
import com.inc.silence.movies.di.components.AppComponent
import com.inc.silence.movies.di.components.DaggerAppComponent
import com.inc.silence.movies.di.modules.AppModule
import com.pixplicity.easyprefs.library.Prefs
import timber.log.Timber

class App : Application() {


    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        injectMembers()
        initLogger()
        initPrefs()
    }

    private fun injectMembers() = appComponent.inject(this)

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initPrefs() {
        Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(packageName)
                .setUseDefaultSharedPreference(true)
                .build()
    }
}