package com.igmer.mancustomer.ui.product

import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.igmer.mancustomer.R
import com.igmer.mancustomer.adapters.AdapterProducts
import com.igmer.mancustomer.databinding.FragmentProductsBinding
import com.igmer.mancustomer.interfaces.AddDialogListener
import com.igmer.mancustomer.models.Product
import com.igmer.mancustomer.ui.dialogs.AddProductItemDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private val productsViewModel: ProductsViewModel by viewModels()
    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!
    private lateinit var productAdapter: AdapterProducts

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(false)
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        setupRecyclerView()
        productsViewModel.getAllProductsObserver().observe(viewLifecycleOwner) {
            productAdapter.setProducts(it as ArrayList<Product>)
            productAdapter.notifyDataSetChanged()
        }
        productsViewModel.isLoading.observe(viewLifecycleOwner) {
            binding.loading.isVisible = it
        }
        val root: View = binding.root

        val fabAddProduct = binding.fabAddProduct

        fabAddProduct.setOnClickListener {
            AddProductItemDialog(
                object : AddDialogListener {
                    override fun onAddButtonClicked(item: Any) {
                        productsViewModel.insertProduct(item as Product)
                    }

                }
            ).show(childFragmentManager, "AddShoppingItemDialog")
        }
        return root
    }


    private fun setupRecyclerView() {
        //set margin top for recycler view
        binding.rvProducts.setPadding(
            0,
            resources.getDimensionPixelSize(R.dimen.margin_top_recycler_view),
            0,
            0
        )
        binding.rvProducts.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            productAdapter = AdapterProducts()
            adapter = productAdapter
            val divider = DividerItemDecoration(context, VERTICAL)
            addItemDecoration(divider)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}