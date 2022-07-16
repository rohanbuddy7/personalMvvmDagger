package com.example.fradelete.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.fradelete.MyApplication
import com.example.fradelete.R
import com.example.fradelete.viewmodel.MainViewModel
import com.example.fradelete.viewmodel.MainViewModelFactory
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        (application as MyApplication).component.inject(this)

        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

    }

}