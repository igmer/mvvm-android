package com.igmer.mancustomer.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val phone: String,
    val address: String?,
    val email: String?)
