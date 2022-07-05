package com.igmer.mancustomer.data.interfacesDao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.igmer.mancustomer.models.Customer

@Dao
interface ICustomerDao {
    @Query("SELECT * FROM customer")
    fun getAllCustomers(): List<Customer>

    @Query("SELECT * FROM customer WHERE id = :id")
     fun getCustomerById(id: Int): LiveData<Customer>

    @Insert
    fun insertCustomer(customer: Customer): Long
}