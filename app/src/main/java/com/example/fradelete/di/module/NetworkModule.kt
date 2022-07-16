package com.example.fradelete.di.module

import com.example.fradelete.retrofit.FakerAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesFakerApi(retrofit: Retrofit ): FakerAPI{
        return retrofit.create(FakerAPI::class.java)
    }

}