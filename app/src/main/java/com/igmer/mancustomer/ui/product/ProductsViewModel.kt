package com.igmer.mancustomer.ui.product

import android.app.Application
import androidx.lifecycle.*
import com.igmer.mancustomer.data.RoomDatabaseLocal
import com.igmer.mancustomer.repository.ProductRepository
import com.igmer.mancustomer.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {
    private var allProducts: MutableLiveData<List<Product>> = MutableLiveData()
    val isLoading = MutableLiveData<Boolean>()
    init {
        getAllProducts()
    }

    fun getAllProductsObserver(): MutableLiveData<List<Product>> {
        return allProducts
    }

    fun getAllProducts() {
        val products = productRepository.getAllProducts()
        allProducts.postValue(products)
        isLoading.postValue(false)
    }

    fun insertProduct(product: Product) {
        isLoading.postValue(true)
        productRepository.insertProduct(product)
        getAllProducts()
        isLoading.postValue(false)

    }

}