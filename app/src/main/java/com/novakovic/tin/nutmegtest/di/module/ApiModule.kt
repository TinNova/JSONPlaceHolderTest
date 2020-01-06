package com.novakovic.tin.nutmegtest.di.module

import com.novakovic.tin.nutmegtest.BASE_URL
import com.novakovic.tin.nutmegtest.BuildConfig
import com.novakovic.tin.nutmegtest.TIMEOUT_IN_SECONDS
import com.novakovic.tin.nutmegtest.network.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Reusable
    fun providesRetrofit(okHttpClient: OkHttpClient): ApiInterface =
            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(ApiInterface::class.java)

    @Provides
    @Reusable
    fun providesOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                    .cache(null)
                    .addInterceptor(HttpLoggingInterceptor().apply { if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY })
                    .build()
}