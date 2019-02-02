package com.novakovic.tin.nutmegtest.network

import com.novakovic.tin.nutmegtest.BASE_URL
import com.novakovic.tin.nutmegtest.BuildConfig
import com.novakovic.tin.nutmegtest.TIMEOUT_IN_SECONDS
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceProvider {

    fun buildJsonPlaceHolderApi(): ApiMethods =
            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(buildOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(ApiMethods::class.java)

    private fun buildOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                    .cache(null)
                    .addInterceptor(HttpLoggingInterceptor().apply { if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY })
                    .build()
}