package com.igmer.mancustomer.ui.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.igmer.mancustomer.adapters.AdapterCustomer
import com.igmer.mancustomer.databinding.FragmentCustomerBinding
import com.igmer.mancustomer.interfaces.AddDialogListener
import com.igmer.mancustomer.interfaces.GoToActivity
import com.igmer.mancustomer.models.Customer
import com.igmer.mancustomer.ui.dialogs.AddCustomerItemDialog
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController
import com.igmer.mancustomer.R

@AndroidEntryPoint
class CustomerFragment : Fragment() {

    private lateinit var customerAdapter: AdapterCustomer
    private var _binding: FragmentCustomerBinding? = null
    private val viewModel: CustomerViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCustomerBinding.inflate(inflater, container, false)
        setupRecyclerView()
        viewModel.getAllCustomerObserver().observe(viewLifecycleOwner) {
            customerAdapter.setCustomers(it as ArrayList<Customer>)
            customerAdapter.notifyDataSetChanged()
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.loading.isVisible = it
        }
        binding.fabAdd.setOnClickListener{
            AddCustomerItemDialog(
                object : AddDialogListener{
                    override fun onAddButtonClicked(item: Any) {
                        viewModel.insertCustomer(item as Customer)
                    }

                }
            ).show(childFragmentManager, "Add Customer")
        }
        val root: View = binding.root

        return root
    }

    private fun setupRecyclerView() {

        binding.rv.apply {
            layoutManager = LinearLayoutManager(context)
            customerAdapter = AdapterCustomer(
                object : GoToActivity {
                    override fun goToActivity(id: Int) {
                        val bundle = bundleOf("id" to id)
                        findNavController().navigate(R.id.action_customer_to_admin, bundle)
                    }
                }
            )
            adapter = customerAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}