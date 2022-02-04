package com.ingredientcalculator

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(peso_do_produto_input) { addTextChangedListener(DecimalWatcher.build3Decimal(this)) }
        with(preco_do_produto_input) { addTextChangedListener(DecimalWatcher.build2Decimal(this)) }
        with(peso_utilizado_input) { addTextChangedListener(DecimalWatcher.build3Decimal(this)) }

        peso_do_produto_input.doOnTextChanged { _, _, _, _ -> calculate() }
        preco_do_produto_input.doOnTextChanged { _, _, _, _ -> calculate() }
        peso_utilizado_input.doOnTextChanged { _, _, _, _ -> calculate() }
    }

    private fun calculate() {
        val peso = peso_do_produto_input.text.valueOrZero()
        val preco = preco_do_produto_input.text.valueOrZero()
        val peso_utilizado = peso_utilizado_input.text.valueOrZero()
        try {
            val result = (preco * peso_utilizado)
                .divide(peso, 2, RoundingMode.HALF_UP)
                .toString()
                .replace(".", ",")
            resultado_input.text = "R$ $result"
        } catch (ex: Exception) {
            resultado_input.text = "R$ 0,00"
        }
        Log.d("TEXT_CHANGE", "$peso - $preco - $peso_utilizado")
    }

    private fun Editable?.valueOrZero(): BigDecimal {
        val value = (this?.ifBlank { "0" } ?: "0").toString().replace(",", ".")
        return try {
            BigDecimal(value)
        } catch (ex: NumberFormatException) {
            BigDecimal("0$value")
        }
    }
}