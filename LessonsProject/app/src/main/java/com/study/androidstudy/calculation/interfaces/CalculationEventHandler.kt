package com.study.androidstudy.calculation.interfaces

interface CalculationEventHandler {
    fun deinit()
    fun getCalculationResult(a: Double, b: Double, c: Double)
}