package com.igmer.mancustomer.data.interfacesDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.igmer.mancustomer.models.Sale
import com.igmer.mancustomer.models.embedded.SaleProduct

@Dao
interface ISaleDao {
    @Insert
    fun insert(sale: Sale): Long

    @Query("SELECT * FROM sales WHERE id_customer = :idCustomer and date = :date ORDER BY id DESC")
    fun getSalesByIdCustomerAndDate(idCustomer: Int, date: String): List<Sale>

    @Query("SELECT p.name AS product,p.description,s.date,s.quantity,s.total,s.discount,s.sale_type,s.price FROM sales s " +
            "INNER JOIN product p on p.id=s.id_product "+
            "WHERE id_customer = :idCustomer and date = :date ORDER BY s.id DESC")
    fun getSalesByIdCustomerAndDateDetail(idCustomer: Int, date: String): List<SaleProduct>
}