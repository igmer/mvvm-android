package com.igmer.mancustomer.data.repository

import androidx.lifecycle.LiveData
import com.igmer.mancustomer.data.interfacesDao.IProductDao
import com.igmer.mancustomer.models.Product

class ProductRepository(private val productDao: IProductDao){

    fun getAllProducts() = productDao.getAllProducts()
    fun getProductById(id: Int) = productDao.getProductById(id)
    fun insertProduct(product: Product) = productDao.insertProduct(product)

}