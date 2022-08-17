package com.loicteyssierdev.bigburger.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loicteyssierdev.bigburger.model.Product
import com.loicteyssierdev.bigburger.util.CurrencyUtils


class CartViewModel : ViewModel() {

    private val _productsInCart = MutableLiveData<ArrayList<Product>>().apply {
        value = arrayListOf()
    }
    val productsInCart: LiveData<ArrayList<Product>> = _productsInCart


    fun addProductInCart(product: Product) {
        _productsInCart.value?.add(product)
        _productsInCart.value = _productsInCart.value
    }

    fun removeProductFromCart(product: Product) {
        _productsInCart.value?.remove(product)
    }

    fun getTotalPrice() = CurrencyUtils.priceInCentsToEuros(productsInCart.value?.sumOf {
        it.price
    } ?: 0)

}