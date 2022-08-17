package com.loicteyssierdev.bigburger.util

import com.loicteyssierdev.bigburger.model.priceInEuro
import java.text.DecimalFormat

object CurrencyUtils {
    fun priceInCentsToEuros(price: Int): String {
        val priceInEuro = price.toDouble() / 100
        return "${DecimalFormat("#.00").format(priceInEuro)}€"
    }
}