package com.example.daggerexample

import android.app.Application
import com.example.daggerexample.di.ApplicationComponent
import com.example.daggerexample.di.DaggerApplicationComponent

class FakerApplication :Application() {

    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().build()
    }
}