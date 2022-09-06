package com.igmer.mancustomer.models.embedded

import androidx.room.Embedded
import androidx.room.Entity


data class SaleProduct(
    val quantity: Int,
    val price: Double,
    val date: String,
    val total: Double,
    val discount: Double,
    val product: String,
    val description: String
)
