package com.study.androidstudy.screens.timeline

import com.study.androidstudy.screens.base.BaseFragment
import com.study.androidstudy.R

class TimelineFragment : BaseFragment() {
    override val layoutResource: Int = R.layout.fragment_timeline

    companion object {
        fun newInstance() = TimelineFragment()
    }
}