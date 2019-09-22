package com.fearaujo.moneyou.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fearaujo.model.Response
import com.fearaujo.usecase.TransactionsUseCase

abstract class DashboardViewModel : ViewModel() {

    abstract fun observeTransactions(): LiveData<Response>

    abstract fun fetchTransactions()

}

class DashboardViewModelImpl(private val useCase: TransactionsUseCase) : DashboardViewModel() {

    override fun observeTransactions(): LiveData<Response> = useCase.observeTransactions()

    override fun fetchTransactions() {
        useCase.fetchTransactions()
    }


}