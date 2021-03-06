package com.example.fradelete.di.component

import com.example.fradelete.di.module.NetworkModule
import com.example.fradelete.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ NetworkModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

}