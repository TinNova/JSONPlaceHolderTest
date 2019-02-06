package com.novakovic.tin.nutmegtest.di

import com.novakovic.tin.nutmegtest.BASE_URL
import com.novakovic.tin.nutmegtest.BuildConfig
import com.novakovic.tin.nutmegtest.network.ApiMethods
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Collection of app-wide injectable instances.
 */
@Module
class AppModule {

    private val timeoutInSeconds = 5L

    /**
     * Initialise ArsenalApi service, including Rx, OkHttp and Gson configs.
     */
    @Provides
    @Reusable
    fun buildJsonPlaceHolderApi(): ApiMethods =
            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(buildOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(ApiMethods::class.java)

    /**
     * Initialise OkHttp builder, so that services can set additional config on OkHttpClient.
     */
    @Provides
    @Reusable
    fun buildOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                    .connectTimeout(timeoutInSeconds, TimeUnit.SECONDS)
                    .writeTimeout(timeoutInSeconds, TimeUnit.SECONDS)
                    .readTimeout(timeoutInSeconds, TimeUnit.SECONDS)
                    .cache(null)
                    .addInterceptor(HttpLoggingInterceptor().apply { if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY })
                    .build()
}
