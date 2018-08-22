package com.inc.silence.movies.data.remote.interceptor


import com.inc.silence.movies.data.remote.exceptions.NoNetworkException
import com.inc.silence.movies.system.NetworkChecker
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkCheckInterceptor(private val networkChecker: NetworkChecker) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        if (!networkChecker.isConnected) {
            throw NoNetworkException("No internet connection")
        }

        try {
            return chain.proceed(requestBuilder.build())
        } catch (e: SocketTimeoutException) {
            throw NoNetworkException()
        } catch (e: UnknownHostException) {
            throw NoNetworkException()
        }
    }
}
