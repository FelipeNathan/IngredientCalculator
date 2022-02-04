package com.ingredientcalculator.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ingredientcalculator.R
import com.ingredientcalculator.activities.fragments.LiquidFragment
import com.ingredientcalculator.activities.fragments.WeightFragment
import com.ingredientcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureMenu()
    }

    private fun configureMenu() {
        binding.bottomNavigation.setOnItemSelectedListener {
            val fragment: Fragment? = when (it.itemId) {
                R.id.page_scale -> WeightFragment()
                R.id.page_liquid -> LiquidFragment()
                else -> {
                    null
                }
            }

            if (fragment == null) {
                false
            } else {

                supportFragmentManager
                    .beginTransaction()
                    .replace(binding.fragmentContainerView.id, fragment)
                    .commit()

                true
            }
        }
    }
}