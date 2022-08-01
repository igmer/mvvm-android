package com.igmer.mancustomer.di

import android.content.Context
import androidx.room.Room
import com.igmer.mancustomer.data.RoomDatabaseLocal
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private val DATABASE="customer_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(context,
        RoomDatabaseLocal::class.java,DATABASE).allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideProductDao(db:RoomDatabaseLocal) = db.productDao()
    @Singleton
    @Provides
    fun provideCustomerDao(db:RoomDatabaseLocal) = db.customerDao()

    @Singleton
    @Provides
    fun provideSalesDao(db:RoomDatabaseLocal) = db.saleDao()
}