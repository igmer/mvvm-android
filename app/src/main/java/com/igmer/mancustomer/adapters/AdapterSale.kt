package com.igmer.mancustomer.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.igmer.mancustomer.R
import com.igmer.mancustomer.databinding.ItemProductBinding
import com.igmer.mancustomer.databinding.ItemSaleBinding
import com.igmer.mancustomer.models.Product
import com.igmer.mancustomer.models.Sale

class AdapterSale() : RecyclerView.Adapter<AdapterSale.ViewHolder>() {
    private lateinit var context: Context
    private  var sales= ArrayList<Sale>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = View.inflate(context, R.layout.item_sale, null)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return sales.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sale = sales[position]
        with(holder) {
            binding.sale = sale
        }
    }


    fun setProducts(sale: ArrayList<Sale>) {
        sales = sale
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemSaleBinding.bind(itemView)
    }
}