package com.example.daggerexample.di

import com.example.daggerexample.MainActivity
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules =[NetworkModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)
}