package com.igmer.mancustomer.data.interfacesDao

import androidx.room.Dao
import androidx.room.Insert
import com.igmer.mancustomer.models.Sale

@Dao
interface ISaleDao {
    @Insert
    fun insert(sale: Sale)
}