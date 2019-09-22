package com.fearaujo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fearaujo.model.Response

class TransactionsRepositoryImpl(
    private val deserializer: JsonDeserializer
) : TransactionsRepository {

    private companion object {
        const val FILE_NAME = "transactions.json"
    }

    private val transactionsLiveData = MutableLiveData<Response>()

    override fun observeTransactions(): LiveData<Response> = transactionsLiveData

    override fun fetchTransactions() : LiveData<Response> {
        val response = deserializer.deserializeFile(FILE_NAME)
        transactionsLiveData.value = response

        return transactionsLiveData
    }
}