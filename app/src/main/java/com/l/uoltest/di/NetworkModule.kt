package com.l.uoltest.di

import android.os.Build
import com.l.uoltest.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        baseUrl: String
    ): Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

    @Singleton
    @Provides
    fun provideBaseUrl(): String = "https://raw.githubusercontent.com/newloran2/testApp/main/"

    @Singleton
    @Provides
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService = retrofit.create(ApiService::class.java)
}