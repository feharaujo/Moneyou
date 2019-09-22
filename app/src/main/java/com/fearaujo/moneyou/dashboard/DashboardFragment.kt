package com.fearaujo.moneyou.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fearaujo.model.Response
import com.fearaujo.moneyou.R
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {

    private val viewModel: DashboardViewModel by viewModel()
    private val adapter = DashboardAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeEvents()

        viewModel.fetchTransactions()
    }

    private fun setupRecyclerView() {
        val linearManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearManager
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
    }

    private fun observeEvents() {
        viewModel.observeTransactions().observe(this, Observer {
            it.transactions?.let { transactions ->
                adapter.submitData(transactions)
            }

            setHeaderInfo(it)
            stopLoadPresentData()
        })
    }

    private fun setHeaderInfo(it: Response) {
        accountNumber.text = getString(R.string.account_number, it.account)
        currentBalance.text = getString(R.string.current_amount, EURO, it.balance)
    }

    private fun stopLoadPresentData() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

}