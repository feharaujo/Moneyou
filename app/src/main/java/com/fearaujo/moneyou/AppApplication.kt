package com.fearaujo.moneyou

import android.app.Application
import com.fearaujo.data.di.RepositoryModule
import com.fearaujo.moneyou.dashboard.di.DashboardModule
import com.fearaujo.usecase.di.UseCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val modules = listOf(
                DashboardModule.module,
                UseCaseModule.module,
                RepositoryModule.module
        )

        startKoin {
            printLogger()
            androidContext(this@AppApplication)
            modules(modules)
        }

    }

}