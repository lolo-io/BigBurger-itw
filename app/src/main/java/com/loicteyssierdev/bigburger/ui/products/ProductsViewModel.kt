package com.loicteyssierdev.bigburger.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loicteyssierdev.bigburger.model.Product

class ProductsViewModel : ViewModel() {

    private val _products = MutableLiveData<ArrayList<Product>>().apply {
        value = arrayListOf()
    }
    val products: LiveData<ArrayList<Product>> = _products

    fun fetchProducts() {
        _products.value?.clear()
        _products.value?.addAll(
            arrayListOf(
                Product("Big Burger"),
                Product("The Big Cheese Burger"),
                Product("The Big Bacon Burger")
            )
        )
        _products.value = _products.value // for now this is sync
    }

}