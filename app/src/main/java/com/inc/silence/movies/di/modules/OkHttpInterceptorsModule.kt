package com.inc.silence.movies.di.modules

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.inc.silence.movies.di.qualifiers.NetworkInterceptor
import com.inc.silence.movies.di.qualifiers.OkHttpInterceptor
import com.inc.silence.movies.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

@Module
class OkHttpInterceptorsModule {

    @Provides
    @OkHttpInterceptor
    @ApplicationScope
    internal fun provideOkHttpInterceptors() : List<out Interceptor>? {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return listOf(httpLoggingInterceptor)
    }

    @Provides
    @NetworkInterceptor
    @ApplicationScope
    internal fun provideNetworkInterceptors() : List<out Interceptor>? {
        return listOf(StethoInterceptor())
    }
}