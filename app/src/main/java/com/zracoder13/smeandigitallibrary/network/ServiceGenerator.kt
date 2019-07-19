package com.zracoder13.smeandigitallibrary.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier

object ServiceGenerator {

    private val BASE_URL = "http://smeandigitallibrary.000webhostapp.com/"

    private val sHttpClient = OkHttpClient.Builder()
            .addInterceptor(ApiKeyAdderInterceptor())
            .sslSocketFactory(Config.getSSLSocketFactory())
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .hostnameVerifier(HostnameVerifier { hostname, session -> return@HostnameVerifier true })


    private val sBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(sHttpClient.build())

    fun <T> createService(serviceClass: Class<T>): T {
        return sBuilder.build().create(serviceClass)
    }
}
