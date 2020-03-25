package com.devwujot.tumblrsearch.framework.di.module

import com.devwujot.tumblrsearch.framework.data.remote.TumblrApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    private val BASE_URL = "https://localhost/"

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun provideScalarsConverter(): ScalarsConverterFactory = ScalarsConverterFactory.create()

    @Provides
    @Singleton
    fun provideRestApi(
        okHttpClient: OkHttpClient,
        scalarsConverterFactory: ScalarsConverterFactory
    ): TumblrApiService =
        Retrofit.Builder()
            .addConverterFactory(scalarsConverterFactory)
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
            .create(TumblrApiService::class.java)
}