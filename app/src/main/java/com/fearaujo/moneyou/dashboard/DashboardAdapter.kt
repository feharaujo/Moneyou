package com.fearaujo.moneyou.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fearaujo.model.Transaction
import com.fearaujo.moneyou.R

class DashboardAdapter : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    private val transactions = mutableListOf<Transaction>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = transactions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(transactions[position])
    }

    fun submitData(newItems: List<Transaction>) {
        transactions.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(transaction: Transaction) {
            bindId(transaction)
            bindAmount(transaction)
            bindDescription(transaction)
            bindOtherAcc(transaction)
            bindDate(transaction)
            bindBalanceBefore(transaction)
            bindBalanceAfter(transaction)
        }

        private fun bindBalanceAfter(transaction: Transaction) {
            val value = String.format(NUMBER_FORMAT, transaction.afterAmount)
            itemView.findViewById<TextView>(R.id.balanceAfter).text =
                    itemView.context.getString(R.string.after, EURO, value)
        }

        private fun bindBalanceBefore(transaction: Transaction) {
            val value = String.format(NUMBER_FORMAT, transaction.beforeAmount)
            itemView.findViewById<TextView>(R.id.balanceBefore).text =
                    itemView.context.getString(R.string.before, EURO, value)
        }

        private fun bindDate(transaction: Transaction) {
            itemView.findViewById<TextView>(R.id.date).text = transaction.description
        }

        private fun bindOtherAcc(transaction: Transaction) {
            itemView.findViewById<TextView>(R.id.otherAcc).text = transaction.otherAccount
        }

        private fun bindDescription(transaction: Transaction) {
            itemView.findViewById<TextView>(R.id.description).text = transaction.description
        }

        private fun bindAmount(transaction: Transaction) {
            itemView.findViewById<TextView>(R.id.amount).text =
                    itemView.context.getString(R.string.amount, EURO, transaction.amount)
        }

        private fun bindId(transaction: Transaction) {
            itemView.findViewById<TextView>(R.id.transaction_id).text = transaction.id
        }

    }
}