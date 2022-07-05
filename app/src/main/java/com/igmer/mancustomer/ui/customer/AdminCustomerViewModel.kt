package com.igmer.mancustomer.ui.customer

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.igmer.mancustomer.models.Customer
import com.igmer.mancustomer.repository.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class AdminCustomerViewModel @Inject constructor(private val repository: CustomerRepository) : ViewModel() {
    private lateinit var lvCustomer: LiveData<Customer>

    fun getCustomerById(id: Int): LiveData<Customer> {
        lvCustomer = repository.getCustomerById(id)
        return lvCustomer
    }


}