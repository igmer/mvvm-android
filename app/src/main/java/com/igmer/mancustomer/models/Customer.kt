package com.igmer.mancustomer.models

import androidx.room.Entity

@Entity(tableName = "customer")
data class Customer(
    val id: Int,
    val name: String,
    val phone: String,
    val address: String,
    val email: String)
