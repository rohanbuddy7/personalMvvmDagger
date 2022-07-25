package com.example.fradelete.di.module

import android.app.Application
import android.os.Build
import com.example.fradelete.BuildConfig
import com.example.fradelete.retrofit.FakerAPI
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule(private val application: Application) {

    val NETWORK_CALL_TIMEOUT = 60

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {

        val okHttpClientBuilder = OkHttpClient.Builder()
            .cache(
                Cache(
                    application.cacheDir,
                    10 * 1024 * 1024
                )
            )
            .addInterceptor(HttpLoggingInterceptor()
                .apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
                })
            .addInterceptor(Interceptor { chain ->
                val original: Request = chain.request()
                val requestBuilder = original.newBuilder()

                val extendedUserAgent = "build-version:" + BuildConfig.VERSION_CODE
                    .toString() + "|" + "manufacturer:" + Build.MANUFACTURER
                    .toString() + "|" + "model:" + Build.MODEL
                    .toString() + "|" + "os-version:" + Build.VERSION.SDK_INT
                requestBuilder.addHeader("X-Extended-User-Agent", extendedUserAgent)
                Timber.tag("Network").d("X-Extended-User-Agent: $extendedUserAgent")

                val request = requestBuilder.build()
                return@Interceptor chain.proceed(request)
            })
            .readTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)

        return Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .client(okHttpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesFakerApi(retrofit: Retrofit): FakerAPI {
        return retrofit.create(FakerAPI::class.java)
    }

}