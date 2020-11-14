package com.study.androidstudy.screens.main

import android.os.Bundle
import com.github.terrakok.cicerone.NavigatorHolder
import com.study.androidstudy.screens.base.BaseActivity
import com.study.androidstudy.R
import org.koin.android.ext.android.inject
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    override val layoutResource: Int = R.layout.activity_main
    private val navigatorHolder by inject<NavigatorHolder>()

    private val viewModel: MainViewModel by viewModel()

    private val navigator by lazy {
        AppNavigator(this, R.id.contentFrame)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.create()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }
}