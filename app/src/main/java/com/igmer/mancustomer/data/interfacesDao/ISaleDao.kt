package com.igmer.mancustomer.data.interfacesDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.igmer.mancustomer.models.Sale

@Dao
interface ISaleDao {
    @Insert
    fun insert(sale: Sale): Long

    @Query("SELECT * FROM sales WHERE id_customer = :idCustomer and date = :date")
    fun getSalesByIdCustomerAndDate(idCustomer: Int, date: String): List<Sale>
}