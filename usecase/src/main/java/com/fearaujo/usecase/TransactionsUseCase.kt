package com.fearaujo.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.fearaujo.data.TransactionsRepository
import com.fearaujo.model.Response


interface TransactionsUseCase {

    fun observeTransactions(): LiveData<Response>

    fun fetchTransactions()

}

class TransactionsUseCaseImpl(private val repository: TransactionsRepository) : TransactionsUseCase {

    override fun observeTransactions(): LiveData<Response> = Transformations.map(
            repository.fetchTransactions(),
            ::orderTransactions
    )

    override fun fetchTransactions() {
        repository.fetchTransactions()
    }

    private fun orderTransactions(response: Response): Response {
        val sortedTransactions = response.transactions?.sortedByDescending { it.date }
        response.transactions = sortedTransactions

        return setInfoAmounts(response)
    }

    private fun setInfoAmounts(response: Response) : Response {
        val currentBalance = response.balance?.toFloat()

        currentBalance?.let {

            var amountTemp: Float = currentBalance

            response.transactions?.forEach {
                it.afterAmount = amountTemp
                amountTemp += ((it.amount?.toFloat() ?: 0f) * -1f)
                it.beforeAmount = amountTemp
            }

        }

        return response
    }

}