package com.features.test_app_githubusersearch.api

import android.os.SystemClock
import com.fasterxml.jackson.databind.ObjectMapper
import dagger.Module
import dagger.Provides
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule(private val baseUrl: String) {

    @Provides
    @Singleton
    fun provideOkHttpClient(
    ): OkHttpClient {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        return OkHttpClient.Builder()

            .addNetworkInterceptor(Interceptor { chain ->
                SystemClock.sleep(3000)
                chain.proceed(chain.request()) })
            .addInterceptor(loggingInterceptor)
            .dispatcher(dispatcher)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideJacksonConverterFactory(): JacksonConverterFactory {
        val objectMapper = ObjectMapper()
        return JacksonConverterFactory
            .create(objectMapper)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        jacksonConverterFactory: JacksonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(jacksonConverterFactory)
            .addConverterFactory(EnumConverterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create<ApiService>(ApiService::class.java)
    }
}