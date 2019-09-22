package com.fearaujo.data.di

import com.fearaujo.data.JsonDeserializer
import com.fearaujo.data.TransactionsRepository
import com.fearaujo.data.TransactionsRepositoryImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.dsl.module

object RepositoryModule {

    val module = module {

        single {
            GsonBuilder().create()
        }

        single {
            JsonDeserializer(
                get() as Gson
            )
        }

        single<TransactionsRepository> {
            TransactionsRepositoryImpl(
                get() as JsonDeserializer
            )
        }

    }

}