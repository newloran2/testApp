package br.com.testeuol.di

import br.com.testeuol.data.api.CustomerApi
import br.com.testeuol.data.network.RetrofitServiceProvider
import br.com.testeuol.data.repository.CustomerRepository
import br.com.testeuol.feature.home.usecase.CustomerUseCase
import br.com.testeuol.feature.home.viewmodel.GalleryLinkWebViewModel
import br.com.testeuol.feature.home.viewmodel.GalleryPhotoProfileViewModel
import br.com.testeuol.feature.home.viewmodel.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    private val data = module {
        factory { RetrofitServiceProvider(androidApplication()) }
        factory { get<RetrofitServiceProvider>().create(CustomerApi::class.java) }
        factory { CustomerRepository(get()) }
    }

    private val userCaseModel = module {
        factory { CustomerUseCase(get()) }
    }

    private val viewModelModule = module {
        viewModel { HomeViewModel(get()) }
        viewModel{GalleryLinkWebViewModel()}
        viewModel{GalleryPhotoProfileViewModel()}

    }
    fun loadDataModule() = data

    fun loadUseCaseModule() = userCaseModel

    fun loadViewModelModule() = viewModelModule
}