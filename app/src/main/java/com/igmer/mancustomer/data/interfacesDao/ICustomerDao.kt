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

    @Query("SELECT * FROM customer WHERE id = :id order by id DESC")
    suspend fun getCustomerById(id: Int): Customer

    @Insert
    fun insertCustomer(customer: Customer): Long
}