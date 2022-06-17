package com.igmer.mancustomer.ui.customer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.igmer.mancustomer.models.Customer
import com.igmer.mancustomer.repository.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class CustomerViewModel @Inject constructor(private val repository: CustomerRepository) :
    ViewModel() {
    private var allCustomers: MutableLiveData<List<Customer>> = MutableLiveData()
    val isLoading = MutableLiveData<Boolean>()
    init {
        getAllCustomer()
    }

    fun getAllCustomerObserver(): MutableLiveData<List<Customer>> {
        return allCustomers
    }

    private fun getAllCustomer() {
        isLoading.postValue(true)
        val customer = repository.getAllCustomer()
        allCustomers.postValue(customer)
        isLoading.postValue(false)
    }
    fun insertCustomer(customer: Customer) {
        isLoading.postValue(true)
        repository.insertCustomer(customer)
        getAllCustomer()
        isLoading.postValue(false)
    }
}