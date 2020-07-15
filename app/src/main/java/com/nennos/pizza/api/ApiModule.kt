package com.nennos.pizza.api

import com.nennos.pizza.BuildConfig
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiModule {

    private const val URL_BASE = "https://api.myjson.com/bins/"
    private const val URL_PAY = "http://httpbin.org/"

    private fun provideBaseRetrofit() = retrofitBuilder(URL_BASE).build()
    private fun providePayRetrofit() = retrofitBuilder(URL_PAY).build()

    fun provideBaseApiService(): BaseApiService =
        provideBaseRetrofit().create(BaseApiService::class.java)

    fun providePayApiService(): PayApiService =
        providePayRetrofit().create(PayApiService::class.java)

    private fun retrofitBuilder(url: String): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient())
    }

    private fun okHttpClient(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpBuilder.addInterceptor(httpLoggingInterceptor)
        }

        return okHttpBuilder.connectTimeout(2, TimeUnit.MINUTES)
            .callTimeout(2, TimeUnit.MINUTES)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectionPool(ConnectionPool(4, 1L, TimeUnit.MINUTES))
            .build()
    }
}