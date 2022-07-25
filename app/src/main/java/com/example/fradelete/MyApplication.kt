package com.example.fradelete

import android.app.Application
import com.example.fradelete.di.component.ApplicationComponent
import com.example.fradelete.di.component.DaggerApplicationComponent
import com.example.fradelete.di.module.NetworkModule

class MyApplication : Application() {

    lateinit var component: ApplicationComponent
    lateinit var networkModule: NetworkModule

    override fun onCreate() {
        super.onCreate()

        networkModule = NetworkModule(this)
        component = DaggerApplicationComponent.builder()
            .networkModule(networkModule)
            .build()

    }

}