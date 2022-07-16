package com.example.fradelete

import android.app.Application
import com.example.fradelete.di.component.ApplicationComponent
import com.example.fradelete.di.component.DaggerApplicationComponent

class MyApplication: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder().build()

    }

}