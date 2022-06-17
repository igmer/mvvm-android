package com.igmer.mancustomer.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.igmer.mancustomer.R
import com.igmer.mancustomer.databinding.ItemCostumerBinding
import com.igmer.mancustomer.models.Customer

class AdapterCustomer() : RecyclerView.Adapter<AdapterCustomer.ViewHolder>() {
    private lateinit var context: Context
    private  var customers= ArrayList<Customer>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = View.inflate(context, R.layout.item_costumer, null)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return customers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = customers[position]
        with(holder) {
            binding.customer = item
        }
    }


    fun setCustomers(item: ArrayList<Customer>) {
        customers = item
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemCostumerBinding.bind(itemView)
    }
}