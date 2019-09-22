package com.fearaujo.data

import androidx.lifecycle.LiveData
import com.fearaujo.model.Response

interface TransactionsRepository {

    fun observeTransactions() : LiveData<Response>

    fun fetchTransactions(): LiveData<Response>

}