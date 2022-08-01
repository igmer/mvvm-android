package com.igmer.mancustomer.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.igmer.mancustomer.data.interfacesDao.ICustomerDao
import com.igmer.mancustomer.data.interfacesDao.IProductDao
import com.igmer.mancustomer.data.interfacesDao.ISaleDao
import com.igmer.mancustomer.models.Customer
import com.igmer.mancustomer.models.Product
import com.igmer.mancustomer.models.Sale

@Database(entities = [Product::class,Customer::class, Sale::class], version =   1)
abstract class RoomDatabaseLocal : RoomDatabase() {

    abstract fun productDao(): IProductDao
    abstract fun customerDao(): ICustomerDao
    abstract fun saleDao(): ISaleDao

}