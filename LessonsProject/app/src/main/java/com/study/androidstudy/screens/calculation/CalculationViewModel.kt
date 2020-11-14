package com.study.androidstudy.screens.calculation

import androidx.lifecycle.MutableLiveData
import com.github.terrakok.cicerone.Router
import com.study.androidstudy.models.CalculationResult
import com.study.androidstudy.screens.base.BaseViewModel
import kotlin.math.sqrt

class CalculationViewModel(
    private var router: Router
): BaseViewModel() {

    val errorHandler = MutableLiveData<String>()
    val result = MutableLiveData<CalculationResult>()

    fun getCalculationResult(a: Double, b: Double, c: Double)  {

        val D = b * b - 4 * a * c

        when {
            a == 0.0 -> {
                errorHandler.value = "Это не квадратное уравнение!"
            }
            D >= 0 -> {
                val x1 = calcX(a, b, sqrt(D))
                val x2 = calcX(a, b, -sqrt(D))
                result.value = CalculationResult(x1, x2)
            }
            else -> {
                result.value = CalculationResult()
            }
        }
    }

    private fun calcX(a: Double, b: Double, sqrtD: Double): Double = (- b + sqrtD) / (2 * a)
}