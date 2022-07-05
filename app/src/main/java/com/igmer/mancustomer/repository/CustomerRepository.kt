package com.igmer.mancustomer.repository

import androidx.lifecycle.MutableLiveData
import com.igmer.mancustomer.data.interfacesDao.ICustomerDao
import com.igmer.mancustomer.data.interfacesDao.IProductDao
import com.igmer.mancustomer.models.Customer
import com.igmer.mancustomer.models.Product
import javax.inject.Inject

class CustomerRepository @Inject constructor(private val dao: ICustomerDao) {

    fun getAllCustomer(): List<Customer> {
        return dao.getAllCustomers()
    }
     fun getCustomerById(id: Int) = dao.getCustomerById(id)
    fun insertCustomer(item: Customer) = dao.insertCustomer(item)

}