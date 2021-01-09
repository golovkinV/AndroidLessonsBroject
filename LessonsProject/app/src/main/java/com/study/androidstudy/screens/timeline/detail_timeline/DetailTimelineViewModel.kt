package com.study.androidstudy.screens.timeline.detail_timeline

import com.github.terrakok.cicerone.Router
import com.study.androidstudy.screens.base.BaseViewModel
import com.study.androidstudy.screens.timeline.controllers.TimelineController
import com.study.androidstudy.screens.timeline.model.TimelineData

class DetailTimelineViewModel(
    private val timelineController: TimelineController,
    private val router: Router
): BaseViewModel() {

    fun doneTimeline(data: TimelineData) {
        timelineController.setDoneTimeline(data)
    }
}