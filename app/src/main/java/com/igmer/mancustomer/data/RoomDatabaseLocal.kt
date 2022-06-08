package com.igmer.mancustomer.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.igmer.mancustomer.data.interfacesDao.IProductDao
import com.igmer.mancustomer.models.Product
@Database(entities = [Product::class], version = 1)
abstract class RoomDatabaseLocal : RoomDatabase() {

        abstract fun productDao(): IProductDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDatabaseLocal? = null
        fun getDatabase(context: android.content.Context): RoomDatabaseLocal {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDatabaseLocal::class.java,
                    "sales_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }

}