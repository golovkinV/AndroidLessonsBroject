package com.study.androidstudy.screens.timeline.model

data class TimelineData(
    val title: String,
    val desc: String,
    val date: Long,
    val isDone: Boolean = false
)
