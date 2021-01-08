package com.study.androidstudy.screens.timeline.add_timeline

import com.study.androidstudy.screens.base.BaseViewModel
import com.study.androidstudy.screens.timeline.controllers.TimelineController
import com.study.androidstudy.screens.timeline.model.TimelineData

class AddTimelineViewModel(
    private val timelineController: TimelineController
): BaseViewModel() {

    var date: Long = 0

    fun addNewItem(data: TimelineData) {
        timelineController.setNewTimeline(data)
    }
}