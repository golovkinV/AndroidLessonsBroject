package com.study.androidstudy.screens.math_calculation

import android.annotation.SuppressLint
import com.study.androidstudy.screens.base.BaseFragment
import com.study.androidstudy.R
import kotlinx.android.synthetic.main.fragment_calculation.*
import kotlinx.android.synthetic.main.fragment_math_calculation.*
import kotlinx.android.synthetic.main.fragment_math_calculation.cleanBtn
import kotlinx.android.synthetic.main.fragment_math_calculation.toolbar
import org.koin.android.viewmodel.ext.android.viewModel

class MathCalculationFragment: BaseFragment() {
    override val layoutResource: Int = R.layout.fragment_math_calculation

    companion object {
        fun newInstance() = MathCalculationFragment()
    }

    private val viewModel: MathCalculationViewModel by viewModel()

    override fun setupViews() {
        toolbar.apply {
            setNavigationOnClickListener { onBackPressed() }
            setNavigationIcon(R.drawable.ic_back)
            title = ""
        }
    }

    @SuppressLint("SetTextI18n")
    override fun setupListeners() {

        equallyBtn.setOnClickListener {
            viewModel.calcResult()
        }


        with(viewModel) {

            intResultLiveData.subscribe {
                calcView.text = "$it"
            }
            resultLiveData.subscribe {
                calcView.text = "$it".replace('.', ',')
            }

            supportActionLiveData.subscribe {
                when(it) {
                    SupportCalcAction.CHANGE -> {
                        changeValueMark()
                    }
                    SupportCalcAction.COMMA -> {
                        val text = calcView.text
                        calcView.text = "$text,"
                    }
                    else -> { return@subscribe }
                }
            }

            cleanBtn.setOnClickListener {
                calcView.text = "0"
                clean()
            }

            listOf(
                button0, button1, button2,
                button3, button4, button5,
                button6, button7, button8,
                button9
            ).forEach { btn ->
                btn.setOnClickListener {
                    val currentValue = calcView.text
                    val newValue = btn.text

                    val text =
                        if (currentValue == "0" || isNextNumberInput) {
                            isNextNumberInput = false
                            if (isNewCalculation) {
                                isNewCalculation = false
                                clean()
                            }
                            newValue
                        } else "$currentValue$newValue"

                    calcView.text = text
                    setCurrentValue(text.toString().replace(',', '.'))
                }
            }

            listOf(
                changeBtn,
                commaBtn
            ).forEach { btn ->
                btn.setOnClickListener {
                    val id = resources.getResourceEntryName(btn.id)
                    setSupportCalcAction(id)
                }
            }

            listOf(
                divisionBtn,
                multiplyBtn,
                plusBtn,
                minusBtn,
                modBtn
            ).forEach { btn ->
                btn.setOnClickListener {
                    val id = resources.getResourceEntryName(btn.id)
                    isNewCalculation = false
                    setCalcAction(id)
                }
            }
        }
    }
}