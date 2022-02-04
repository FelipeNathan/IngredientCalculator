package com.ingredientcalculator.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.ingredientcalculator.R
import com.ingredientcalculator.databinding.LiquidFragmentBinding
import com.ingredientcalculator.formatter.DecimalWatcher
import com.ingredientcalculator.formatter.valueOrZero
import com.ingredientcalculator.service.CalculatorService

class LiquidFragment : Fragment(R.layout.liquid_fragment) {

    lateinit var binding: LiquidFragmentBinding
    private val calculator = CalculatorService()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LiquidFragmentBinding.inflate(inflater, container, false)

        with(binding.precoDoProdutoInput) {
            addTextChangedListener(DecimalWatcher.build2Decimal(this))
            doOnTextChanged { _, _, _, _ -> calculate() }
        }
        with(binding.litrosDoProdutoInput) {
            addTextChangedListener(DecimalWatcher.build3Decimal(this))
            doOnTextChanged { _, _, _, _ -> calculate() }
        }
        with(binding.litrosUtilizadoInput) {
            addTextChangedListener(DecimalWatcher.build3Decimal(this))
            doOnTextChanged { _, _, _, _ -> calculate() }
        }

        return binding.root
    }

    private fun calculate() {
        val price = binding.precoDoProdutoInput.text.valueOrZero()
        val liters = binding.litrosDoProdutoInput.text.valueOrZero()
        val usedLiters = binding.litrosUtilizadoInput.text.valueOrZero()

        binding.resultadoInput.text = calculator.calculateAndFormat(price, liters, usedLiters)
    }
}