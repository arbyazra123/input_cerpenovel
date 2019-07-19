package com.zracoder13.smeandigitallibrary.network

import java.io.IOException

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class ApiKeyAdderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request()
                .url()
                .newBuilder()
                // .addQueryParameter("api_key", BuildConfig.THE_MOVIE_DB_API)
                .build()
        val request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
