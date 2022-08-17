package com.loicteyssierdev.bigburger.model

import java.text.DecimalFormat

data class Product(
    val title: String = "",
    val description: String = "",
    val price: Int = 0,
    val ref: String = "",
    val thumbnail: String = ""
)

val Product.priceInEuro: String
    get() {
        val priceInEuro = this.price.toDouble() / 100
        return DecimalFormat("#.00").format(priceInEuro)
    }
