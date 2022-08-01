package com.igmer.mancustomer.ui.sales

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.igmer.mancustomer.databinding.AdminCustomerFragmentBinding
import com.igmer.mancustomer.databinding.FragmentSaleDetailBinding
import com.igmer.mancustomer.models.Product
import com.igmer.mancustomer.models.Sale
import com.igmer.mancustomer.ui.MainActivity
import com.igmer.mancustomer.ui.customer.CustomerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class SaleDetailFragment : Fragment() {

    private val viewModel: SaleDetailViewModel by viewModels()
    lateinit var binding:  FragmentSaleDetailBinding
    private var productSelected: Product? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaleDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val id = arguments?.getInt("id")
        viewModel.getCustomerById(id!!).observe(viewLifecycleOwner){
            binding.tvCustomerName.text = it.name
        }
        viewModel.productList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding.idProduct.setAdapter(context?.let { it1 -> ArrayAdapter(it1, android.R.layout.simple_spinner_dropdown_item, it) })
        })

       binding.idProduct.setOnItemClickListener { parent, view, position, id ->
            val selected = parent.getItemAtPosition(position) as Product
            productSelected = selected
        }

        binding.save.setOnClickListener{
            val isValid = validate()
            if (isValid) {
                val currentTime = Calendar.getInstance().time
                val dateFormated = SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault()).format(currentTime)
                val product = binding.idProduct.text.toString()
                val quantity = binding.quantity.text.toString()
                val unitPrice = binding.price.text.toString()
                val total = binding.total.text.toString()
                val discount = binding.discoutnt.text.toString()
                val idCliente = id
                val sale = Sale(0, quantity.toInt(), unitPrice.toDouble(), dateFormated, 1, id, "Efectivo")
                viewModel.savesale(sale)
            }
        }

        return root
    }

    private fun validate(): Boolean {
        var isValid = true;
        if (binding.quantity.text.isNullOrEmpty()){
            binding.quantity.error = "Ingrese una cantidad"
            isValid = false
        }
        if (binding.price.text.isNullOrEmpty()){
            binding.price.error = "Ingrese un precio"
            isValid = false
        }
        if(productSelected == null){
            binding.idProduct.error = "Ingrese un producto"
            isValid = false
        }

        return isValid

    }


}