package com.l.uoltest.di

import com.l.uoltest.data.remote.ApiService
import com.l.uoltest.data.repository.CustomerRepository
import com.l.uoltest.data.repository.CustomerRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCustomerRepository(
        apiService: ApiService
    ): CustomerRepository = CustomerRepositoryImpl(apiService)
}