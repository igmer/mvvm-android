package com.igmer.mancustomer.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "sales")
data class Sale(@PrimaryKey val id: Int,
                @Embedded val customer: Customer,
                @Embedded val product: Product,
                val quantity: Int,
                val price: Double,
                val date: String)
