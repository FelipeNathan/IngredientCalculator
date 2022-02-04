package com.ingredientcalculator.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.ingredientcalculator.R
import com.ingredientcalculator.databinding.WeightFragmentBinding
import com.ingredientcalculator.formatter.DecimalWatcher
import com.ingredientcalculator.formatter.valueOrZero
import com.ingredientcalculator.service.CalculatorService

class WeightFragment : Fragment(R.layout.weight_fragment) {

    lateinit var binding: WeightFragmentBinding
    private val calculator = CalculatorService()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WeightFragmentBinding.inflate(inflater, container, false)

        with(binding.precoDoProdutoInput) {
            addTextChangedListener(DecimalWatcher.build2Decimal(this))
            doOnTextChanged { _, _, _, _ -> calculate() }
        }
        with(binding.pesoDoProdutoInput) {
            addTextChangedListener(DecimalWatcher.build3Decimal(this))
            doOnTextChanged { _, _, _, _ -> calculate() }
        }
        with(binding.pesoUtilizadoInput) {
            addTextChangedListener(DecimalWatcher.build3Decimal(this))
            doOnTextChanged { _, _, _, _ -> calculate() }
        }

        return binding.root
    }

    private fun calculate() {
        val price = binding.precoDoProdutoInput.text.valueOrZero()
        val weight = binding.pesoDoProdutoInput.text.valueOrZero()
        val usedWeight = binding.pesoUtilizadoInput.text.valueOrZero()

        binding.resultadoInput.text = calculator.calculateAndFormat(price, weight, usedWeight)
    }
}