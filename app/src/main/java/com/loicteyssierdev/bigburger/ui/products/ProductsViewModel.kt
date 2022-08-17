package com.loicteyssierdev.bigburger.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loicteyssierdev.bigburger.api.ApiInstance
import com.loicteyssierdev.bigburger.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {

    private val _products = MutableLiveData<ArrayList<Product>>().apply {
        value = arrayListOf()
    }
    val products: LiveData<ArrayList<Product>> = _products

    fun fetchProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            ApiInstance.getProducts {
                _products.value?.clear()
                _products.value?.addAll(it)
                _products.postValue(_products.value)
            }
        }
    }

}