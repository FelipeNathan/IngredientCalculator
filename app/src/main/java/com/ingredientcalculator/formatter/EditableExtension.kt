package com.ingredientcalculator.formatter

import android.text.Editable
import java.math.BigDecimal

fun Editable?.valueOrZero(): BigDecimal {
    val value = (this?.ifBlank { "0" } ?: "0").toString().replace(",", ".")
    return try {
        BigDecimal(value)
    } catch (ex: NumberFormatException) {
        BigDecimal("0$value")
    }
}