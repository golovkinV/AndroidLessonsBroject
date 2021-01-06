package com.study.androidstudy.router


import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.study.androidstudy.screens.calculation.CalculationFragment
import com.study.androidstudy.screens.hello.HelloFragment
import com.study.androidstudy.screens.math_calculation.MathCalculationFragment
import com.study.androidstudy.screens.timeline.TimelineFragment

object Screens {

    fun getHelloScreen() =
        FragmentScreen { HelloFragment.newInstance() }

    fun getCalculationScreen() =
        FragmentScreen { CalculationFragment.newInstance() }

    fun getMathCalculationFragment() =
        FragmentScreen { MathCalculationFragment.newInstance() }

    fun getTimelineFragment() =
        FragmentScreen { TimelineFragment.newInstance() }
}