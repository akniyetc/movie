package com.inc.silence.movies.di.modules

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.inc.silence.movies.data.remote.MovieService
import com.inc.silence.movies.data.remote.interceptor.NetworkCheckInterceptor
import com.inc.silence.movies.di.qualifiers.NetworkInterceptor
import com.inc.silence.movies.di.qualifiers.OkHttpInterceptor
import com.inc.silence.movies.di.scopes.ApplicationScope
import com.inc.silence.movies.system.NetworkChecker
import com.inc.silence.movies.utils.auth.AuthKeyValueStorage
import com.inc.silence.movies.utils.auth.AuthUtils
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetworkModule {

    private val baseUrl = "https://api.themoviedb.org/3/"


    @Provides
    @ApplicationScope
    internal fun provideOkHttpClient(networkChecker: NetworkChecker,
                                     authUtils: AuthKeyValueStorage,
                                     @OkHttpInterceptor interceptors: List<@JvmSuppressWildcards Interceptor>?,
                                     @NetworkInterceptor networkInterceptors: List<@JvmSuppressWildcards Interceptor>?)
            : OkHttpClient {

        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor(NetworkCheckInterceptor(networkChecker))

        if (interceptors != null) {
            for (interceptor in interceptors) {
                okHttpBuilder.addInterceptor(interceptor)
            }
        }

        if (networkInterceptors != null) {
            for (networkInterceptor in networkInterceptors) {
                okHttpBuilder.addNetworkInterceptor(networkInterceptor)
            }
        }

        okHttpBuilder.addInterceptor { chain ->
            var request = chain.request()
            val url = request.url().newBuilder()
            if (authUtils.getKey().isNotEmpty()) {
                url.addQueryParameter("api_key", authUtils.getKey())
                url.addQueryParameter("append_to_response", "videos")
            }
            request = request.newBuilder()
                    .url(url.build())
                    .build()
            chain.proceed(request)
        }

        return okHttpBuilder.build()
    }

    @Provides
    @ApplicationScope
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
        return Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build()
    }

    @Provides
    @ApplicationScope
    internal fun provideGson() = Gson()

    @Provides
    @ApplicationScope
    internal fun provideApi(retrofit: Retrofit): MovieService = retrofit.create(MovieService::class.java)

    @Provides
    @ApplicationScope
    internal fun provideKeyValueStorage(): AuthKeyValueStorage = AuthUtils()
}