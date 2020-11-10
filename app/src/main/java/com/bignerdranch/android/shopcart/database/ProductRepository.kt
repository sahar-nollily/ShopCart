package com.bignerdranch.android.shopcart.database

import android.util.Log
import androidx.lifecycle.LiveData
import com.bignerdranch.android.shopcart.Product
import com.bignerdranch.android.shopcart.network.RemoteSource
import java.net.UnknownHostException

class ProductRepository(private val productDao: ProductDao,  private val remoteSource: RemoteSource){


//    suspend fun readAllProducts() {
//        try {
//            val response = remoteSource.readAllProducts()
//
//            if (response.isSuccessful) {
//                response.body()?.let { productDto ->
//                    productDao.addProduct(
//                        productDto.map {
//                            Product(
//                                productID = 0,
//                                productName = it.name,
//                                productPrice = it.price,
//                                productImg = it.image
//                            )
//                        }.toTypedArray()
//                    )
//                }
//            } else {
//                Log.d("ProductRepo", "Retrofit error")
//            }
//        } catch (e: UnknownHostException) {
//            //There is no internet connection
//            e.printStackTrace()
//        }
//    }

    fun getProducts() : LiveData<List<Product>> =productDao.getProducts()

    suspend fun addProduct(product: Product){
        productDao.addProduct(product)
    }
}