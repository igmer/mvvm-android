package com.igmer.mancustomer.data.interfacesDao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.igmer.mancustomer.models.Product

@Dao
interface IProductDao {
    @Query("SELECT * FROM product")
    fun getAllProducts(): List<Product>
    @Query("SELECT * FROM product WHERE id = :id")
    fun getProductById(id: Int): LiveData<Product>
    @Insert
    fun insertProduct(product: Product) : Long
}