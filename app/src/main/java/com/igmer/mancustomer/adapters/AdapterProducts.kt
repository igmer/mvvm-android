package com.igmer.mancustomer.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.igmer.mancustomer.R
import com.igmer.mancustomer.databinding.ItemProductBinding
import com.igmer.mancustomer.models.Product

class AdapterProducts() : RecyclerView.Adapter<AdapterProducts.ViewHolder>() {
    private lateinit var context: Context
    private  var products= ArrayList<Product>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = View.inflate(context, R.layout.item_product, null)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        with(holder) {
            binding.product = product
        }
    }


    fun setProducts(productos: ArrayList<Product>) {
        products = productos
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemProductBinding.bind(itemView)
    }
}