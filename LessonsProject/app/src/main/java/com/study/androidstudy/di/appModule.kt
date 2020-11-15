package com.study.androidstudy.di


import com.github.terrakok.cicerone.Cicerone
import com.study.androidstudy.screens.calculation.CalculationViewModel
import com.study.androidstudy.screens.hello.HelloViewModel
import com.study.androidstudy.screens.main.MainViewModel
import com.study.androidstudy.screens.math_calculation.MathCalculationViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    val cicerone = Cicerone.create()
    single { cicerone.router }
    single { cicerone.getNavigatorHolder() }

    // View Models

    viewModel { MainViewModel(get()) }
    viewModel { HelloViewModel(get()) }
    viewModel { CalculationViewModel(get()) }
    viewModel { MathCalculationViewModel(get()) }
}