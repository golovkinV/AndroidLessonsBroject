package com.study.androidstudy.screens.math_calculation

import androidx.lifecycle.MutableLiveData
import com.github.terrakok.cicerone.Router
import com.study.androidstudy.screens.base.BaseViewModel

enum class SupportCalcAction(val value: String) {
    NONE("none"),
    CLEAN("cleanBtn"),
    CHANGE("changeBtn"),
    COMMA("commaBtn")
}

enum class CalcAction(val value: String) {
    NONE("none"),
    MULTIPLY("multiplyBtn"),
    DIVISION("divisionBtn"),
    MINUS("minusBtn"),
    PLUS("plusBtn"),
    MOD("modBtn")
}

class MathCalculationViewModel(
    private val router: Router
): BaseViewModel() {

    var firstValue: Double? = null

    private var secondValue: Double? = null

    private var inputValue: Double? = null

    private var currentCalcAction = CalcAction.NONE

    val supportActionLiveData = MutableLiveData<SupportCalcAction>()

    val resultLiveData = MutableLiveData<Double>()
    val intResultLiveData = MutableLiveData<Int>()

    var isNextNumberInput: Boolean = false
    var isNewCalculation: Boolean = false

    fun setCurrentValue(value: String) {
        inputValue = value.toDouble()
    }

    fun changeValueMark() {
        if (inputValue == null) { return }
        val value = -inputValue!!
        inputValue = value

        if (value.isInt()) {
            intResultLiveData.value = value.toInt()
        } else {
            resultLiveData.value = value
        }
    }

    fun setCalcAction(id: String) {
        if (firstValue == null) {
            setFirstValue()
        }
        currentCalcAction = CalcAction.values().first { it.value == id }
    }

    fun setSupportCalcAction(id: String) {
        supportActionLiveData.value = SupportCalcAction.values().first { it.value == id }
    }

    fun calcResult() {
        if (currentCalcAction != CalcAction.NONE)  {
            setSecondValue()
            val result = when(currentCalcAction) {
                CalcAction.PLUS -> {
                    firstValue!! + secondValue!!
                }
                CalcAction.MINUS -> {
                    firstValue!! - secondValue!!
                }
                CalcAction.MULTIPLY -> {
                    firstValue!! * secondValue!!
                }
                CalcAction.DIVISION -> {
                    firstValue!! / secondValue!!
                }
                CalcAction.MOD -> {
                    firstValue!! % secondValue!!
                }
                else -> inputValue!!
            }

            if (result.isInt()) {
                intResultLiveData.value = result.toInt()
            } else {
                resultLiveData.value = result
            }

            firstValue = result
            isNextNumberInput = true
            isNewCalculation = true
            currentCalcAction = CalcAction.NONE
        }
    }

    fun clean() {
        inputValue = null
        firstValue = null
        secondValue = null
        currentCalcAction = CalcAction.NONE
    }

    // Private

    private fun setFirstValue() {
        if (inputValue == null) return
        firstValue = inputValue!!.toDouble()
        isNextNumberInput = true
    }

    private fun setSecondValue() {
        if (inputValue == null) return
        secondValue = inputValue!!.toDouble()
    }

    private fun Double.isInt(): Boolean {
        val rem = this.rem(1)
        return rem.equals(0.0) || rem.equals(-0.0)
    }
}