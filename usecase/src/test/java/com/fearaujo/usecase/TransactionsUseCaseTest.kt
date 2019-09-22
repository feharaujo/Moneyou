package com.fearaujo.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fearaujo.data.di.RepositoryModule
import com.fearaujo.usecase.di.UseCaseModule
import com.jraska.livedata.test
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.MockitoAnnotations


class TransactionsUseCaseTest : KoinTest {

    private companion object {
        const val BEFORE_AMOUNT_ITEM_0 = 118.399994f
        const val AFTER_AMOUNT_ITEM_0 = 100.2f

        const val BEFORE_AMOUNT_ITEM_4 = 849.7f
        const val AFTER_AMOUNT_ITEM_4 = 845.4f

        const val FIRST_ID = "trx1"
        const val LAST_ID = "trx5"
    }

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val useCase: TransactionsUseCase by inject()

    @Before
    fun onSetup() {
        MockitoAnnotations.initMocks(this)

        startKoin {
            modules(listOf(
                    RepositoryModule.module,
                    UseCaseModule.module
            ))
        }
    }

    @After
    fun onStop() {
        stopKoin()
    }

    @Test
    fun `on fetch data should order it by date`() {
        useCase.observeTransactions()
                .test()
                .awaitValue()
                .assertValue { it!!.transactions!![0].id == FIRST_ID }
                .assertValue { it!!.transactions!![4].id == LAST_ID }

        useCase.fetchTransactions()

    }

    @Test
    fun `on fetch data should set the before and after amounts`() {
        useCase.observeTransactions()
                .test()
                .awaitValue()
                .assertValue { it!!.transactions!![0].beforeAmount == BEFORE_AMOUNT_ITEM_0 }
                .assertValue { it!!.transactions!![0].afterAmount == AFTER_AMOUNT_ITEM_0 }
                .assertValue { it!!.transactions!![4].beforeAmount == BEFORE_AMOUNT_ITEM_4 }
                .assertValue { it!!.transactions!![4].afterAmount == AFTER_AMOUNT_ITEM_4 }

        useCase.fetchTransactions()

    }
}
