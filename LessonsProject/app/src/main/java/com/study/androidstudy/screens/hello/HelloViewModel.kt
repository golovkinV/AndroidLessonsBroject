package com.study.androidstudy.screens.hello

import androidx.lifecycle.MutableLiveData
import com.github.terrakok.cicerone.Router
import com.study.androidstudy.router.Screens
import com.study.androidstudy.screens.base.BaseViewModel

class HelloViewModel(
    private val router: Router
): BaseViewModel() {

    val message = MutableLiveData<String>()
    val keyboard = MutableLiveData<Boolean>()

    fun showMessage(name: String?) {
        if (!name.isNullOrEmpty()) {
            keyboard.value = true
            message.value = "Привет, $name!"
        } else {
            keyboard.value = false
            message.value = "Введите, пожалуйста, имя!"
        }
    }

    fun routeToQuadratic() {
        router.navigateTo(Screens.getCalculationScreen())
    }

    fun routeToCalculation() {
        router.navigateTo(Screens.getMathCalculationFragment())
    }
}