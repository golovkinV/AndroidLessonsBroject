package com.study.androidstudy.main

import android.content.Intent
import com.study.androidstudy.BaseActivity
import com.study.androidstudy.R
import com.study.androidstudy.calculation.CalculationActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override val layoutResource: Int = R.layout.activity_main

    override fun setupListeners() {

        /*
        * на кнопку с @id/helloBtn определяем слушатель на событие нажатия
        * */
        helloBtn.setOnClickListener {

            // получаем введенное имя
            val userName = nameEditText.text

            // если имя не пустое, то выводим приветствие, в обратном случае ошибка
            if (!userName.isNullOrEmpty()) {
                hideKeyboard()
                showToast("Привет, $userName! Как дела?")
            } else {
                showToast("Введите, пожалуйста, имя!")
            }
        }

        calcBtn.setOnClickListener {
            openCalculation()
        }
    }


    // Private

    /**
     *  Роут на экран Решения квадратного уравнения
     */
    private fun openCalculation() {
        /*
        * startActivity - метод для открытия нашего экрана, в который передается объект класс Intent
        *
        * Intent -  это объект обмена сообщениями, с помощью которого запрашивается какое-то действие.
        * Он описывает операцию, которую требуется запустить, а также содержит все остальные
        * необходимые данные. В нашем случае мы передаем туда
        *
        * apply - метод для применения каких-либо настроек для класс, у которого он вызывается.
        * В нашем случае определяется флаг для поведения открываемого представления, чтобы перенести
        * его на передний план выполнения
        * */
        startActivity(Intent(
            this,
            CalculationActivity::class.java
        ).apply {
            addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        })
    }
}