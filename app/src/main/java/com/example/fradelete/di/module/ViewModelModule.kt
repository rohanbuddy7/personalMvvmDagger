package com.example.fradelete.di.module

import androidx.lifecycle.ViewModel
import com.example.fradelete.viewmodel.MainViewModel
import dagger.Module

@Module
abstract class ViewModelModule {

    abstract fun getMainViewModel(mainViewModel: MainViewModel): ViewModel

}