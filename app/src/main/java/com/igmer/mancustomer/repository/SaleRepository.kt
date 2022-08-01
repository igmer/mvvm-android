package com.igmer.mancustomer.repository

import com.igmer.mancustomer.data.interfacesDao.ISaleDao
import com.igmer.mancustomer.models.Sale
import javax.inject.Inject

class SaleRepository @Inject constructor(private val saleDao: ISaleDao) {
    fun insert(sale: Sale) = saleDao.insert(sale)
}
