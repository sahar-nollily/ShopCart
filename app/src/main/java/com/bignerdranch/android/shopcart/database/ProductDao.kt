package com.bignerdranch.android.shopcart.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bignerdranch.android.shopcart.Product

@Dao
interface ProductDao{
    @Query("SELECT * FROM Product")
    fun getProducts():LiveData<List<Product>>

    @Insert
    suspend fun addProduct(product: Product)
}