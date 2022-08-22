package com.igmer.mancustomer.repository

import com.igmer.mancustomer.data.interfacesDao.ISaleDao
import com.igmer.mancustomer.models.Sale
import javax.inject.Inject

class SaleRepository @Inject constructor(private val saleDao: ISaleDao) {
    fun insert(sale: Sale): Long {
        return saleDao.insert(sale)
    }

    fun getSalesByIdCustomerAndDate(idCustomer: Int, date: String): List<Sale> {
            return saleDao.getSalesByIdCustomerAndDate(idCustomer, date)
    }
}
