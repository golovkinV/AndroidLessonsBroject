package com.study.androidstudy.screens.auth

import com.github.terrakok.cicerone.Router
import com.study.androidstudy.router.Screens
import com.study.androidstudy.screens.base.BaseViewModel

class AuthViewModel(
    private var router: Router
): BaseViewModel() {

    fun routeToCalculation() {
        router.navigateTo(Screens.getMathCalculationFragment())
    }
}