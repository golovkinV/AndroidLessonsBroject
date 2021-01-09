package com.study.androidstudy.screens.timeline.model

import java.io.Serializable

data class TimelineData(
    val title: String = "",
    val desc: String = "",
    val date: Long = 0,
    var isDone: Boolean = false
): Serializable
