package com.ingredientcalculator.entities

import android.util.Rational
import androidx.annotation.DrawableRes
import com.ingredientcalculator.R
import com.ingredientcalculator.math.toRational

class Fraction(
    private val decimal: Double
) {

    private val _rational: Rational = decimal.toRational()
    private val _representation: Representation = findRepresentation()

    val rational: Rational
        get() = _rational

    val representation: Representation
        get() = _representation

    private fun findRepresentation(): Representation {
        if (decimal > 1.0) return Representation.FULL

        val lastIndexPossibleState = Representation.values().indexOfFirst { decimal <= it.amount }
        if (lastIndexPossibleState == 0) return Representation.EMPTY

        val actualState = Representation.values()[lastIndexPossibleState]
        val previousState = Representation.values()[lastIndexPossibleState - 1]

        return if ((decimal - previousState.amount) <= actualState.amount - decimal) {
            previousState
        } else {
            actualState
        }
    }

    enum class Representation(val amount: Double, @DrawableRes val resource: Int) {
        EMPTY(0.0, R.drawable.ic_cup_0),
        ONE_QUARTER(0.25, R.drawable.ic_cup_1_4),
        ONE_THIRD(0.33, R.drawable.ic_cup_1_3),
        HALF(0.5, R.drawable.ic_cup_1_2),
        TWO_THIRDS(0.66, R.drawable.ic_cup_2_3),
        THREE_QUARTERS(0.75, R.drawable.ic_cup_3_4),
        FULL(1.0, R.drawable.ic_cup_1);
    }
}