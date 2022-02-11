package com.ingredientcalculator.service

import androidx.annotation.DrawableRes
import com.ingredientcalculator.R

class ConvertService {

    fun calculate(amount: Double): State {
        return getClosiestStateOfValue(amount)
    }

    private fun getClosiestStateOfValue(value: Double): State {
        if (value > 1.0) return State.FULL

        val lastIndexPossibleState = State.values().indexOfFirst { value <= it.amount }
        if (lastIndexPossibleState == 0) return State.EMPTY

        val actualState = State.values()[lastIndexPossibleState]
        val previousState = State.values()[lastIndexPossibleState - 1]

        return if ((value - previousState.amount) <= actualState.amount - value) {
            previousState
        } else {
            actualState
        }
    }


    enum class State(val amount: Double, @DrawableRes val resource: Int, val text: String) {

        EMPTY(0.0, R.drawable.ic_milk, "0"),
        ONE_EIGHTH(0.125, R.drawable.ic_milk_1_4, "1/8"),
        ONE_QUARTER(0.25, R.drawable.ic_milk_1_4, "1/4"),
        ONE_THIRD(0.33, R.drawable.ic_milk_1_3, "1/3"),
        HALF(0.5, R.drawable.ic_milk_1_2, "1/2"),
        TWO_THIRDS(0.66, R.drawable.ic_milk_2_3, "2/3"),
        THREE_QUARTERS(0.75, R.drawable.ic_milk_3_4, "3/4"),
        FULL(1.0, R.drawable.ic_milk_full, "1");
    }
}