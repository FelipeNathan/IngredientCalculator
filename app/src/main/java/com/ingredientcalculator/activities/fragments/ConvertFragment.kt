package com.ingredientcalculator.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ingredientcalculator.databinding.FragmentConvertBinding
import com.ingredientcalculator.entities.Fraction
import com.ingredientcalculator.service.ConvertService
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.floor
import kotlin.random.Random

class ConvertFragment : Fragment() {

    lateinit var binding: FragmentConvertBinding

    private val convertService = ConvertService()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConvertBinding.inflate(inflater, container, false)

        buttonClick()

        return binding.root
    }

    private fun buttonClick() {

        binding.button.setOnClickListener {

            val ingredientSize = 1.5 * 2.5
            var fullBottleSize = floor(ingredientSize)
            val decimal = ingredientSize - fullBottleSize;
            val fraction = convertService.calculate(decimal)

            binding.generatedNumber.text = BigDecimal(ingredientSize)
                .setScale(3, RoundingMode.HALF_EVEN)
                .toPlainString()

            if (fraction.representation == Fraction.Representation.FULL) {
                fullBottleSize++
            }

            val hasFullBottle = (fullBottleSize >= 1)

            toggleFullBottle(fullBottleSize.toInt())
            toggleFractionBottle(hasFullBottle, fraction)
        }
    }

    private fun toggleFractionBottle(hasFullBottle: Boolean, fraction: Fraction) {
        if (fraction.representation == Fraction.Representation.FULL || (fraction.representation == Fraction.Representation.EMPTY && hasFullBottle)) {
            binding.imgFraction.visibility = View.GONE
            binding.imgFractionText.visibility = View.GONE
        } else {
            binding.imgFraction.visibility = View.VISIBLE
            binding.imgFractionText.visibility = View.VISIBLE
            binding.imgFraction.setBackgroundResource(fraction.representation.resource)
            binding.imgFractionText.text = fraction.rational.toString()
        }
    }

    private fun toggleFullBottle(value: Int) {
        if (value >= 1) {
            binding.imgFull.visibility = View.VISIBLE
            binding.imgFullText.visibility = View.VISIBLE
            binding.imgFullText.text = value.toString()
        } else {
            binding.imgFull.visibility = View.GONE
            binding.imgFullText.visibility = View.GONE
        }
    }
}