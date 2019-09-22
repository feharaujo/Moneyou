package com.fearaujo.usecase.di

import com.fearaujo.data.TransactionsRepository
import com.fearaujo.usecase.TransactionsUseCase
import com.fearaujo.usecase.TransactionsUseCaseImpl
import org.koin.dsl.module

object UseCaseModule {

    val module = module {
        single<TransactionsUseCase> {
            TransactionsUseCaseImpl(
                get() as TransactionsRepository
            )
        }
    }

}