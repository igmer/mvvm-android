package com.igmer.mancustomer.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "sales")
data class Sale(@PrimaryKey(autoGenerate = true) val id: Int,
                val quantity: Int,
                val price: Double,
                val date: String,
                val total: Double,
                val discount: Double,
                @ColumnInfo(name = "id_product")
                val idProduct: Int,
                @ColumnInfo(name = "id_customer")
                val idCustomer: Int,
                @ColumnInfo(name = "sale_type")
                val saleType: String)

