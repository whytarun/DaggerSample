package com.example.daggerexample.di

import com.example.daggerexample.retrofit.FakerApi
import com.example.daggerexample.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit() :Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Singleton
    @Provides
    fun provideFakerApi(retrofit: Retrofit):FakerApi{
        return retrofit.create(FakerApi::class.java)
    }
}