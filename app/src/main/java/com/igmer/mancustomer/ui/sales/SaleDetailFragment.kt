package com.igmer.mancustomer.ui.sales

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.igmer.mancustomer.databinding.AdminCustomerFragmentBinding
import com.igmer.mancustomer.databinding.FragmentSaleDetailBinding
import com.igmer.mancustomer.models.Sale
import com.igmer.mancustomer.ui.MainActivity
import com.igmer.mancustomer.ui.customer.CustomerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class SaleDetailFragment : Fragment() {

    private val viewModel: SaleDetailViewModel by viewModels()
    lateinit var binding:  FragmentSaleDetailBinding

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
        binding.save.setOnClickListener{
            val product = binding.idProduct.text.toString()
            val quantity = binding.quantity.text.toString()
            val unitPrice = binding.price.text.toString()
            val total = binding.total.text.toString()
            val discount= binding.discoutnt.text.toString()
            val idCliente = id
            val sale = Sale(0,quantity.toInt(),unitPrice.toDouble(),"",product,id,"","")
        }

        return root
    }


}