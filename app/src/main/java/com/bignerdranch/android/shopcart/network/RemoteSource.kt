package com.bignerdranch.android.shopcart.network

import com.bignerdranch.android.shopcart.Product
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET

interface RemoteSource{
    @GET("faker/api/v1/product")
    suspend fun readAllProducts(): Response<List<ProductDto>>
}

data class ProductDto(
    val name: String,
    @SerializedName("net_price")
    val price: Double,
    val image: String
)