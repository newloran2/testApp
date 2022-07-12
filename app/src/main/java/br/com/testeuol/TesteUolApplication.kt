package br.com.testeuol

import android.app.Application
import br.com.testeuol.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class TesteUolApplication : Application() {

    override fun onCreate() {

        startKoin {
            androidContext(applicationContext)
            modules(module {
                factory {  }
            })
            val listModules = listOf(AppModule.loadDataModule(), AppModule.loadUseCaseModule(), AppModule.loadViewModelModule())
            modules(listModules)
        }
        super.onCreate()
    }
}