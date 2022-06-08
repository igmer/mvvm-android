package com.igmer.mancustomer.ui.product

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.igmer.mancustomer.adapters.AdapterProducts
import com.igmer.mancustomer.databinding.FragmentProductsBinding
import com.igmer.mancustomer.models.Product

class ProductsFragment : Fragment() {

    private lateinit var notificationsViewModel: ProductsViewModel
    private var _binding: FragmentProductsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var productAdapter: AdapterProducts

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         notificationsViewModel =
            ViewModelProvider(requireActivity()).get(ProductsViewModel::class.java)

        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val fabAddProduct = binding.fabAddProduct
        setupRecyclerView()
        setAllProducts()

        fabAddProduct.setOnClickListener {
            notificationsViewModel.insert(Product("Descripcion", 12.2, 1.0, "Modelo", "Color"))
        }
        return root
    }
    private fun getAllProducts(): MutableLiveData<List<Product>> {
        return notificationsViewModel.allProducts
    }

    private fun setupRecyclerView() {
        productAdapter = AdapterProducts()
        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productAdapter
        }
    }
    private fun setAllProducts(){
        val products = getAllProducts()
        products.observe(viewLifecycleOwner, Observer { products ->
            productAdapter.setProducts(products)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}