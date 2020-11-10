package com.bignerdranch.android.shopcart

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(@PrimaryKey(autoGenerate = true) val productID : Int = 0,
                   var productName : String = "",
                   var productPrice : Double = 0.0,
                   var productImg : String = ""
)