package com.bignerdranch.android.shopcart.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bignerdranch.android.shopcart.Product

@Database(entities = [Product::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

}