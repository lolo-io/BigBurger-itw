package com.loicteyssierdev.bigburger.model

import com.loicteyssierdev.bigburger.util.CurrencyUtils

data class Product(
    val title: String = "",
    val description: String = "",
    val price: Int = 0,
    val ref: String = "",
    val thumbnail: String = ""
)

val Product.priceInEuro: String
    get() = CurrencyUtils.priceInCentsToEuros(this.price)
