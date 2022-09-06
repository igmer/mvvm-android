package com.igmer.mancustomer.ui.sales

import android.text.BoringLayout
import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.igmer.mancustomer.models.Customer
import com.igmer.mancustomer.models.Product
import com.igmer.mancustomer.models.Sale
import com.igmer.mancustomer.models.embedded.SaleProduct
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
                                              repositoryProduct: ProductRepository,
                                              private val repositorySale: SaleRepository
) :
    ViewModel() {
    private lateinit var lvCustomer: LiveData<Customer>
    private val allSale: MutableLiveData<List<SaleProduct>> = MutableLiveData()
    var productList: LiveData<List<Product>> = repositoryProduct.getAllProductsLV()

    fun getAllSaleObserver(): MutableLiveData<List<SaleProduct>>{
        return allSale
    }
    fun onQuantityChanged(text: CharSequence) {
        // log text change events
        println("onQuantityChanged: ${text.toString()}")


    }
    fun getCustomerById(id: Int): LiveData<Customer> {
        lvCustomer = repositoryCustomer.getCustomerById(id)
        return lvCustomer
    }
 fun getAllSaleByIdCustomerAndDate(id: Int, date: String){
    val sales = repositorySale.getSalesByIdCustomerAndDateDetail(id, date)
    allSale.postValue(sales)
}
    fun savesale(sale: Sale,idCustomer: Int, today: String) : Long {
        val id =  repositorySale.insert(sale)
        getAllSaleByIdCustomerAndDate(idCustomer,today)
        return id;
    }
}