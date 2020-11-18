package com.study.androidstudy.screens.calculation

import android.text.Editable
import com.study.androidstudy.R
import com.study.androidstudy.models.CalculationResult
import com.study.androidstudy.screens.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_calculation.*
import org.koin.android.viewmodel.ext.android.viewModel

class CalculationFragment: BaseFragment() {
    override val layoutResource: Int = R.layout.fragment_calculation

    companion object {
        fun newInstance() = CalculationFragment()
    }

    private val viewModel: CalculationViewModel by viewModel()

    override fun setupViews() {
        toolbar.apply {
            setNavigationOnClickListener { onBackPressed() }
            setNavigationIcon(R.drawable.ic_back)
            title = ""
        }
    }

    override fun setupListeners() {

        cleanBtn.setOnClickListener {
            listOf(
                aParamEditText,
                bParamEditText,
                cParamEditText
            ).forEach {
                it.text?.clear()
            }
            aParamEditText.requestFocus()
        }

        with(viewModel) {
            errorHandler.subscribe {
                showToast(it)
            }

            result.subscribe {
                showCalculationResult(it)
            }
        }

        calcBtn.setOnClickListener {
            val params = listOf(
                aParamEditText.text,
                bParamEditText.text,
                cParamEditText.text
            ).map { it?.replace("\\s+".toRegex(), "") }

            val isFilled = params.all { !it.isNullOrEmpty() }

            if (isFilled) {
                 hideKeyboard()
                viewModel.getCalculationResult(
                    params[0]!!.toDouble(),
                    params[1]!!.toDouble(),
                    params[2]!!.toDouble()
                )
            } else  {
                showToast("Заполните все параметры")
            }
        }
    }

    private fun showCalculationResult(result: CalculationResult) {
        val x1 = result.x1
        val x2 = result.x2

        if (x1 != null && x2 != null) {
            showToast("x1 = $x1\nx2 = $x2")
        } else {
            showToast("Дискриминант меньше нуля")
        }
    }
}
