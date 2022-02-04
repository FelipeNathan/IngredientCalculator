package com.ingredientcalculator.service

import java.math.BigDecimal
import java.math.RoundingMode

class CalculatorService {

    fun calculateAndFormat(price: BigDecimal, realValue: BigDecimal, usedValue: BigDecimal): String {
        return try {
            val result = (price * usedValue)
                .divide(realValue, 2, RoundingMode.HALF_UP)
                .toString()
                .replace(".", ",")
            "R$ $result"
        } catch (ex: Exception) {
            "R$ 0,00"
        }
    }
}