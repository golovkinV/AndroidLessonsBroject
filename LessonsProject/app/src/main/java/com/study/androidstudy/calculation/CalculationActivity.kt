package com.study.androidstudy.calculation

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.study.androidstudy.BaseActivity
import com.study.androidstudy.R
import com.study.androidstudy.calculation.interfaces.CalculationEventHandler
import com.study.androidstudy.calculation.interfaces.CalculationView
import com.study.androidstudy.models.CalculationResult
import kotlinx.android.synthetic.main.activity_calculation.*
import kotlinx.android.synthetic.main.activity_calculation.calcBtn
import kotlinx.android.synthetic.main.activity_calculation.toolbar

class CalculationActivity: BaseActivity(), CalculationView {

    override val layoutResource: Int = R.layout.activity_calculation

    private var presenter: CalculationEventHandler = CalculationPresenter(this)

    override fun onDestroy() {
        super.onDestroy()
        // удаляем указатель на CalculationView во избежание утечки памяти
        presenter.deinit()
    }

    override fun showCalculationResult(result: CalculationResult) {
        val x1 = result.x1
        val x2 = result.x2

        if (x1 != null && x2 != null) {
            showToast("x1 = $x1\nx2 = $x2")
        } else {
            showToast("Дискриминант меньше нуля")
        }
    }

    override fun showError(messageError: String) {
        showToast(messageError)
    }

    // Private

    override fun setupViews() {

        // переопределяем кнопку Назад в Toolbar
        toolbar.apply {
            setNavigationOnClickListener { finish() }
            setNavigationIcon(R.drawable.ic_back)
            title = ""
        }
    }

    override fun setupListeners() {

        /*
        * на кнопку с @id/calcBtn определяем слушатель на событие нажатия
        * */
        calcBtn.setOnClickListener {
            val params = listOf(
                aParamEditText.text,
                bParamEditText.text,
                cParamEditText.text
            ).map { it?.replace("\\s+".toRegex(), "") }

            val isFilled = params.all { !it.isNullOrEmpty() }

            if (isFilled) {
                hideKeyboard()
                presenter.getCalculationResult(
                    params[0]!!.toDouble(),
                    params[1]!!.toDouble(),
                    params[2]!!.toDouble()
                )
            } else  {
                showToast("Заполните все параметры")
            }
        }
    }
}