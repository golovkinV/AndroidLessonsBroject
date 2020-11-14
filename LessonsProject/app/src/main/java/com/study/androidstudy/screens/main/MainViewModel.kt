package com.study.androidstudy.screens.main

import com.github.terrakok.cicerone.Router
import com.study.androidstudy.router.Screens
import com.study.androidstudy.screens.base.BaseViewModel

class MainViewModel(
    private val router: Router
): BaseViewModel() {

    fun create() {
        router.newRootScreen(Screens.getHelloScreen())
    }
}