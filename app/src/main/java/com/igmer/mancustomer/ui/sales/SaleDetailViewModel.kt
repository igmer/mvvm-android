package com.igmer.mancustomer.ui.sales

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.igmer.mancustomer.models.Customer
import com.igmer.mancustomer.repository.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class SaleDetailViewModel @Inject constructor(private val repository: CustomerRepository) : ViewModel() {
    private lateinit var lvCustomer: LiveData<Customer>

     fun getCustomerById(id: Int): LiveData<Customer> {
         lvCustomer = repository.getCustomerById(id)
         return lvCustomer
    }
}