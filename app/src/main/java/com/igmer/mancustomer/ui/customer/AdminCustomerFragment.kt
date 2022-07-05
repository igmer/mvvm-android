package com.igmer.mancustomer.ui.customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.igmer.mancustomer.R
import com.igmer.mancustomer.databinding.AdminCustomerFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminCustomerFragment : Fragment() {
    lateinit var binding: AdminCustomerFragmentBinding
    private val viewModel: AdminCustomerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AdminCustomerFragmentBinding.inflate(inflater, container, false)
        val id = arguments?.getInt("id")
        viewModel.getCustomerById(id!!).observe(viewLifecycleOwner){
            binding.tvCustomerName.text = it.name
        }
        binding.cvVender.setOnClickListener {
            val action = AdminCustomerFragmentDirections.actionAdminToSaleDetail(id)
            findNavController().navigate(action)
        }
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}