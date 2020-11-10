package com.bignerdranch.android.shopcart

import android.content.Context
import androidx.room.Room
import com.bignerdranch.android.shopcart.database.ProductDatabase
import com.bignerdranch.android.shopcart.database.ProductRepository
import com.bignerdranch.android.shopcart.network.RemoteSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceLocator {
    private lateinit var app: App
    lateinit var db: ProductDatabase
    lateinit var retrofit: Retrofit
    private lateinit var productRemoteSource: RemoteSource

    fun init(app: App) {
        this.app = app
        initializeDatabase(app)
        initializeNetwork(app)
    }

    private fun getOkhttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private fun initializeNetwork(context: Context) {
        retrofit = Retrofit.Builder()
            .baseUrl("https://safcsp-faker-api.herokuapp.com/")
            .client(getOkhttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        productRemoteSource = retrofit.create(RemoteSource::class.java)
    }

    private fun initializeDatabase(context: Context) {
        db = Room.databaseBuilder(
            context,
            ProductDatabase::class.java,
            "product_db"
        ).build()
    }

    val productRepository: ProductRepository by lazy {
        ProductRepository(db.productDao(),productRemoteSource)
    }
}