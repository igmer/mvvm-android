package com.igmer.mancustomer.ui.product

import android.app.Application
import androidx.lifecycle.*
import androidx.room.RoomDatabase
import com.igmer.mancustomer.data.RoomDatabaseLocal
import com.igmer.mancustomer.data.repository.ProductRepository
import com.igmer.mancustomer.models.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ProductRepository
     val allProducts = MutableLiveData<List<Product>>()

    init {
        val productoDao = RoomDatabaseLocal.getDatabase(application).productDao()
        repository = ProductRepository(productoDao)
        allProducts.postValue(repository.getAllProducts())

    }

    fun insert(product: Product)  = viewModelScope.launch(Dispatchers.IO) {
        repository.insertProduct(product)
    }

}