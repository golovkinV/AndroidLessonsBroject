package com.study.androidstudy.calculation.interfaces

import com.study.androidstudy.models.CalculationResult

interface CalculationView {
    fun showCalculationResult(result: CalculationResult)
    fun showError(messageError: String)
}