package com.study.androidstudy.router


import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.study.androidstudy.screens.calculation.CalculationFragment
import com.study.androidstudy.screens.hello.HelloFragment

object Screens {

    fun getHelloScreen() =
        FragmentScreen { HelloFragment.newInstance() }

    fun getCalculationScreen() =
        FragmentScreen { CalculationFragment.newInstance() }
}