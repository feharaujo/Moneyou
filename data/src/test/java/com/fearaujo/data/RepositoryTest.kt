package com.fearaujo.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.fearaujo.data.di.RepositoryModule
import com.fearaujo.model.Response
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class RepositoryTest : KoinTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<Response>

    private val repository: TransactionsRepository by inject()

    @Before
    fun onSetup() {
        MockitoAnnotations.initMocks(this)

        startKoin {
            modules(RepositoryModule.module)
        }

        repository.observeTransactions().observeForever(observer)
    }

    @After
    fun onStop() {
        stopKoin()
    }

    @Test
    fun `on fetch receives data with success`() {
        repository.fetchTransactions()

        verify(observer, times(1)).onChanged(ArgumentMatchers.any(Response::class.java))
    }
}
