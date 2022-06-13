package com.igmer.mancustomer.models

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "product")
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val purchase_price: Double,
    val sale_price: Double,
    val description: String,
    val image: String,
    val stock: Int
    ){



}