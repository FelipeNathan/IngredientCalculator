package com.ingredientcalculator.service

import com.ingredientcalculator.entities.Fraction

class ConvertService {

    fun calculate(amount: Double): Fraction {
        return Fraction(amount)
    }
}