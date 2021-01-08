package com.study.androidstudy.screens.timeline.controllers

import com.jakewharton.rxrelay2.BehaviorRelay
import com.study.androidstudy.screens.timeline.model.TimelineData
import io.reactivex.Observable

class TimelineController {
    private val emptyTimeline: TimelineData = TimelineData()
    private val newTimelineRelay = BehaviorRelay.createDefault(emptyTimeline)

    val timelines: Observable<TimelineData> = newTimelineRelay.hide().distinctUntilChanged()

    fun setNewTimeline(data: TimelineData) = newTimelineRelay.accept(data)

    fun getNewTimeline() = newTimelineRelay.value!!
}