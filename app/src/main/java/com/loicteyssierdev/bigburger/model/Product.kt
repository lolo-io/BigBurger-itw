package com.loicteyssierdev.bigburger.model

data class Product(
    val title: String = "",
    val description: String = "",
    val price: Int = 0,
    val ref: String = "",
    val thumbnail: String = ""
)