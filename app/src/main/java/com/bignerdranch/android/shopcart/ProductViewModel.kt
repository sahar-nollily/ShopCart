package com.bignerdranch.android.shopcart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel(){
    var productRepository = ServiceLocator.productRepository

    val getProducts : LiveData<List<Product>> =productRepository.getProducts()

    fun addProduct(product: Product){
        viewModelScope.launch {
            productRepository.addProduct(product)
        }
    }

    fun readAllProducts() {
        viewModelScope.launch {
            productRepository.readAllProducts()
        }
    }

}