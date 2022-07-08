package br.com.testeuol.di

import br.com.testeuol.data.api.CustomerApi
import br.com.testeuol.data.network.RetrofitServiceProvider
import br.com.testeuol.data.repository.CustomerRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

object AppModule {

    private val data = module {
        factory { RetrofitServiceProvider(androidApplication()) }
        factory { get<RetrofitServiceProvider>().create(CustomerApi::class.java) }
        factory { CustomerRepository(get()) }
    }

    fun loadDataModule() = data
}