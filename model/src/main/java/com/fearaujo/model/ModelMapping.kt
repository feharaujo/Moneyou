package com.fearaujo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Response(
    var account: String?,
    var balance: String?,
    var transactions: List<Transaction>?
) : Parcelable

@Parcelize
data class Transaction(
    var id: String?,
    var amount: String?,
    var description: String?,
    var otherAccount: String?,
    var date: Date?,
    @Transient var beforeAmount: Float,
    @Transient var afterAmount: Float
) : Parcelable