package com.igmer.mancustomer.models

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "product")
data class Product(
    val name: String,
    val price: Double,
    val sale_price: Double,
    val description: String,
    val image: String
    ){

    @PrimaryKey(autoGenerate = true) var id: Int = 0

}