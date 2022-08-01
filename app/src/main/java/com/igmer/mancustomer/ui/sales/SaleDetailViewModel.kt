package com.igmer.mancustomer.ui.sales

import android.text.BoringLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.igmer.mancustomer.models.Customer
import com.igmer.mancustomer.models.Product
import com.igmer.mancustomer.models.Sale
import com.igmer.mancustomer.repository.CustomerRepository
import com.igmer.mancustomer.repository.ProductRepository
import com.igmer.mancustomer.repository.SaleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaleDetailViewModel @Inject constructor(private val repositoryCustomer: CustomerRepository,
                                              private val repositoryProduct: ProductRepository,
                                              private val repositorySale: SaleRepository
) :
    ViewModel() {
    private lateinit var lvCustomer: LiveData<Customer>
    var productList: LiveData<List<Product>> = repositoryProduct.getAllProductsLV()

    fun getCustomerById(id: Int): LiveData<Customer> {
        lvCustomer = repositoryCustomer.getCustomerById(id)
        return lvCustomer
    }

    fun savesale(sale: Sale) {
        repositorySale.insert(sale)
    }
}