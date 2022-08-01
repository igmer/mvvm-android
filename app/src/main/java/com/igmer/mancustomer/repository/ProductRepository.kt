package com.igmer.mancustomer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.igmer.mancustomer.data.interfacesDao.IProductDao
import com.igmer.mancustomer.models.Product
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productDao: IProductDao){

    fun getAllProducts(): List<Product> {
        return productDao.getAllProducts()
    }fun getAllProductsLV(): LiveData<List<Product>> {
        return productDao.getAllProductsLV()
    }

    suspend fun getProductById(id: Int) = productDao.getProductById(id)
    fun insertProduct(product: Product) = productDao.insertProduct(product)

}