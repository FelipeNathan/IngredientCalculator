package com.ingredientcalculator.math

import android.util.Rational

fun Double.toRational(): Rational {

    val ds = this.toString().trimEnd('0').trimEnd('.')
    val index = ds.indexOf('.')

    if (index == -1) return Rational(ds.toInt(), 1)

    var num = ds.replace(".", "").toLong()
    var den = 1L
    for (n in 1 until ds.length - index) den *= 10L

    while (num % 2L == 0L && den % 2L == 0L) {
        num /= 2L
        den /= 2L
    }

    while (num % 5L == 0L && den % 5L == 0L) {
        num /= 5L
        den /= 5L
    }

    return Rational(num.toInt(), den.toInt())
}