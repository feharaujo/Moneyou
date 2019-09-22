package com.fearaujo.moneyou.dashboard.di

import com.fearaujo.moneyou.dashboard.DashboardViewModel
import com.fearaujo.moneyou.dashboard.DashboardViewModelImpl
import com.fearaujo.usecase.TransactionsUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DashboardModule {

    val module = module {
        viewModel<DashboardViewModel> {
            DashboardViewModelImpl(
                    get() as TransactionsUseCase
            )
        }
    }

}