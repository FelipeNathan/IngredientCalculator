package com.ingredientcalculator.formatter

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText
import java.math.BigDecimal
import java.text.DecimalFormat

class DecimalWatcher(
    private val editText: TextInputEditText,
    private val scale: Int
) : TextWatcher {

    private val decimal = "".padEnd(scale, '0')

    override fun beforeTextChanged(
        charSequence: CharSequence?,
        start: Int,
        count: Int,
        after: Int
    ) {
    }

    override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(editable: Editable) {
        val value =
            BigDecimal(clearString(editable.toString()))
                .divide(
                    BigDecimal("1$decimal"),
                    scale,
                    BigDecimal.ROUND_FLOOR
                )

        val format = "0.$decimal"
        val formatted = DecimalFormat(format).format(value).replace(".", ",")

        editText.removeTextChangedListener(this)
        editText.setText(formatted)
        editText.setSelection(formatted.length)
        editText.addTextChangedListener(this)
    }

    private fun clearString(text: String): String {
        if (text.isBlank())
            return "0"

        return text.replace(Regex("\\D"), "")
    }

    companion object {
        fun build3Decimal(editText: TextInputEditText): TextWatcher {
            return DecimalWatcher(editText, 3)
        }

        fun build2Decimal(editText: TextInputEditText): TextWatcher {
            return DecimalWatcher(editText, 2)
        }
    }
}