package com.study.androidstudy.calculation

import com.study.androidstudy.calculation.interfaces.CalculationEventHandler
import com.study.androidstudy.calculation.interfaces.CalculationView
import com.study.androidstudy.models.CalculationResult
import kotlin.math.sqrt

class CalculationPresenter(
    private var view: CalculationView?
): CalculationEventHandler {

    override fun getCalculationResult(a: Double, b: Double, c: Double)  {

        val D = b * b - 4 * a * c

        when {
            a == 0.0 -> {
                view?.showError("Это не квадратное уравнение!")
            }
            D >= 0 -> {
                val x1 = calcX(a, b, sqrt(D))
                val x2 = calcX(a, b, -sqrt(D))
                view?.showCalculationResult(CalculationResult(x1, x2))
            }
            else -> {
                view?.showCalculationResult(CalculationResult())
            }
        }
    }

    override fun deinit() {
        view = null
    }

    private fun calcX(a: Double, b: Double, sqrtD: Double): Double = (- b + sqrtD) / (2 * a)
}